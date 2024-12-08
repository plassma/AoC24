package lvl8

import java.io.File


fun main() {
    val antinodes = mutableSetOf<Pair<Int, Int>>()
    val nodes = mutableMapOf<Char, ArrayList<Pair<Int, Int>>>()
    val input = mutableListOf<StringBuilder>()
    var ln = 0
    var col = 0
    File("lvl8/input.txt").bufferedReader().forEachLine {
        it.forEachIndexed { i, e ->
            if (e != '.') {
                val n = Pair(ln, i)
                if (nodes[e] == null) {
                    nodes[e] = arrayListOf(n)
                } else {
                    nodes[e]!!.add(n)
                }
            }
        }
        input.add(StringBuilder(it))

        col = it.length
        ln += 1
    }

    for (c in nodes.keys) {
        //println(nodes[c]!!.size)
        for (i in 0..<nodes[c]!!.size) {
            for (j in 0..<nodes[c]!!.size) {
                if (i != j) {
                    var flag = true
                    var d = 1
                    while (flag) {
                        val an = Pair(
                            nodes[c]!![i].first + d * (nodes[c]!![j].first - nodes[c]!![i].first),
                            nodes[c]!![i].second + d * (nodes[c]!![j].second - nodes[c]!![i].second)
                        )
                        if (an.first in 0..<ln && an.second in 0..<col) {
                            antinodes.add(an)
                            d += 1
                        } else
                            flag = false
                    }
                }
            }
        }
    }

    for (an in antinodes) {
        input[an.first][an.second] = '#'
    }
    for (line in input)
        println(line)

    println(antinodes.size) //> 899
}