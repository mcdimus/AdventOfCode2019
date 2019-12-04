package eu.maksimov.advent.year2019.day01

import eu.maksimov.advent.year2019.AdventOfCode
import kotlin.time.ExperimentalTime

@ExperimentalTime
class Day01 : AdventOfCode(1) {

    private lateinit var modules: List<Module>

    override fun parseInput() {
        modules = input.filter { it.isNotBlank() }
            .map { Module(it.toInt()) }
    }

    override fun stage1() = modules.map { it.getFuelRequirement() }.sum().toString()

    override fun stage2() = modules.map { it.getCompleteFuelRequirement() }.sum().toString()

}

@ExperimentalTime
fun main() {
    Day01().run()
}
