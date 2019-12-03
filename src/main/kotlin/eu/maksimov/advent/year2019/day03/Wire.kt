package eu.maksimov.advent.year2019.day03

import eu.maksimov.advent.year2019.geometry.Point
import eu.maksimov.advent.year2019.geometry.PointModifier

class Wire(centralPoint: Point, input: String) {

    private val pointsWithSteps = mutableMapOf<Point, Int>()

    init {
        val pointModifiers = input.split(',').map(::PointModifier)

        var currentPoint = centralPoint
        var steps = 0
        for (pointModifier in pointModifiers) {
            for (i in 0 until pointModifier.delta) {
                val nextPoint = currentPoint.apply(pointModifier.direction)
                pointsWithSteps.putIfAbsent(nextPoint, ++steps)
                currentPoint = nextPoint
            }
        }
    }

    fun findIntersections(other: Wire) = pointsWithSteps.keys.filter { other.pointsWithSteps.containsKey(it) }

    fun findStepsToPoints(points: List<Point>): Map<Point, Int> {
        return pointsWithSteps.filterKeys(points::contains)
    }

}
