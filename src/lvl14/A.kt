package lvl14

import java.io.File

fun main() {
    val positions = mutableListOf<Pair<Int, Int>>()
    val vels = mutableListOf<Pair<Int, Int>>()

    File("lvl14/input.txt").bufferedReader().forEachLine {
        val coords = "(-*\\d+)".toRegex().findAll(it).map {it.value.toInt()}.toList()
        positions.add(Pair(coords[0], coords[1]))
        vels.add(Pair(coords[2], coords[3]))
    }

    val X = 101
    val Y = 103

    for (i in 0..<100) {
        for (j in 0..<positions.size) {
            val x = (positions[j].first + vels[j].first + X) %X
            val y = (positions[j].second + vels[j].second + Y) % Y
            positions[j] = Pair(x, y)
        }
    }
    val map = MutableList(Y) { MutableList(X) {0} }
    for (p in positions) {
        map[p.second][p.first] += 1
    }

    for (r in map)
        println(r)

    val quads = MutableList(4) {0}
    for (i in 0..<map.size) {
        for (j in 0..<map[0].size) {
            if (i == map.size / 2 || j == map[0].size / 2)
                continue
            val q = (if (i < map.size / 2) 0 else 2) + (if (j < map[0].size / 2) 0 else 1)
            quads[q] += map[i][j]
        }
    }

    println(quads.reduce {acc, i -> i * acc})
}