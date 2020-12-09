package day_9

import java.io.File

const val preamble = 25

fun main() {
    val nums = File("src/day_9/input.txt").readLines().map { it.toLong() }.toLongArray()
    var invalidNum = 0L
    for (i in preamble until nums.size) {
        if (!isValid(i, nums)) {
            invalidNum = nums[i]
            break
        }
    }

    println(invalidNum)

    //B
    val range = findSum(invalidNum, nums)
    val max = nums.slice(range).max()
    val min = nums.slice(range).min()

    println(min!! + max!!)
}

fun findSum(targetSum: Long, nums: LongArray): IntRange {
    for (i in nums.indices) {
        var currSum = 0L

        if (nums[i] == targetSum) {
            continue
        }

        for (j in i until nums.size) {
            if (currSum == targetSum) {
                return IntRange(i, j - 1)
            }

            if (currSum > targetSum) {
                break
            }

            currSum += nums[j]
        }
    }

    throw IllegalArgumentException()
}

fun isValid(idx: Int, nums: LongArray): Boolean {

    for (i in idx - preamble until idx) {
        for (j in idx - preamble until idx) {
            if (nums[idx] == nums[i] + nums[j]) {
                return true
            }
        }
    }

    return false
}