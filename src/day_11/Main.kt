package day_11

import java.io.File

lateinit var grid: Array<CharArray>

fun main() {
    grid = File("src/day_11/input.txt").readLines().map { it.toCharArray() }.toTypedArray()

    var hasChange = true
    while (hasChange) {
        val newGrid = grid.map { it.clone() }.toTypedArray()

        for (i in grid.indices) {
            for (j in grid[0].indices) {
                if (grid[i][j] == '.') continue
                if (isEmpty(i, j) && !anyAdjacentOccupied(i, j)) {
                    if (newGrid[i][j] == '#') continue
                    newGrid[i][j] = '#'
                    continue
                }
                if (isOccupied(i, j) && countAdjacentOccupied(i, j) >= 4) {
                    if (newGrid[i][j] == 'L') continue
                    newGrid[i][j] = 'L'
                    continue
                }
            }
        }


        hasChange = !(grid contentDeepEquals newGrid)
        grid = newGrid.map { it.clone() }.toTypedArray()
    }

    println(grid.flatMap { it.toList() }.count { it == '#' })

    //B
    grid = File("src/day_11/input.txt").readLines().map { it.toCharArray() }.toTypedArray()

    hasChange = true
    while (hasChange) {
        val newGrid = grid.map { it.clone() }.toTypedArray()

        for (i in grid.indices) {
            for (j in grid[0].indices) {
                if (grid[i][j] == '.') continue
                if (isEmpty(i, j) && !canSeeOccupiedSeats(i, j)) {
                    if (newGrid[i][j] == '#') continue
                    newGrid[i][j] = '#'
                    continue
                }
                if (isOccupied(i, j) && countVisibleOccupied(i, j) >= 5) {
                    if (newGrid[i][j] == 'L') continue
                    newGrid[i][j] = 'L'
                    continue
                }
            }
        }

        hasChange = !(grid contentDeepEquals newGrid)
        grid = newGrid.map { it.clone() }.toTypedArray()
    }

    println(grid.flatMap { it.toList() }.count { it == '#' })
}

fun canSeeOccupiedSeats(row: Int, col: Int): Boolean {
    //horizontal-right
    for (i in (col + 1) until grid[0].size) {
        if (isEmpty(row, i)) break
        if (isOccupied(row, i)) {
            return true
        }
    }

    //horizontal-left
    for (i in (col - 1) downTo 0) {
        if (isEmpty(row, i)) break
        if (isOccupied(row, i)) {
            return true
        }
    }

    //vertical-down
    for (i in (row + 1)..grid.size) {
        if (isEmpty(i, col)) break
        if (isOccupied(i, col)) {
            return true
        }
    }

    //vertical-up
    for (j in (row - 1) downTo 0) {
        if (isEmpty(j, col)) break
        if (isOccupied(j, col)) {
            return true
        }
    }

    //diagonal-down-right
    var j = col + 1
    for (i in (row + 1)..grid.size) {
        if (isEmpty(i, j)) break
        if (isOccupied(i, j++)) {
            return true
        }
    }

    //diagonal-down-left
    j = col - 1
    for (i in (row + 1)..grid.size) {
        if (isEmpty(i, j)) break
        if (isOccupied(i, j--)) {
            return true
        }
    }

    //diagonal-top-left
    j = col - 1
    for (i in (row - 1) downTo 0) {
        if (isEmpty(i, j)) break
        if (isOccupied(i, j--)) {
            return true
        }
    }

    //diagonal-top-right
    j = col + 1
    for (i in (row - 1) downTo 0) {
        if (isEmpty(i, j)) break
        if (isOccupied(i, j++)) {
            return true
        }
    }
    return false
}

fun countVisibleOccupied(row: Int, col: Int): Int {
    var res = 0
    //horizontal-right
    for (i in (col + 1) until grid[0].size) {
        if (isEmpty(row, i)) break
        if (isOccupied(row, i)) {
            res++
            break
        }
    }

    //horizontal-left
    for (i in (col - 1) downTo 0) {
        if (isEmpty(row, i)) break
        if (isOccupied(row, i)) {
            res++
            break
        }
    }

    //vertical-down
    for (i in (row + 1)..grid.size) {
        if (isEmpty(i, col)) break
        if (isOccupied(i, col)) {
            res++
            break
        }
    }

    //vertical-up
    for (j in (row - 1) downTo 0) {
        if (isEmpty(j, col)) break
        if (isOccupied(j, col)) {
            res++
            break
        }
    }

    //diagonal-down-right
    var j = col + 1
    for (i in (row + 1)..grid.size) {
        if (isEmpty(i, j)) break
        if (isOccupied(i, j++)) {
            res++
            break
        }
    }

    //diagonal-down-left
    j = col - 1
    for (i in (row + 1)..grid.size) {
        if (isEmpty(i, j)) break
        if (isOccupied(i, j--)) {
            res++
            break
        }
    }

    //diagonal-top-left
    j = col - 1
    for (i in (row - 1) downTo 0) {
        if (isEmpty(i, j)) break
        if (isOccupied(i, j--)) {
            res++
            break
        }
    }

    //diagonal-top-right
    j = col + 1
    for (i in (row - 1) downTo 0) {
        if (isEmpty(i, j)) break
        if (isOccupied(i, j++)) {
            res++
            break
        }
    }
    return res
}

fun anyAdjacentOccupied(row: Int, col: Int): Boolean {
    return isOccupied(row - 1, col) ||
            isOccupied(row - 1, col + 1) ||
            isOccupied(row - 1, col - 1) ||
            isOccupied(row + 1, col) ||
            isOccupied(row + 1, col - 1) ||
            isOccupied(row + 1, col + 1) ||
            isOccupied(row, col - 1) ||
            isOccupied(row, col + 1)
}

fun countAdjacentOccupied(x: Int, y: Int): Int {
    return listOf(
            isOccupied(x - 1, y),
            isOccupied(x - 1, y + 1),
            isOccupied(x - 1, y - 1),
            isOccupied(x + 1, y),
            isOccupied(x + 1, y - 1),
            isOccupied(x + 1, y + 1),
            isOccupied(x, y - 1),
            isOccupied(x, y + 1))
            .count { it }
}

fun isOccupied(row: Int, col: Int): Boolean {
    try {
        grid[row][col]
    } catch (e: ArrayIndexOutOfBoundsException) {
        return false
    }

    return grid[row][col] == '#'
}

fun isEmpty(row: Int, col: Int): Boolean {
    try {
        grid[row][col]
    } catch (e: ArrayIndexOutOfBoundsException) {
        return false
    }

    return grid[row][col] == 'L'
}
