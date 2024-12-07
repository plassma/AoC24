package lvl3

import java.io.File

fun main() {
    var ans = 0
    val outerRegex = "mul\\(\\d{1,3},\\d{1,3}\\)".toRegex()
    val innerRegex = "\\d+".toRegex()
    File("lvl3/input.txt").bufferedReader().forEachLine { input ->

        val valid = outerRegex.findAll(input)
        valid.forEach {
            val nums = innerRegex.findAll(it.value).map { it.value.toInt() }.toList()
            assert(nums.size == 2)
            ans += nums[0] * nums[1]
        }
    }
    println(ans) // 183380722
}