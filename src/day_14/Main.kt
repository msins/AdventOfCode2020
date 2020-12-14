package day_14

import java.io.File

fun main() {
    var maskOr = 0L
    var maskAnd = 0L
    val mem: HashMap<Int, Long> = hashMapOf()

    File("src/day_14/input.txt").forEachLine { line ->
        if (line.startsWith("mask")) {
            val mask = line.substringAfter("= ")
            maskOr = mask.replace('X', '0').toLong(2)
            maskAnd = mask.replace('X', '1').toLong(2)
        } else {
            val address = line.substringAfter('[').substringBefore(']').toInt()
            val value = line.substringAfter("= ").toLong()
            mem[address] = value and maskAnd or maskOr
        }
    }

    println(mem.values.sum())

    //B will add some day
}
