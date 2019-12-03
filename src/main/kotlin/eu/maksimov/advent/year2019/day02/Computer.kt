package eu.maksimov.advent.year2019.day02

data class Computer(private val initialMemory: IntArray) {

    private var currentMemory = initialMemory.copyOf()

    fun setNoun(value: Int) {
        currentMemory[1] = value
    }

    fun setVerb(value: Int) {
        currentMemory[2] = value
    }

    fun run() {
        var currentIndex = 0
        var opCode = currentMemory[0]
        while (opCode != 99) {
            opCode = currentMemory[currentIndex]
            val targetIndex = currentMemory[currentIndex + 3]
            val param1Index = currentMemory[currentIndex + 2]
            val param2Index = currentMemory[currentIndex + 1]
            when (opCode) {
                1 -> currentMemory[targetIndex] = currentMemory[param1Index] + currentMemory[param2Index]
                2 -> currentMemory[targetIndex] = currentMemory[param1Index] * currentMemory[param2Index]
            }
            currentIndex += 4
        }
    }

    fun getCurrentMemory() = currentMemory

    fun reset() {
        currentMemory = initialMemory.copyOf()
    }

}
