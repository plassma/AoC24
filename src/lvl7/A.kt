package lvl7

import java.io.File


fun main() {
    var ans: Long = 0
    File("lvl7/input.txt").bufferedReader().forEachLine {
        val split = it.split(":")
        val target = split[0].toLong()
        val operands = split[1].substring(1).split("\\s+".toRegex()).map { it -> it.toLong()}
        var results = mutableSetOf(operands[0])

        for (i in 1..<operands.size) {
            val newResults = mutableSetOf<Long>()
            results.forEach {
                newResults.add(it * operands[i])
                newResults.add(it + operands[i])
            }
            results = newResults
        }
        if (results.contains(target))
            ans += target
    }

    println(ans)
}