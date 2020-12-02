package day_2

import java.io.File

fun main() {
    var validCount1 = 0
    var validCount2 = 0
    File("src/day_2/input.txt").forEachLine {
        val (rule, password) = it.split(':').map { e -> e.trim() }
        val (min, max) = rule.split(' ')[0].split('-').map { e -> e.toInt() }
        val char = rule.split(' ')[1][0]

        if (password.count { c -> c == char } in min..max) {
            validCount1++
        }

        val isValid = (password[min - 1] == char).xor(password[max - 1] == char)
        if (isValid) {
            validCount2++
        }
    }

    println(validCount1)
    println(validCount2)
}