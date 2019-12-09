package eu.maksimov.advent.year2019.day07

import eu.maksimov.advent.year2019.AdventOfCode
import eu.maksimov.advent.year2019.day02.Computer
import eu.maksimov.advent.year2019.permutations
import kotlin.time.ExperimentalTime

@ExperimentalTime
class Day07 : AdventOfCode(7) {

    private lateinit var computer: Computer

    override fun parseInput() {
        val program = input.filter { it.isNotBlank() }
            .flatMap { it.split(',') }
            .map { it.toInt() }
            .toIntArray()

        computer = Computer(initialMemory = program)
    }

    override fun stage1(): Any {
        var maxResult = Int.MIN_VALUE

        val permutations = listOf(0, 1, 2, 3, 4).permutations()

        for (permutation in permutations) {
            computer.inputs.offer(permutation[0])
            computer.inputs.offer(0)
            computer.run()
            val result1 = computer.output.last().toInt()
            computer.reset()

            computer.inputs.offer(permutation[1])
            computer.inputs.offer(result1)
            computer.run()
            val result2 = computer.output.last().toInt()
            computer.reset()

            computer.inputs.offer(permutation[2])
            computer.inputs.offer(result2)
            computer.run()
            val result3 = computer.output.last().toInt()
            computer.reset()

            computer.inputs.offer(permutation[3])
            computer.inputs.offer(result3)
            computer.run()
            val result4 = computer.output.last().toInt()
            computer.reset()

            computer.inputs.offer(permutation[4])
            computer.inputs.offer(result4)
            computer.run()
            val result5 = computer.output.last().toInt()
            computer.reset()
            if (result5 > maxResult) {
                maxResult = result5
            }
        }

        return maxResult
    }

    override fun stage2(): Any {
        TODO("not implemented")
    }
}

@ExperimentalTime
fun main() {
    Day07().run()
}
