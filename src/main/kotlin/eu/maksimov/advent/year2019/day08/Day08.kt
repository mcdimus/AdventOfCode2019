package eu.maksimov.advent.year2019.day08

import eu.maksimov.advent.year2019.AdventOfCode
import kotlin.time.ExperimentalTime

@ExperimentalTime
class Day08 : AdventOfCode(8) {

    private val imageWidth = 25
    private val imageHeight = 6

    lateinit var layers: List<String>

    override fun parseInput() {
        layers = input.filter { it.isNotBlank() }
            .flatMap { it.chunked(imageHeight * imageWidth) }
    }

    override fun stage1(): Any {
        val layerWithMinZeros = layers.minBy { it.chars().filter { char -> char == '0'.toInt() }.count() }!!
        return layerWithMinZeros.filter { it == '1' }.count() * layerWithMinZeros.filter { it == '2' }.count()
    }

    override fun stage2(): Any {
        val image = Array(imageHeight) { CharArray(imageWidth) }
        image.forEachIndexed { rowIndex, row ->
            row.forEachIndexed { pixelIndex, _ ->
                val location = rowIndex * imageWidth + pixelIndex
                val value = layers.map { it[location] }.dropWhile { it == '2' }.first()
                image[rowIndex][pixelIndex] = if (value == '1') '@' else ' '
                print(image[rowIndex][pixelIndex])
            }
            println()
        }
        return "^^^ See Above ^^^"
    }
}

@ExperimentalTime
fun main() {
    Day08().run()
}
