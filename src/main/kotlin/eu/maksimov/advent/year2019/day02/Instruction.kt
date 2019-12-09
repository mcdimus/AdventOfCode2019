package eu.maksimov.advent.year2019.day02

import eu.maksimov.advent.year2019.digits

sealed class Instruction(
    val address: Int,
    val opcode: Int,
    val modes: List<Mode> = emptyList()
) {

    enum class Mode {
        POSITION, IMMEDIATE
    }

    abstract fun apply(computer: Computer): Boolean

    abstract fun nextInstructionAddress(): Int

    fun getParamValue(paramId: Int, computer: Computer): Int {
        return if (modes.size >= paramId && modes[paramId - 1] == Mode.IMMEDIATE) {
            computer.currentMemory[address + paramId]
        } else {
            val paramIndex = computer.currentMemory[address + paramId]
            computer.currentMemory[paramIndex]
        }
    }

    companion object {
        fun of(computer: Computer, address: Int): Instruction {
            val instructionCode = computer.currentMemory[address]
            val opcode = instructionCode % 100
            val parameterModes = (instructionCode / 100).digits().reversed().map {
                if (it == 0) Mode.POSITION else Mode.IMMEDIATE
            }

            return when (opcode) {
                1 -> SumInstruction(address, parameterModes)
                2 -> MultiplyInstruction(address, parameterModes)
                3 -> InputInstruction(address, parameterModes)
                4 -> OutputInstruction(address, parameterModes)
                5 -> JumpIfTrueInstruction(address, parameterModes)
                6 -> JumpIfFalseInstruction(address, parameterModes)
                7 -> LessThenInstruction(address, parameterModes)
                8 -> EqualsInstruction(address, parameterModes)
                99 -> HaltInstruction(address, parameterModes)
                else -> throw IllegalStateException("Unsupported opcode: $opcode")
            }
        }
    }

}

class HaltInstruction(address: Int, parameterModes: List<Mode>) : Instruction(address, 99, parameterModes) {
    override fun apply(computer: Computer) = false
    override fun nextInstructionAddress() = address + 2
}

class MultiplyInstruction(address: Int, parameterModes: List<Mode>) : Instruction(address, 2, parameterModes) {
    override fun apply(computer: Computer): Boolean {
        val param1Value = getParamValue(1, computer)
        val param2Value = getParamValue(2, computer)
        val target = computer.currentMemory[address + 3]

        computer.currentMemory[target] = param1Value * param2Value
        return true
    }

    override fun nextInstructionAddress() = address + 4
}

class SumInstruction(address: Int, parameterModes: List<Mode>) : Instruction(address, 1, parameterModes) {
    override fun apply(computer: Computer): Boolean {
        val param1Value = getParamValue(1, computer)
        val param2Value = getParamValue(2, computer)
        val targetIndex = computer.currentMemory[address + 3]

        computer.currentMemory[targetIndex] = param1Value + param2Value
        return true
    }

    override fun nextInstructionAddress() = address + 4
}

class InputInstruction(address: Int, parameterModes: List<Mode>) : Instruction(address, 3, parameterModes) {
    override fun apply(computer: Computer): Boolean {
        while (computer.inputs.isEmpty()) {
            Thread.sleep(1)
        }
        val input = computer.inputs.poll()
        val targetIndex = computer.currentMemory[address + 1]

        computer.currentMemory[targetIndex] = input;
        return true
    }

    override fun nextInstructionAddress() = address + 2
}

class OutputInstruction(address: Int, parameterModes: List<Mode>) : Instruction(address, 4, parameterModes) {
    override fun apply(computer: Computer): Boolean {
        val param1Value = getParamValue(1, computer)
        computer.output.push(param1Value)
        return true
    }

    override fun nextInstructionAddress() = address + 2
}

class JumpIfTrueInstruction(address: Int, parameterModes: List<Mode>) : Instruction(address, 5, parameterModes) {

    private var nextInstructionAddress: Int = address + 3

    override fun apply(computer: Computer): Boolean {
        val param1Value = getParamValue(1, computer)
        if (param1Value > 0) {
            nextInstructionAddress = getParamValue(2, computer)
        }
        return true
    }

    override fun nextInstructionAddress() = nextInstructionAddress
}

class JumpIfFalseInstruction(address: Int, parameterModes: List<Mode>) : Instruction(address, 6, parameterModes) {

    private var nextInstructionAddress: Int = address + 3

    override fun apply(computer: Computer): Boolean {
        val param1Value = getParamValue(1, computer)
        if (param1Value == 0) {
            nextInstructionAddress = getParamValue(2, computer)
        }
        return true
    }

    override fun nextInstructionAddress() = nextInstructionAddress
}

class LessThenInstruction(address: Int, parameterModes: List<Mode>) : Instruction(address, 7, parameterModes) {
    override fun apply(computer: Computer): Boolean {
        val param1Value = getParamValue(1, computer)
        val param2Value = getParamValue(2, computer)
        val targetIndex = computer.currentMemory[address + 3]

        computer.currentMemory[targetIndex] = if (param1Value < param2Value) 1 else 0
        return true
    }

    override fun nextInstructionAddress() = address + 4
}

class EqualsInstruction(address: Int, parameterModes: List<Mode>) : Instruction(address, 8, parameterModes) {
    override fun apply(computer: Computer): Boolean {
        val param1Value = getParamValue(1, computer)
        val param2Value = getParamValue(2, computer)
        val targetIndex = computer.currentMemory[address + 3]

        computer.currentMemory[targetIndex] = if (param1Value == param2Value) 1 else 0
        return true
    }

    override fun nextInstructionAddress() = address + 4
}
