package eu.maksimov.advent.year2019.day02

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class ComputerTest {

    @Test
    fun run() {
        Computer(intArrayOf(1, 0, 0, 0, 99)).apply {
            run();
            assertThat(currentMemory).containsExactly(2, 0, 0, 0, 99)
        }

        Computer(intArrayOf(2, 3, 0, 3, 99)).apply {
            run();
            assertThat(currentMemory).containsExactly(2, 3, 0, 6, 99)
        }

        Computer(intArrayOf(2, 4, 4, 5, 99, 0)).apply {
            run();
            assertThat(currentMemory).containsExactly(2, 4, 4, 5, 99, 9801)
        }

        Computer(intArrayOf(1, 1, 1, 4, 99, 5, 6, 0, 99)).apply {
            run();
            assertThat(currentMemory).containsExactly(30, 1, 1, 4, 2, 5, 6, 0, 99)
        }

    }

}
