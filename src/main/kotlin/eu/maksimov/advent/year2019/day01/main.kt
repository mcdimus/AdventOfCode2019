package eu.maksimov.advent.year2019.day01

import eu.maksimov.advent.year2019.readResourceLines

fun main() {
    val modules = readResourceLines("day1_input")
        .filter { it.isNotBlank() }
        .map { Module(it.toInt()) }

    println("What is the sum of the fuel requirements for all of the modules on your spacecraft?")
    println("Answer: ${modules.map { it.getFuelRequirement() }.sum()}")

    println("What is the sum of the fuel requirements for all of the modules on your spacecraft when also taking into account the mass of the added fuel?")
    println("Answer: ${modules.map { it.getCompleteFuelRequirement() }.sum()}")
}
