package day_4

import java.io.File

fun main() {
    val required = setOf("byr", "iyr", "eyr", "hgt", "hcl", "ecl", "pid")
    val list: MutableList<MutableMap<String, String>> = mutableListOf(mutableMapOf())
    File("src/day_4/input.txt").forEachLine { line ->
        if (line.trim().isEmpty()) {
            list.add(mutableMapOf())
        } else {
            val map = list.last()
            map.putAll(line.trim().split(" ").associate {
                val (field, value) = it.split(':')
                field to value
            })
        }

    }

    var validCount = 0
    for (e in list) {
        if (e.keys.containsAll(required)) {
            if (e["byr"]!!.toInt() !in 1920..2002) {
                continue
            }

            if (e["iyr"]!!.toInt() !in 2010..2020) {
                continue
            }

            if (e["eyr"]!!.toInt() !in 2020..2030) {
                continue
            }

            val height = e["hgt"]!!
            if(!height.matches(".*(cm|in)".toRegex())){
                continue
            }

            if (height.contains("cm")) {
                if (height.substringBefore("cm").toInt() !in 150..193) {
                    continue
                }
            }
            if (height.contains("in")) {
                if (height.substringBefore("in").toInt() !in 59..76) {
                    continue
                }
            }

            val hair = e["hcl"]!!
            if (!hair.startsWith("#") || !hair.substring(1).matches("([0-9]|[a-f]){6}".toRegex())) {
                continue
            }

            if (!listOf("amb", "blu", "brn", "gry", "grn", "hzl", "oth").contains(e["ecl"])) {
                continue
            }

            if (!e["pid"]!!.matches("[0-9]{9}".toRegex())) {
                continue
            }

            validCount++
        }
    }

    println(validCount)
}