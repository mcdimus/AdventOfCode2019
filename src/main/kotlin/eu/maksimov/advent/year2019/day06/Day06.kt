package eu.maksimov.advent.year2019.day06

import eu.maksimov.advent.year2019.AdventOfCode
import kotlin.time.ExperimentalTime

@ExperimentalTime
class Day06 : AdventOfCode(6) {

    val map = mutableMapOf<String, MutableList<String>>()

    override fun parseInput() {
        val firstOrbitsAroundSecond = input.filter { it.isNotBlank() }
            .map { orbitalRelationship -> orbitalRelationship.split(')').let { it[1] to it[0] } }

        firstOrbitsAroundSecond.forEach { pair ->
            map.compute(pair.first) { _, v -> (v ?: mutableListOf()).also { it.add(pair.second) } }
        }
    }

    override fun stage1(): Any {
        return map.map { countOrbits(it.key) }.sum()
    }

    fun countOrbits(obj: String): Int {
        var count = 0
        var currentObj = obj;
        while (map.containsKey(currentObj)) {
            count++
            currentObj = map[currentObj]?.get(0) ?: throw IllegalStateException(currentObj)
        }
        return count;
    }

    override fun stage2(): Any {
        TODO("not implemented")
    }
}

@ExperimentalTime
fun main() {
    Day06().run()
}
