package org.example

import kotlin.math.absoluteValue

object Day9 {
    object Part1 {
        fun solve(): Long {
            val tiles = readDay9().map(::parseRedTiles)
            var largest = 0L

            for (tile1 in tiles) {
                for (tile2 in tiles) {
                    val square = getSquare(tile1, tile2)
                    if (square > largest) largest = square
                }
            }

            return largest
        }

        private fun parseRedTiles(line: String): RedTile {
            val split = line.split(',')
            return RedTile(
                x = split[0].toLong(),
                y = split[1].toLong(),
            )
        }

        private fun getSquare(tile1: RedTile, tile2: RedTile): Long {
            val width = (tile1.x - tile2.x).absoluteValue.plus(1)
            val height = (tile1.y - tile2.y).absoluteValue.plus(1)
            return width * height
        }

        data class RedTile(val x: Long, val y: Long)
    }
}
