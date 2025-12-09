package org.example

import java.nio.file.Path
import kotlin.io.path.readLines
import kotlin.io.path.readText

fun readInputLines(path: String): List<String> = Path.of(path).readLines()
fun readInput(path: String): String = Path.of(path).readText()

fun readDay1() = readInputLines(System.getenv("DAY_1_INPUT_PATH"))
fun readDay2() = readInput(System.getenv("DAY_2_INPUT_PATH")).trim()
fun readDay3() = readInputLines(System.getenv("DAY_3_INPUT_PATH"))
fun readDay4() = readInput(System.getenv("DAY_4_INPUT_PATH"))
fun readDay5() = readInputLines(System.getenv("DAY_5_INPUT_PATH"))
fun readDay6() = readInput(System.getenv("DAY_6_INPUT_PATH"))
fun readDay7() = readInputLines(System.getenv("DAY_7_INPUT_PATH"))
fun readDay8() = readInputLines(System.getenv("DAY_8_INPUT_PATH"))
fun readDay9() = readInputLines(System.getenv("DAY_9_INPUT_PATH"))
