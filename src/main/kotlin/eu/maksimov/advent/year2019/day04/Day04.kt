package eu.maksimov.advent.year2019.day04

import eu.maksimov.advent.year2019.AdventOfCode
import eu.maksimov.advent.year2019.digits
import kotlin.time.ExperimentalTime

@ExperimentalTime
class Day04 : AdventOfCode(4) {

    lateinit var range: IntRange

    override fun parseInput() {
        val values = input[0].split('-').map { it.toInt() }
        range = IntRange(values[0], values[1])
    }

    /**
     * How many different passwords within the range given in your puzzle input meet these criteria?
     */
    override fun stage1(): Any {
        val numbers = mutableListOf<Int>()
        outer@ for (number in range) {
            var lastDigit = -1
            var hasDuplicatedAdjacentDigits = false
            for (digit in number.digits()) {
                if (digit < lastDigit) {
                    continue@outer
                }
                if (lastDigit == digit) {
                    hasDuplicatedAdjacentDigits = true
                }
                lastDigit = digit
            }
            if (hasDuplicatedAdjacentDigits) {
                numbers.add(number)
            }
        }
        return numbers.size
    }

    /**
     * How many different passwords within the range given in your puzzle input meet all of the criteria?
     */
    override fun stage2(): Any {
        val numbers = mutableListOf<Int>()
        outer@ for (number in range) {
            var lastDigit = -1
            val adjacentDuplicatedDigitsCounts = mutableMapOf<Int, Int>()
            for (digit in number.digits()) {
                if (digit < lastDigit) {
                    continue@outer
                }
                if (lastDigit == digit) {
                    adjacentDuplicatedDigitsCounts.compute(digit) { _, v ->
                        v?.let { it + 1 } ?: 2
                    }
                }
                lastDigit = digit
            }
            if (adjacentDuplicatedDigitsCounts.isNotEmpty() && adjacentDuplicatedDigitsCounts.values.any { it == 2 }) {
                numbers.add(number)
            }
        }
        return numbers.size
    }

}

@ExperimentalTime
fun main() {
    Day04().run()
}
