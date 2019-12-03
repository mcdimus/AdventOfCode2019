package eu.maksimov.advent.year2019.day03

enum class Direction(private val codes: Set<String>) {
    UP(setOf("U", "UP")),
    DOWN(setOf("D", "DOWN")),
    LEFT(setOf("L", "LEFT")),
    RIGHT(setOf("R", "RIGHT"));

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
