package day_12

import java.io.File
import java.util.*
import kotlin.math.abs

data class Pos(var x: Int, var y: Int)

var nav = Pos(0, 0)
var waypoint = Pos(10, 1)
var dirs = mutableListOf('E', 'N', 'W', 'S')
var currDirection = 'E'

fun main() {
    File("src/day_12/input.txt").forEachLine {
        val direction = it[0]
        val value = it.substring(1).toInt()
        moveShip(direction, value)
    }

    println(abs(nav.x) + abs(nav.y))

    //B
    nav = Pos(0, 0)

    File("src/day_12/input.txt").forEachLine {
        val direction = it[0]
        val value = it.substring(1).toInt()
        moveWaypoint(direction, value)
    }

    println(abs(nav.x) + abs(nav.y))
}

fun moveShip(dir: Char, value: Int) {
    when (dir) {
        'L' -> {
            Collections.rotate(dirs, -value / 90)
            currDirection = dirs[0]
        }
        'R' -> {
            Collections.rotate(dirs, value / 90)
            currDirection = dirs[0]
        }
        'N' -> nav.y += value
        'S' -> nav.y -= value
        'E' -> nav.x += value
        'W' -> nav.x -= value
        'F' -> moveShip(currDirection, value)
    }
}

fun moveWaypoint(dir: Char, value: Int) {
    when (dir) {
        'L' -> for (i in 0 until value / 90) waypoint.y = waypoint.x.also { waypoint.x = -waypoint.y }
        'R' -> for (i in 0 until value / 90) waypoint.x = waypoint.y.also { waypoint.y = -waypoint.x }
        'N' -> waypoint.y += value
        'S' -> waypoint.y -= value
        'E' -> waypoint.x += value
        'W' -> waypoint.x -= value
        'F' -> moveWaypoint(value)
    }
}

fun moveWaypoint(value: Int) {
    nav.x += value * waypoint.x
    nav.y += value * waypoint.y
}
