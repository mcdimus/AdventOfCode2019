package eu.maksimov.advent.year2019

import java.time.Year
import kotlin.system.measureTimeMillis
import kotlin.time.ExperimentalTime
import kotlin.time.measureTimedValue

@ExperimentalTime
abstract class AdventOfCode(val day: Int, val year: Year = Year.of(2019)) {

    val input: List<String> = readResourceLines("day${day.toString().padStart(2, '0')}_input")

    abstract fun parseInput()
    abstract fun stage1(): Any
    abstract fun stage2(): Any

    fun run() {
        println("Advent of Code $year Day $day")
        println("=============================")
        println("-> Parsing")
        try {
            val parsingTime = measureTimeMillis {
                parseInput()
            }
            println("... done in $parsingTime ms\n")
        } catch (e: NotImplementedError) {
            println(e.message)
        }

        println("-> Stage 1")
        try {
            val stage1Result = measureTimedValue { stage1() }
            println("Answer: ${stage1Result.value}")
            println("Time Elapsed: ${stage1Result.duration}\n")
        } catch (e: NotImplementedError) {
            println(e.message)
        }

        println("-> Stage 2")
        try {
            val stage2Result = measureTimedValue { stage2() }
            println("Answer: ${stage2Result.value}")
            println("Time Elapsed: ${stage2Result.duration}")
        } catch (e: NotImplementedError) {
            println(e.message)
        }
    }

    private fun readResourceLines(filename: String) =
        ClassLoader.getSystemResource(filename).readText().split("\n")

}
