package eu.maksimov.advent.year2019

fun readResourceLines(filename: String) =
    ClassLoader.getSystemResource(filename).readText().split("\n")
