package eu.maksimov.advent.year2019

import java.util.*

fun Int.digits(): Deque<Int> {
    val stack: Deque<Int> = ArrayDeque<Int>()
    var number = this
    while (number > 0) {
        stack.push(number % 10)
        number /= 10
    }
    return stack
}
