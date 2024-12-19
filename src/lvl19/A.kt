package lvl19

import java.io.File

fun main() {
    val lines = File("lvl19/input.txt").readLines()
    val allowed = lines[0].split(", ")
    val patterns = (2..<lines.size).map { lines[it] }

    var ans: Long = 0
    for (p in patterns) {
        val dp = Array(p.length) {0.toLong()}
        for (i in dp.indices) {
            for (a in allowed) {
                if (i - a.length < -1)
                    continue
                if (p.substring(i - a.length + 1, i + 1) == a)
                    dp[i] += if (i - a.length == -1) 1 else dp[i - a.length]
            }
        }
        ans += if (dp[dp.size - 1] > 0) 1 else 0
    }

    println(ans)

}