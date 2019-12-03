package eu.maksimov.advent.year2019.day03

import eu.maksimov.advent.year2019.geometry.Point
import eu.maksimov.advent.year2019.readResourceLines

fun main() {
    val centralPoint = Point(0, 0)

    val wires = readResourceLines("day03_input")
        .filter { it.isNotBlank() }
        .map { Wire(centralPoint, it) }

    val intersections = wires[0].findIntersections(wires[1])

    val distance = intersections
        .map { it.distanceTo(centralPoint) }
        .min()

    println("What is the Manhattan distance from the central port to the closest intersection?")
    println(distance)

    val wire1Steps = wires[0].findStepsToPoints(intersections)
    val wire2Steps = wires[1].findStepsToPoints(intersections)

    val result = wire1Steps.toMutableMap()
    wire2Steps.forEach { (k, v) ->
        result.merge(k, v, Integer::sum)
    }
    val minSteps = result.minBy { it.value }!!.value

    println("What is the fewest combined steps the wires must take to reach an intersection?")
    println(minSteps)
}
