package eu.maksimov.advent.year2019.day03

data class Line(val a: Point, val b: Point) {

    fun isHorizontal() = a.y == b.y

    fun findIntersection(other: Line): Point? {
        // works only with vertical and horizontal lines
        val theoreticalIntersection = if (this.isHorizontal() && !other.isHorizontal()) {
            Point(other.a.x, this.a.y)
        } else if (!this.isHorizontal() && other.isHorizontal()) {
            Point(this.a.x, other.a.y)
        } else {
            null
        }

        if (theoreticalIntersection == null) {
            return null // lines are parallel
        }

        return if (this.contains(theoreticalIntersection) && other.contains(theoreticalIntersection)) theoreticalIntersection else null
    }

    fun contains(point: Point): Boolean {
        return a.distanceTo(point) + b.distanceTo(point) == a.distanceTo(b)
    }

}
