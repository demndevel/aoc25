package org.example

import kotlin.arrayOf
import kotlin.collections.mutableListOf

object Day6 {
    object Part1 {
        fun solve(): Long {
            val rows = parse(readDay6())
            return calculate(rows)
        }

        private fun parse(input: String): List<Column> {
            val columns: MutableList<MutableList<Long>> = mutableListOf(mutableListOf())

            val parsed = input
                .lines()
                .dropLast(2)
                .also(::println)
                .forEachIndexed { rowIndex, line ->
                    line
                        .split(' ')
                        .filter(String::isNotBlank)
                        .map(String::trim)
                        .forEachIndexed { columnIndex, string ->
                            if (columnIndex >= columns.size) {
                                columns.add(mutableListOf())
                            }
                            if (rowIndex >= columns[columnIndex].size) {
                                columns[columnIndex].add(string.toLong())
                            } else {
                                columns[columnIndex][rowIndex] = string.toLong()
                            }
                        }
                }

            val operators = input.lines().dropLast(1).last().map(Char::toString).filter(String::isNotBlank)
            return columns.mapIndexed { index, column ->
                when (operators[index]) {
                    "+" -> Column.AddColumn(column)
                    "*" -> Column.MultiplyColumn(column)
                    else -> error("Unknown $index")
                }
            }
        }

        sealed interface Column {
            data class AddColumn(val numbers: List<Long>) : Column
            data class MultiplyColumn(val numbers: List<Long>) : Column
        }

        private fun calculate(column: List<Column>): Long {
            return column.sumOf { row ->
                val calculation = when (row) {
                    is Column.AddColumn -> {
                        row.numbers.sum()
                    }

                    is Column.MultiplyColumn -> {
                        row.numbers.reduce { num, accumulator ->
                            num * accumulator
                        }
                    }
                }

                calculation
            }
        }
    }

    object Part2 {
        fun solve(): Long {
            return calculate(readDay6().transpose())
        }

        private fun parse(input: String): List<Column> {
            input
                .transpose()
            return emptyList()
        }

        fun String.transpose(): List<Column> {
            val lines = this.lines().dropLast(2)
            val transposed = MutableList(lines.first().length) { columnIndex ->
                MutableList(lines.size + 1) { rowIndex -> "" }
            }
            lines.forEachIndexed { columnIndex, string ->
                string.forEachIndexed { rowIndex, number ->
                    transposed[rowIndex][columnIndex] = number.toString()
                }
            }

            val operators = this.lines().dropLast(1).last().split(' ').filter(String::isNotBlank)

            val columns = transposed
                .splitByEmpty()
                .map { line ->
                    line.map(String::toLong)
                }
                .mapIndexed { index, line ->
                    when (operators[index]) {
                        "*" -> Column.MultiplyColumn(line)
                        "+" -> Column.AddColumn(line)
                        else -> error("unknown operator ${operators[index]}")
                    }
                }
                .also(::println)

            return columns
        }

        private fun List<List<String>>.splitByEmpty(): List<List<String>> {
            val result: MutableList<MutableList<String>> = mutableListOf(mutableListOf())

            for (elements in this) {
                if (elements.all { element -> element.isBlank() }) {
                    result.add(mutableListOf())
                } else {
                    result.last().add(elements.joinToString(separator = "").trim())
                }
            }

            return result
        }

        sealed interface Column {
            data class AddColumn(val numbers: List<Long>) : Column
            data class MultiplyColumn(val numbers: List<Long>) : Column
        }

        private fun calculate(column: List<Column>): Long {
            return column.sumOf { row ->
                val calculation = when (row) {
                    is Column.AddColumn -> {
                        row.numbers.sum()
                    }

                    is Column.MultiplyColumn -> {
                        row.numbers.reduce { num, accumulator ->
                            num * accumulator
                        }
                    }
                }

                calculation
            }
        }
    }
}
