package task_1

import java.io.File

fun main() {
    val nums = File("src/task_1/input.txt").readLines().map { it.toInt() }.toIntArray()
    val targetNum = 2020

    for (i in nums.indices) {
        for (j in i until nums.size) {
            if (nums[i] + nums[j] == targetNum) {
                println(nums[i] * nums[j])
            }

            for (k in j until nums.size) {
                if (nums[i] + nums[j] + nums[k] == targetNum) {
                    println(nums[i] * nums[j] * nums[k])
                }
            }
        }
    }
}