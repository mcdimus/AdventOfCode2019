package eu.maksimov.advent.year2019.geometry

enum class Direction(val dx: Int, val dy: Int, private val codes: Set<String>) {

    UP(dx = 0, dy = 1, codes = setOf("U", "UP")),
    DOWN(dx = 0, dy = -1, codes = setOf("D", "DOWN")),
    LEFT(dx = -1, dy = 0, codes = setOf("L", "LEFT")),
    RIGHT(dx = 1, dy = 0, codes = setOf("R", "RIGHT"));

    companion object {
        fun of(value: String): Direction {
            for (direction in values()) {
                if (direction.codes.contains(value)) {
                    return direction
                }
            }
            throw IllegalArgumentException(value)
        }
    }

}
