package org.example

import kotlin.math.sqrt
import kotlin.math.pow

object Day8 {
    object Part1 {
        fun solve(): Int {
            val boxes = readDay8().map(::parse)
            
//            val distances = mutableListOf<Circuits>()
//            
//            for (box in boxes) {
//                val (closestDistance, closestBox) = findClosest(box, boxes)
//                
//                if (closestDistance in distances) break
//                
//                
//            }

            return boxes.count()
        }
        
        private fun findClosest(
            box: JunctionBox,
            list: List<JunctionBox>,
        ): Pair<Double, JunctionBox> {
            var closestDistance = Double.MAX_VALUE
            var closestBox: JunctionBox? = null
            for (otherBox in list) {
                if (box == otherBox) continue
                val newDistance = findDistance(box, otherBox)
                if (newDistance > closestDistance) {
                    closestDistance = newDistance
                    closestBox = box
                }
            }
            return closestDistance to (closestBox ?: error("some error occurred"))
        }

        private fun findDistance(
            o1: JunctionBox,
            o2: JunctionBox,
        ): Double {
            val (x1, y1, z1) = o1
            val (x2, y2, z2) = o2

            return sqrt((x2 - x1).pow(2) + (y2 - y1).pow(2) + (z2 - z1).pow(2))
        }
        
        data class Circuit(
            val boxes: List<JunctionBox>,
        )

        data class JunctionBox(
            val x: Double,
            val y: Double,
            val z: Double,
        )

        private fun parse(line: String): JunctionBox {
            val split = line.split(',')
            return JunctionBox(
                x = split[0].toDouble(),
                y = split[1].toDouble(),
                z = split[2].toDouble(),
            )
        }
    }
}
