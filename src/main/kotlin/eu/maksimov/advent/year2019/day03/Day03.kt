package eu.maksimov.advent.year2019.day03

import eu.maksimov.advent.year2019.AdventOfCode
import eu.maksimov.advent.year2019.geometry.Point
import kotlin.time.ExperimentalTime

@ExperimentalTime
class Day03 : AdventOfCode(3) {

    private val centralPoint = Point(0, 0)

    private lateinit var wires: List<Wire>
    private lateinit var intersections: List<Point>

    override fun parseInput() {
        wires = input
            .filter { it.isNotBlank() }
            .map { Wire(centralPoint, it) }
    }

    override fun stage1(): Any {
        intersections = wires[0].findIntersections(wires[1])
        return intersections.map { it.distanceTo(centralPoint) }.min() ?: -1
    }

    override fun stage2(): Any {
        val wire1Steps = wires[0].findStepsToPoints(intersections)
        val wire2Steps = wires[1].findStepsToPoints(intersections)

        val result = wire1Steps.toMutableMap()
        wire2Steps.forEach { (k, v) ->
            result.merge(k, v, Integer::sum)
        }
        return result.minBy { it.value }!!.value
    }

}

@ExperimentalTime
fun main() {
    Day03().run()
}
