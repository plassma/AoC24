package lvl11

import java.io.File

fun main() {
    var stones = File("lvl11/input.txt").bufferedReader().readLine().split(" ")
    for (i in 0..<25) {
        val newStones = mutableListOf<String>()
        for (s in stones) {
            if (s == "0")
                newStones.add("1")
            else if (s.length % 2 == 0) {
                newStones.add(s.substring(0, s.length / 2))
                newStones.add(s.substring(s.length / 2).toInt().toString())
            } else {
                newStones.add((s.toLong() * 2024).toString())
            }
        }
        stones = newStones
    }
    println(stones.size)
}