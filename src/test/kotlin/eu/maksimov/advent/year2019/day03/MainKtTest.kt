package eu.maksimov.advent.year2019.day03

import eu.maksimov.advent.year2019.geometry.Point
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class MainKtTest {

    @Test
    internal fun test1() {
        val centralPoint = Point(0, 0)

        val wire1 = Wire(centralPoint, "R75,D30,R83,U83,L12,D49,R71,U7,L72")
        val wire2 = Wire(centralPoint, "U62,R66,U55,R34,D71,R55,D58,R83")

        val (point, distance) = wire1.findIntersections(wire2).findClosestTo(
            centralPoint
        )
        assertThat(distance).isEqualTo(159)
    }

    @Test
    internal fun test2() {
        val centralPoint = Point(0, 0)

        val wire1 = Wire(centralPoint, "R98,U47,R26,D63,R33,U87,L62,D20,R33,U53,R51")
        val wire2 = Wire(centralPoint, "U98,R91,D20,R16,D67,R40,U7,R15,U6,R7")

        val (point, distance) = wire1.findIntersections(wire2).findClosestTo(
            centralPoint
        )
        assertThat(distance).isEqualTo(135)
    }

}
