package eu.maksimov.advent.year2019.day03

class Wire(input: String) {

    private val verticalSections: MutableList<Line> = emptyList<Line>().toMutableList()
    private val horizontalSections: MutableList<Line> = emptyList<Line>().toMutableList()

    init {
        var currentPoint = Point(0, 0)
        val pointModifiers = input.split(',').map(::PointModifier)

        for (pointModifier in pointModifiers) {
            val nextPoint = currentPoint.apply(pointModifier)
            val line = Line(currentPoint, nextPoint)
            if (line.isHorizontal()) {
                horizontalSections.add(line)
            } else {
                verticalSections.add(line)
            }
            currentPoint = nextPoint
        }
    }

    fun findIntersections(other: Wire): List<Point> {
        val intersections = mutableListOf<Point>()
        for (verticalSection in verticalSections) {
            for (otherHorizontalSection in other.horizontalSections) {
                val intersection = verticalSection.findIntersection(otherHorizontalSection)
                intersection?.let(intersections::add)
            }
        }
        for (horizontalSection in horizontalSections) {
            for (otherVerticalSection in other.verticalSections) {
                val intersection = horizontalSection.findIntersection(otherVerticalSection)
                intersection?.let(intersections::add)
            }
        }
        return intersections.also {
            it.remove(Point(0, 0))
        }
    }

}
