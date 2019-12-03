package eu.maksimov.advent.year2019.geometry

data class PointModifier(val direction: Direction, val delta: Int) {

    /**
     * Takes an argument like one of the following R75,D30,R83,U83,L12
     */
    constructor(value: String) : this(
        direction = Direction.of(value.take(1)),
        delta = value.drop(1).toInt()
    )

}
