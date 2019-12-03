package eu.maksimov.advent.year2019.geometry

import kotlin.math.abs

data class Point(val x: Int, val y: Int) {

    fun apply(modifier: PointModifier) = when (modifier.direction) {
        Direction.UP -> Point(x, y + modifier.delta)
        Direction.DOWN -> Point(x, y - modifier.delta)
        Direction.LEFT -> Point(x - modifier.delta, y)
        Direction.RIGHT -> Point(x + modifier.delta, y)
    }

    fun apply(direction: Direction) = Point(x + direction.dx, y + direction.dy)

    fun distanceTo(other: Point) = abs(this.x - other.x) + abs(this.y - other.y)

    operator fun minus(other: Point) =
        Point(this.x - other.x, this.y - other.y)

    operator fun times(other: Point) = this.x * other.x + this.y * other.y

}
