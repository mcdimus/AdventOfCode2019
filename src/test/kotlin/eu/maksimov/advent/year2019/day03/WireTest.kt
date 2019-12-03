package eu.maksimov.advent.year2019.day03

import eu.maksimov.advent.year2019.geometry.Point
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import java.lang.Integer.sum

internal class WireTest {

    @Test
    fun findStepsToPoints() {
        val centralPoint = Point(0, 0)

        val wire1 = Wire(centralPoint, "R75,D30,R83,U83,L12,D49,R71,U7,L72")
        val wire2 = Wire(centralPoint, "U62,R66,U55,R34,D71,R55,D58,R83")

        val intersections = wire1.findIntersections(wire2)
        val wire1Steps = wire1.findStepsToPoints(intersections)
        val wire2Steps = wire2.findStepsToPoints(intersections)

        val result = wire1Steps.toMutableMap()
        wire2Steps.forEach { (k, v) ->
            result.merge(k, v, ::sum)
        }

        assertThat(result.minBy { it.value }!!.value).isEqualTo(610)
    }
}
