package eu.maksimov.advent.year2019.day05

import eu.maksimov.advent.year2019.AdventOfCode
import eu.maksimov.advent.year2019.day02.Computer
import kotlin.time.ExperimentalTime

@ExperimentalTime
class Day05 : AdventOfCode(5) {

    private lateinit var computer: Computer

    override fun parseInput() {
        val program = input.filter { it.isNotBlank() }
            .flatMap { it.split(',') }
            .map { it.toInt() }
            .toIntArray()

        computer = Computer(initialMemory = program)
    }

    override fun stage1(): Any {
        println("After providing 1 to the only input instruction and passing all the tests, what diagnostic code does the program produce?")
        computer.inputs.offer(1)
        computer.run()
        return computer.output.last().also {
            computer.reset()
        }
    }

    override fun stage2(): Any {
        println("What is the diagnostic code for system ID 5?")
        computer.inputs.offer(5)
        computer.run()
        return computer.output.last().also {
            computer.reset()
        }
    }

}

@ExperimentalTime
fun main() {
    Day05().run()
}
