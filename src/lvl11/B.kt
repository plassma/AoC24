package lvl11

import java.io.File

fun main() {
    var stones = File("lvl11/input.txt").bufferedReader().readLine().split(" ").groupingBy { it }.eachCount().mapValues { it.value.toLong() }

    for (i in 0..<75) {
        val newStones = HashMap<String, Long>()
        for (s in stones.keys) {
            if (s == "0")
                newStones["1"] = newStones.getOrDefault("1", 0) + stones[s]!!
            else if (s.length % 2 == 0) {
                val h1 = s.substring(0, s.length / 2)
                newStones[h1] = newStones.getOrDefault(h1, 0) + stones[s]!!
                val h2 = s.substring(s.length / 2).toInt().toString()
                newStones[h2] = newStones.getOrDefault(h2, 0) + stones[s]!!
            } else {
                val n = (s.toLong() * 2024).toString()
                newStones[n] = newStones.getOrDefault(n, 0) + stones[s]!!
            }
        }
        stones = newStones
    }
    println(stones.values.sum()) // > 547791280
}