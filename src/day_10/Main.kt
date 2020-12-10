package day_10

import java.io.File

fun main() {
    var jolts = File("src/day_10/input.txt").readLines().map { it.toInt() }.toIntArray()

    //zero adapter
    jolts = jolts.plus(0)

    jolts.sort()

    //last adapter
    jolts = jolts.plus(jolts.last() + 3)

    val steps = jolts.asList().zipWithNext { a, b -> b - a }.groupingBy { it }.eachCount()
    println(steps[1]!! * steps[3]!!)

    //B
    val combinations = mutableMapOf<Int, Long>().withDefault { 0 }
    combinations[0] = 1

    for (jolt in jolts.drop(1)) {
        combinations[jolt] = combinations.getValue(jolt - 1) + combinations.getValue(jolt - 2) + combinations.getValue(jolt - 3)
    }

    println(combinations.getValue(jolts.last()))
}

