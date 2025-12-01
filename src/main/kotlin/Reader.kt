package org.example

import java.nio.file.Path
import kotlin.io.path.readLines

fun readInput(path: String): List<String> = Path.of(path).readLines()

fun readDay1() = readInput(System.getenv("DAY_1_INPUT_PATH"))
