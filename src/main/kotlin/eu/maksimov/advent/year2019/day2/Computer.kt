package eu.maksimov.advent.year2019.day2

data class Computer(private val program: IntArray) {

    fun run() {
        var currentIndex = 0
        var opCode = program[0]
        while (opCode != 99) {
            opCode = program[currentIndex]
            when (opCode) {
                1 -> program[program[currentIndex + 3]] = program[program[currentIndex + 2]] + program[program[currentIndex + 1]]
                2 -> program[program[currentIndex + 3]] = program[program[currentIndex + 2]] * program[program[currentIndex + 1]]
            }
            currentIndex += 4
        }

    }

    fun getState() = program

}
