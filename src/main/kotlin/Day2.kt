package org.example

/**
 * Pure functions, make val not var
 */
object Day2 {
    object Part1 {
        fun solve(): Long = readDay2()
            .split(',')
            .map(::parseRange)
            .fold(0L) { sum, range ->
                range
                    .filter(::isInvalid)
                    .sum()
                    .plus(sum)
            }

        private fun parseRange(string: String): LongRange {
            val split = string.split('-')
            val begin = split[0].toLong()
            val end = split[1].toLong()
            return begin..end
        }

        private fun isInvalid(long: Long): Boolean {
            val string = long.toString()
            if (string.length % 2 == 0) {
                val mid = (string.length / 2) - 1
                val first = string.slice(0..mid)
                val second = string.slice((mid + 1)..<string.length)
                return first == second
            }
            return false
        }
    }

    object Part2 {
        fun solve(): Long = readDay2()
            .split(',')
            .map(::parseRange)
            .fold(0L) { sum, range ->
                range.filter(::isInvalid).sum().plus(sum)
            }

        private fun parseRange(string: String): LongRange {
            val split = string.split('-')
            val begin = split[0].toLong()
            val end = split[1].toLong()
            return begin..end
        }

        private fun isInvalid(long: Long): Boolean {
            val string = long.toString()
            val mid = (string.length / 2)

            for (divisor in 1..mid) {
                val pieces = string.chunked(divisor)
                val allEqual = pieces.distinct().count() == 1
                if (allEqual) return true
            }

            return false
        }
    }
}
