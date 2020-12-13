package day_13

import java.io.File
import kotlin.math.ceil

fun main() {
    val input = File("src/day_13/input.txt").readLines()
    val time = input[0].toLong()
    val idsFiltered = input[1].split(',').filter { it != "x" }.map { it.toLong() }

    val bus = idsFiltered.map { Pair(it, ceil(time.toDouble() / it) * it) }.minBy { it.second - time }
    println(bus!!.first * (bus.second - time))

    //B
    val ids = input[1].split(',').map { if (it == "x") 1L else it.toLong() }
    var inc = ids.first()
    var idx = 1
    var curr = inc
    while (idx < ids.size) {
        if ((curr + idx) % ids[idx] == 0L) inc *= ids[idx++]
        else curr += inc
    }
    println(curr)
}