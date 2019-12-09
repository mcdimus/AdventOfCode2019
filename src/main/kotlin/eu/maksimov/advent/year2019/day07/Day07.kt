package eu.maksimov.advent.year2019.day07

import eu.maksimov.advent.year2019.AdventOfCode
import eu.maksimov.advent.year2019.day02.Computer
import eu.maksimov.advent.year2019.permutations
import java.util.concurrent.Executors
import kotlin.time.ExperimentalTime

@ExperimentalTime
class Day07 : AdventOfCode(7) {

    private val executorService = Executors.newFixedThreadPool(5)
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
        var maxResult = Int.MIN_VALUE

        val permutations = listOf(5, 6, 7, 8, 9).permutations()

        for (permutation in permutations) {
            val amplifier1 = Computer(computer.currentMemory)
            val amplifier2 = Computer(computer.currentMemory)
            val amplifier3 = Computer(computer.currentMemory)
            val amplifier4 = Computer(computer.currentMemory)
            val amplifier5 = Computer(computer.currentMemory)

            executorService.submit { amplifier1.run() }
            executorService.submit { amplifier2.run() }
            executorService.submit { amplifier3.run() }
            executorService.submit { amplifier4.run() }
            executorService.submit { amplifier5.run() }

            amplifier1.inputs.offer(permutation[0])
            amplifier1.inputs.offer(0)

            amplifier2.inputs.offer(permutation[1])
            amplifier3.inputs.offer(permutation[2])
            amplifier4.inputs.offer(permutation[3])
            amplifier5.inputs.offer(permutation[4])

            while (!amplifier5.done) {
                if (amplifier5.output.isNotEmpty()) {
                    val pop = amplifier5.output.pop()
                    amplifier1.inputs.offer(pop)
                }
                if (amplifier1.output.isNotEmpty()) {
                    amplifier2.inputs.offer(amplifier1.output.pop())
                }
                if (amplifier2.output.isNotEmpty()) {
                    amplifier3.inputs.offer(amplifier2.output.pop())
                }
                if (amplifier3.output.isNotEmpty()) {
                    amplifier4.inputs.offer(amplifier3.output.pop())
                }
                if (amplifier4.output.isNotEmpty()) {
                    amplifier5.inputs.offer(amplifier4.output.pop())
                }

                Thread.sleep(1)
            }
            if (amplifier5.output.firstOrNull() ?: Int.MIN_VALUE > maxResult) {
                maxResult = amplifier5.output.first
            }
        }

        executorService.shutdownNow()
        return maxResult
    }
}

@ExperimentalTime
fun main() {
    Day07().run()
}
