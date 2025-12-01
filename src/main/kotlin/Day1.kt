package org.example

/**
 * No mutability: only pure functions without vars and cycles.
 * There is only first part of the day. I wanted to make it the same way as the first one, but
 * there was late night and I decided to take healthy sleep instead of perdollings
 * related to pure functions, divisions and so on. I didn't wanted to solve that via sequences, loops and
 * other bullshit like most people did. For AoC leaderboards — of course — I've solved it via loops,
 * but the solution was made on the fly, so I decided to better do not show that here.
 */
object Day1 {
    object Verbose {
        fun solve(): Int {
            val input: List<String> = readDay1()
            val initial = Pair(0, 50)

            val (result, _) = input.fold(initial) { (zeros, currentDial), nextMove ->
                val (side, rotationAmount) = nextMove.parse()
                val value = calculateMove(side, currentDial, rotationAmount)
                if (value == 0) return@fold Pair(zeros + 1, value)
                Pair(zeros, value)
            }

            return result
        }

        private fun calculateMove(side: Side, currentDial: Int, rotationAmount: Int): Int {
            val remainder = rotationAmount % 100
            return when (side) {
                Side.L -> (currentDial - remainder).mod(100)

                Side.R -> (currentDial + remainder).mod(100)
            }
        }

        sealed interface Side {
            data object R : Side
            data object L : Side
        }

        private fun String.parse(): Pair<Side, Int> {
            val side = when (this[0]) {
                'L' -> Side.L
                'R' -> Side.R
                else -> error("Unknown side")
            }
            return Pair(side, this.drop(1).toInt())
        }
    }

    /**
     * IDK why I made this, but it looks a bit funny.
     */
    object Obfuscated {
        fun solve(): Int = readDay1()
            .map(::parse)
            .fold(Pair(0, 50)) { (zeros, currentDial), (side, rotationAmount) ->
                val value = calculateMove(side, currentDial, rotationAmount).mod(100)
                if (value == 0) return@fold Pair(zeros.plus(1), value)
                Pair(zeros, value)
            }
            .let { (result, _) -> result }

        private fun calculateMove(side: Char, currentDial: Int, rotationAmount: Int): Int {
            return when (side) {
                'L' -> currentDial - rotationAmount % 100
                'R' -> currentDial + rotationAmount % 100
                else -> error("unknown")
            }
        }

        private fun parse(string: String): Pair<Char, Int> = Pair(string[0], string.drop(1).toInt())
    }
}
