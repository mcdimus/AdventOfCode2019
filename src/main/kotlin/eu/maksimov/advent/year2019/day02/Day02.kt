package eu.maksimov.advent.year2019.day02

import eu.maksimov.advent.year2019.AdventOfCode
import kotlin.time.ExperimentalTime

@ExperimentalTime
class Day02 : AdventOfCode(2) {

    private lateinit var computer: Computer

    override fun parseInput() {
        val program = input.filter { it.isNotBlank() }
            .flatMap { it.split(',') }
            .map { it.toInt() }
            .toIntArray()

        computer = Computer(initialMemory = program)
    }

    override fun stage1(): Any {
        computer.setNoun(12)
        computer.setVerb(2)

        computer.run()

        return computer.currentMemory[0]
    }

    override fun stage2(): Any {
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
                result = computer.currentMemory[0]
                computer.reset()
                if (result == 19690720) {
                    break@outer
                }
            }
        }
        return 100 * noun + verb
    }

}

@ExperimentalTime
fun main() {
    Day02().run()
}
