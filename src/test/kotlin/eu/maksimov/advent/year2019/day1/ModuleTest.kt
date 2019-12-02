package eu.maksimov.advent.year2019.day1

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class ModuleTest {

    @Test
    fun getFuelRequirement() {
        assertThat(Module(12).getFuelRequirement()).isEqualTo(2)
        assertThat(Module(14).getFuelRequirement()).isEqualTo(2)
        assertThat(Module(1969).getFuelRequirement()).isEqualTo(654)
        assertThat(Module(100756).getFuelRequirement()).isEqualTo(33583)
    }

    @Test
    fun getCompleteFuelRequirement() {
        assertThat(Module(14).getCompleteFuelRequirement()).isEqualTo(2)
        assertThat(Module(1969).getCompleteFuelRequirement()).isEqualTo(966)
        assertThat(Module(100756).getCompleteFuelRequirement()).isEqualTo(50346)
    }

}
