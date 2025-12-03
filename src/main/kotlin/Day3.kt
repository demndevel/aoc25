package org.example

object Day3 {
    object Part1 {
        fun solve(): Long = readDay3().sumOf(::getMax)

        private fun getMax(string: String): Long {
            var max = 0L
            string.forEachIndexed { indexI, i ->
                string.forEachIndexed { indexJ, j ->
                    if (indexI >= indexJ) return@forEachIndexed
                    val concatenated = "$i$j".toLong()
                    if (concatenated > max) max = concatenated
                }
            }
            return max
        }
    }

    /**
     * Unreadable, but funny (no mutability!)
     */
    object Part2 {
        fun solve(): Long = readDay3().sumOf { line -> getMax(line, 12) }

        fun getMax(input: String, digits: Int): Long = (0..<digits)
            .fold(Pair(input.map(Char::digitToInt), String())) { (remaining, result), digit ->
                val next = remaining.dropLast(digits - digit - 1).max()
                Pair(remaining.drop(remaining.indexOf(next) + 1), result + next)
            }
            .let { (_, result) -> result.toLong() }
    }
}
