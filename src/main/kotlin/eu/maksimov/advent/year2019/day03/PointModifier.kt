package eu.maksimov.advent.year2019.day03

data class PointModifier(val direction: Direction, val delta: Int) {

    constructor(value: String) : this(
        direction = Direction.of(value.take(1)),
        delta = value.drop(1).toInt()
    )

}
