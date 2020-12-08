package day_8

import java.io.File

fun main() {
    val instructions = File("src/day_8/input.txt").readLines().map {
        val split = it.split(" ")
        Pair(split[0], split[1].toInt())
    }.toTypedArray()

    //A
    println(runInstructions(instructions.clone()))

    //B
    loop@ for (i in instructions.indices) {
        val tempInstructions = instructions.clone()

        when (tempInstructions[i].first) {
            "jmp" -> tempInstructions[i] = Pair("nop", tempInstructions[i].second)
            "nop" -> tempInstructions[i] = Pair("jmp", tempInstructions[i].second)
            else -> continue@loop
        }

        runInstructions(tempInstructions).also { if (it.first == "eof") println(it) }
    }

}

fun runInstructions(instructions: Array<Pair<String, Int>>): Pair<String, Int> {
    var acc = 0
    var idx = 0
    while (instructions[idx].first != "visited") {
        val instruction = instructions[idx]
        val oldIdx = idx
        when (instruction.first) {
            "acc" -> {
                acc += instruction.second
                idx++
            }
            "jmp" -> {
                idx += instruction.second
            }
            else -> {
                idx++
            }
        }

        if (idx >= instructions.size) {
            return Pair("eof", acc)
        }

        instructions[oldIdx] = Pair("visited", 0)
    }
    return Pair("infinite_loop", acc)
}
