package eu.maksimov.advent.year2019.day02

sealed class Instruction(
    val address: Int,
    val opcode: Int,
    private val modes: List<Mode> = emptyList()
) {

    enum class Mode {
        POSITION, IMMEDIATE
    }

    abstract fun apply(computer: Computer): Boolean

    abstract fun nextInstructionAddress(): Int

    companion object {
        fun of(computer: Computer, address: Int): Instruction {
            val instructionCode = computer.currentMemory[address]

            return when (instructionCode) {
                1 -> SumInstruction(address)
                2 -> MultiplyInstruction(address)
                99 -> HaltInstruction(address)
                else -> throw IllegalStateException("Unsupported instructionCode: $instructionCode")
            }
        }
    }

}

class HaltInstruction(address: Int) : Instruction(address, 99) {
    override fun apply(computer: Computer) = false
    override fun nextInstructionAddress() = address + 1
}

class MultiplyInstruction(address: Int) : Instruction(address, 2) {
    override fun apply(computer: Computer): Boolean {
        val param1Index = computer.currentMemory[address + 1]
        val param2Index = computer.currentMemory[address + 2]
        val targetIndex = computer.currentMemory[address + 3]

        computer.currentMemory[targetIndex] = computer.currentMemory[param1Index] * computer.currentMemory[param2Index]
        return true
    }

    override fun nextInstructionAddress() = address + 4
}

class SumInstruction(address: Int) : Instruction(address, 1) {
    override fun apply(computer: Computer): Boolean {
        val param1Index = computer.currentMemory[address + 1]
        val param2Index = computer.currentMemory[address + 2]
        val targetIndex = computer.currentMemory[address + 3]

        computer.currentMemory[targetIndex] = computer.currentMemory[param1Index] + computer.currentMemory[param2Index]
        return true
    }

    override fun nextInstructionAddress() = address + 4
}
