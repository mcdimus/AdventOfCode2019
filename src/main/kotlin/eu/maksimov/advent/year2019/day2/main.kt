package eu.maksimov.advent.year2019.day2

import eu.maksimov.advent.year2019.readResourceLines

fun main() {
    val program = readResourceLines("day2_input")
        .filter { it.isNotBlank() }
        .flatMap { it.split(',') }
        .map { it.toInt() }
        .toIntArray()

    program[1] = 12
    program[2] = 2

    val computer = Computer(program)
    computer.run()

    println("What value is left at position 0 after the program halts?")
    println(computer.getState()[0])
}
