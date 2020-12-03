package day_3

import java.io.File

fun main() {
    val grid = File("src/day_3/input.txt").readLines()
    val rowCount = grid.size
    val colCount = grid[0].length

    var currRow = 0
    var currCol = 0
    var treeCount = 0
    while (currRow < rowCount) {
        currCol += 3
        currRow += 1
        if (currRow >= rowCount) break
        if (grid[currRow][currCol % colCount] == '#') {
            treeCount++
        }
    }
    println(treeCount)

    //B
    val slopes = listOf(Pair(1, 1), Pair(3, 1), Pair(5, 1), Pair(7, 1), Pair(1, 2))

    var result = 1L
    for (slope in slopes) {
        currRow = 0
        currCol = 0
        treeCount = 0
        while (currRow <= rowCount) {
            currCol += slope.first
            currRow += slope.second
            if (currRow >= rowCount) break
            if (grid[currRow][currCol % colCount] == '#') {
                treeCount++
            }
        }

        result *= treeCount
    }

    println(result)
}