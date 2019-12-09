package eu.maksimov.advent.year2019.day02

import java.util.*

data class Computer(
    private val initialMemory: IntArray,
    var inputs: Deque<Int> = ArrayDeque<Int>()
) {

    var output: Deque<Int> = ArrayDeque<Int>()
    var done = false

    var currentMemory = initialMemory.copyOf()
        private set

    fun setNoun(value: Int) {
        currentMemory[1] = value
    }

    fun setVerb(value: Int) {
        currentMemory[2] = value
    }

    fun run() {
        var currentInstruction = Instruction.of(this, 0)
        while (currentInstruction.apply(this)) {
            currentInstruction = Instruction.of(this, currentInstruction.nextInstructionAddress())
        }
        done = true
    }

    fun reset() {
        currentMemory = initialMemory.copyOf()
        inputs = ArrayDeque<Int>()
        output = ArrayDeque<Int>()
        done = false
    }

}
