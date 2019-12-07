package eu.maksimov.advent.year2019.day06

import eu.maksimov.advent.year2019.AdventOfCode
import java.util.*
import kotlin.time.ExperimentalTime

@ExperimentalTime
class Day06 : AdventOfCode(6) {

    private lateinit var map: Map<String, String>

    override fun parseInput() {
        map = input.filter { it.isNotBlank() }
            .map { orbitalRelationship -> orbitalRelationship.split(')').let { it[1] to it[0] } }
            .toMap()

    }

    override fun stage1(): Any {
        return map.map { countOrbits(it.key) }.sum()
    }

    private fun countOrbits(obj: String): Int {
        var count = 0
        var currentObj = obj;
        while (map.containsKey(currentObj)) {
            count++
            currentObj = map[currentObj]!!
        }
        return count;
    }

    override fun stage2(): Any {
        val startObj = map["YOU"]!!

        val currentPath = ArrayDeque<String>()
        currentPath.push(startObj)
        val visitedNodes = mutableSetOf<String>("YOU")
        find(currentPath, visitedNodes)
        return currentPath.size - 1
    }

    private fun find(
        currentPath: Deque<String>,
        visitedNodes: MutableSet<String>
    ): Boolean {
        val currentLocation = currentPath.first
        visitedNodes.add(currentLocation)

        val possibleWays = ArrayDeque<String>()
        val next1 = map[currentLocation]
        val next2 = map.entries.filter { it.value == currentLocation }.map { it.key }
        if (next1 == "SAN" || next2.contains("SAN")) {
            return true
        }
        if (next1 != null && !visitedNodes.contains(next1)) {
            possibleWays.add(next1)
        }
        possibleWays.addAll(next2.filter { !visitedNodes.contains(it) })
        if (possibleWays.isEmpty()) {
            currentPath.pop()
            return false
        }
        val possibleWay = possibleWays.pop()
        currentPath.push(possibleWay)
        val find = find(currentPath, visitedNodes)
        if (find) {
            return true
        } else {
            val pop = currentPath.pop()
            currentPath.push(pop)
            return find(currentPath, visitedNodes)
        }
    }
}

@ExperimentalTime
fun main() {
    Day06().run()
}
