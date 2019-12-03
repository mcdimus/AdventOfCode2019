package eu.maksimov.advent.year2019.day02

import eu.maksimov.advent.year2019.readResourceLines

fun main() {
    val program = readResourceLines("day2_input")
        .filter { it.isNotBlank() }
        .flatMap { it.split(',') }
        .map { it.toInt() }
        .toIntArray()

    val computer = Computer(program).apply {
        setNoun(12)
        setVerb(2)
    }
    computer.run()

    println("What value is left at position 0 after the program halts?")
    println(computer.getCurrentMemory()[0])

    computer.reset()

    var noun = 0
    var verb = 0
    var result: Int
    outer@ for (i in 0..99) {
        noun = i
        for (j in 0..99) {
            verb = j
            computer.setNoun(noun)
            computer.setVerb(verb)
            computer.run()
            result = computer.getCurrentMemory()[0]
            computer.reset()
            if (result == 19690720) {
                println("result = $result")
                break@outer
            }
        }
    }

    println("What is 100 * noun + verb?")
    println("100 * $noun + $verb = ${100 * noun + verb}")
}
