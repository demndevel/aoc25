package org.example

object Day5 {
    /**
     * Pretty dumb bruteforce solution, although it was obvious that the second part will require merging ranges..
     */
    object Part1 {
        fun solve(): Int {
            val input = parse(readDay5())
            var result = 0
            input.products.forEach { id ->
                if (input.ranges.any { range -> id.long in range }) {
                    result++
                }
            }
            return result
        }
    }

    object Part2 {
        fun solve(): Long {
            val input = parse(readDay5())
            val mergedRange = mergeRanges(input.ranges)
            return mergedRange.sumOf { range -> range.last - range.first + 1 }
        }

        private fun mergeRanges(ranges: List<LongRange>): List<LongRange> {
            val result = mutableListOf<LongRange>()

            val sortedRanges = ranges.sortedBy { range -> range.first }

            for (range in sortedRanges) {
                if (result.isEmpty() || result.last().last < range.first) {
                    result.add(range.first..range.last)
                } else {
                    result[result.lastIndex] = result.last().first..maxOf(result.last().last, range.last)
                }
            }

            return result
        }
    }

    @JvmInline
    value class ProductId(val long: Long)

    data class Input(
        val ranges: List<LongRange>,
        val products: List<ProductId>
    )

    private fun parse(lines: List<String>): Input {
        val ranges = lines
            .takeWhile { line -> line.isNotBlank() }
            .map { line ->
                val split = line.split('-')
                split[0].toLong()..split[1].toLong()
            }
        val products = lines
            .dropWhile { line -> line.isBlank() }
            .mapNotNull { line ->
                line.toLongOrNull()?.let { ProductId(it) }
            }
        return Input(
            ranges = ranges,
            products = products,
        )
    }
}
