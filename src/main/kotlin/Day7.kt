package org.example

object Day7 {
    object Part1 {
        fun solve(): Int {
            val input = readDay7()
            val indexOfStart = input.take(1).joinToString("").indexOf("S")
            
            val initialPreviousPositions = setOf(indexOfStart)
            val initial = Pair(initialPreviousPositions, 0)
            
            val result = input.fold(
                initial = initial,
            ) { (previousPositions, splitCounter), line ->
                val splitterIndices = line
                    .withIndex()
                    .filter { (_, char) -> char == '^' }
                    .map { (index, _) -> index }
                
                val (newPositions, splitTimes) = split(previousPositions, splitterIndices)
                Pair(newPositions, splitTimes + splitCounter)
            }

            return result.second
        }
        
        private fun split(
            previousPositions: Set<Int>,
            splitterIndices: List<Int>,
        ): Pair<Set<Int>, Int> {
            val result = mutableSetOf<Int>()
            var splitCounter = 0
            
            for (pipe in previousPositions) {
                if (pipe in splitterIndices) {
                    result.add(pipe - 1)
                    result.add(pipe + 1)
                    splitCounter++
                } else {
                    result.add(pipe)
                }
            }
            
            return Pair(result, splitCounter)
        }
    }
    
    object Part2 {
        fun solve(): Int {
            val input = readDay7()
            val indexOfStart = input.take(1).joinToString("").indexOf("S")

            val initialPreviousPositions = mapOf(indexOfStart to 1)
            val initial = Pair(initialPreviousPositions, 0)
            
            val result = input.fold(
                initial = initial,
            ) { (previousPositions, splitCounter), line ->
                val splitterIndices = line
                    .withIndex()
                    .filter { (_, char) -> char == '^' }
                    .map { (index, _) -> index }

                val (newPositions, splitTimes) = split(previousPositions, splitterIndices)
                Pair(newPositions, splitTimes + splitCounter)
            }

            return result.first.values.sum()
        }

        private fun split(
            previousPositions: Map<Int, Int,>,
            splitterIndices: List<Int>,
        ): Pair<Map<Int, Int>, Int> {
            val result = mutableMapOf<Int, Int>()
            var splitCounter = 0
            
            for ((pipe, ways) in previousPositions) {
                if (pipe in splitterIndices) {
                    result[pipe - 1] = result.getOrPut(pipe - 1) { ways + 1 }
                    result[pipe + 1] = result.getOrPut(pipe + 1) { ways + 1 }
//                    result.getOrPut(pipe - 1) { ways }
//                    result[pipe - 1] = ways + 1
//                    result[pipe + 1] = ways + 1
                    splitCounter++
                } else {
                    result[pipe] = ways
                }
            }
            
            println(splitterIndices.joinToString("  "))
            println("#$result")

            return Pair(result, splitCounter)
        }
    }
}
