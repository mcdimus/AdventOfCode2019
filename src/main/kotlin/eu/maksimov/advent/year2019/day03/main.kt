package eu.maksimov.advent.year2019.day03

import eu.maksimov.advent.year2019.geometry.Point
import eu.maksimov.advent.year2019.readResourceLines

fun main() {
    val centralPoint = Point(0, 0)

    val wires = readResourceLines("day03_input")
        .filter { it.isNotBlank() }
        .map { Wire(centralPoint, it) }

    val (point, distance) = wires[0].findIntersections(wires[1]).findClosestTo(
        centralPoint
    )

    println("What is the Manhattan distance from the central port to the closest intersection?")
    println(distance)
}

fun Collection<Point>.findClosestTo(target: Point): Pair<Point, Int> {
    var minDistance = Int.MAX_VALUE
    var closestPoint: Point = target
    for (point in this) {
        val distance = point.distanceTo(target)
        if (distance < minDistance) {
            minDistance = distance
            closestPoint = point
        }
    }
    return closestPoint to minDistance
}

