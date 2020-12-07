package day_7

import java.io.File

val map = mutableMapOf<String, MutableList<Pair<String, Int>>>()
var colors = mutableListOf<String>()

fun main() {
    File("src/day_7/input.txt").forEachLine { line ->
        val color = line.substringBefore("bags").trim()
        colors.add(color)
        line.substringAfter("contain").removeSuffix(".").split(",").map { it.trim().substringBeforeLast(" bag") }.forEach {
            if (it == "no other") {
                map[color] = mutableListOf()
                return@forEach
            }

            val count = it.substringBefore(" ").toInt()
            val otherColor = it.substringAfter(" ")

            if (map[color].isNullOrEmpty()) map[color] = mutableListOf()

            map[color]!!.add(otherColor to count)
        }
    }

    colors.remove("shiny gold")
    println(colors.count { containsShiny(it) })

    //B
    println(countAllFrom("shiny gold"))
}

fun countAllFrom(color: String): Int {
    if (!map.containsKey(color)) return 0

    var sum = 0
    for (pair in map[color]!!) {
        sum += pair.second + pair.second * countAllFrom(pair.first)
    }

    return sum
}

fun containsShiny(color: String): Boolean {
    if (color.contains("shiny gold")) return true
    if (!map.containsKey(color)) return false

    for (pair in map[color]!!) {
        if (containsShiny(pair.first)) {
            return true
        }
    }

    return false
}