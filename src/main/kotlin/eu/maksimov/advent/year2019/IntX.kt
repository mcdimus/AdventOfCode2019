package eu.maksimov.advent.year2019

import java.util.*
import kotlin.collections.ArrayList


fun Int.digits(): Deque<Int> {
    val stack: Deque<Int> = ArrayDeque<Int>()
    var number = this
    while (number > 0) {
        stack.push(number % 10)
        number /= 10
    }
    return stack
}

fun <T> Collection<T>.permutations(): List<List<T>> {
    val result = ArrayList<List<T>>()
    if (this.isEmpty()) {
        return result
    }

    permutations(this.toMutableList(), 0, this.size, result)
    return result
}

private fun <T> permutations(arr: MutableList<T>, loc: Int, len: Int, result: ArrayList<List<T>>) {
    if (loc == len) {
        result.add(ArrayList(arr))
        return
    }

    permutations(arr, loc + 1, len, result)
    for (i in loc + 1 until len) { // Swap the current arr[loc] to position i
        swap(arr, loc, i)
        permutations(arr, loc + 1, len, result)
        // Restore the status of arr to perform the next pick
        swap(arr, loc, i)
    }
}

private fun <T> swap(input: MutableList<T>, index1: Int, index2: Int) {
    val tmp: T = input[index1]
    input[index1] = input[index2]
    input[index2] = tmp
}
