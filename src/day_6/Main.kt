package day_6

import java.io.File

fun main() {
    val groups: MutableList<MutableList<List<Char>>> = mutableListOf(mutableListOf())
    File("src/day_6/input.txt").forEachLine { line ->
        if (line.trim().isEmpty()) {
            groups.add(mutableListOf())
        } else {
            groups.last().add(line.toList())
        }
    }

    var result = groups.fold(0) { acc, group -> acc + group.flatten().distinct().count() }
    println(result)

    //B
    result = groups.fold(0) { acc, group ->
        val size = group.size
        acc + group.flatten().groupingBy { it }.eachCount().filter { entry -> entry.value == size }.count()
    }

    println(result)
}