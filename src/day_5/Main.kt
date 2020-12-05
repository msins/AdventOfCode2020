package day_5

import java.io.File

fun findRow(row: CharArray, from: Int = 0, to: Int = 127): Int {
    var low = from
    var high = to
    var idx = 0
    var mid = 0
    while (low <= high) {
        mid = low + (high - low) / 2
        if (low == high) return mid
        if (row[idx++] == 'F') {
            high = mid
        } else {
            low = mid + 1
        }
    }

    return mid
}

fun findSeat(seat: CharArray, from: Int = 0, to: Int = 7): Int {
    val converted = seat.map {
        if (it == 'R') 'B' else 'F'
    }
    return findRow(converted.toCharArray(), from, to)
}

fun main() {
    // A
    val maxId = File("src/day_5/input.txt").readLines().map {
        val row = findRow(it.substring(0, 7).toCharArray())
        val seat = findSeat(it.substring(7).toCharArray())
        row * 8 + seat
    }.max()
    println(maxId)

    // B
    val takenSeats = mutableListOf<Int>()
    File("src/day_5/input.txt").forEachLine {
        val row = findRow(it.substring(0, 7).toCharArray())
        val seat = findSeat(it.substring(7).toCharArray())
        takenSeats.add(row * 8 + seat)
    }

    takenSeats.sort()
    val allSeats = takenSeats.first()..takenSeats.last()
    println(allSeats.minus(takenSeats))
}
