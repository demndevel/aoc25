package org.example

import kotlin.collections.component1
import kotlin.collections.component2

object Day4 {
    object Part1 {
        fun solve(): Int {
            val result = readDay4().lines().toGrid().count { coordinate, cell ->
                if (cell.isRoll()) {
                    val adjacentCount = coordinate.getAdjacent().count(Char::isRoll)

                    if (adjacentCount < 4) {
                        return@count true
                    }
                }
                false
            }
            return result
        }


        data class Coordinate(val x: Int, val y: Int)

        context(grid: Grid)
        fun Coordinate.getAdjacent(): List<Char> {
            val deltas = (-1..1).flatMap { dy ->
                (-1..1).map { dx -> dy to dx }
            }.filter { (dy, dx) -> dy != 0 || dx != 0 }

            return deltas.mapNotNull { (dy, dx) ->
                val neighbor = Coordinate(y = y + dy, x = x + dx)
                neighbor.takeIf { it in grid.cells }?.let { grid.cells[it] }
            }
        }

        data class Grid(
            val cells: Map<Coordinate, Char>
        ) {
            fun count(predicate: Grid.(Coordinate, Char) -> Boolean): Int {
                return cells.count { (coordinate, char) ->
                    predicate(coordinate, char)
                }
            }
        }

        private fun List<String>.toGrid(): Grid {
            return Grid(
                cells = this.flatMapIndexed { yIndex, line ->
                    line.mapIndexed { xIndex, char ->
                        Coordinate(yIndex, xIndex) to char
                    }
                }.toMap()
            )
        }
    }

    object Part2 {
        fun solve(): Int {
            var previousGrid = readDay4().lines().toGrid()
            var totalRemoved = 0

            while (previousGrid != removeRolls(previousGrid).second) {
                val (removed, newGrid) = removeRolls(previousGrid)
                previousGrid = newGrid
                totalRemoved += removed
            }

            return totalRemoved
        }

        fun removeRolls(grid: Grid): Pair<Int, Grid> {
            val coordinatesToRemove = grid.cells.filter { (coordinate, cell) ->
                if (cell.isRoll()) {
                    val adjacentCount = coordinate.getAdjacent(grid).count(Char::isRoll)

                    if (adjacentCount < 4) {
                        return@filter true
                    }
                }
                false
            }

            val newCells = grid.cells.toList().associate { (coordinate, cell) ->
                if (coordinate in coordinatesToRemove) {
                    coordinate to '.'
                } else {
                    coordinate to cell
                }
            }

            return Pair(coordinatesToRemove.count(), Grid(newCells))
        }

        data class Coordinate(val x: Int, val y: Int)

        fun Coordinate.getAdjacent(grid: Grid): List<Char> {
            val deltas = (-1..1).flatMap { dy ->
                (-1..1).map { dx -> dy to dx }
            }.filter { (dy, dx) -> dy != 0 || dx != 0 }

            return deltas.mapNotNull { (dy, dx) ->
                val neighbor = Coordinate(y = y + dy, x = x + dx)
                neighbor.takeIf { it in grid.cells }?.let { grid.cells[it] }
            }
        }

        data class Grid(val cells: Map<Coordinate, Char>)

        private fun List<String>.toGrid(): Grid {
            return Grid(
                cells = this.flatMapIndexed { yIndex, line ->
                    line.mapIndexed { xIndex, char ->
                        Coordinate(yIndex, xIndex) to char
                    }
                }.toMap()
            )
        }
    }
}

private fun Char.isRoll(): Boolean = this == '@'
