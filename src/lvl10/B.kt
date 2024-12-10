package lvl10

import java.io.File

fun main() {
    val input = mutableListOf<List<Int>>()
    File("lvl10/input.txt").bufferedReader().forEachLine {
        input.add(it.map {it.toString().toInt()})
    }
    var ans = 0
    for (i in 0..<input.size) {
        for (j in 0..<input[0].size) {
            if (input[i][j] == 0) {
                var q = mutableListOf(Pair(i, j))
                while (q.size > 0) {
                    val nq = mutableListOf<Pair<Int, Int>>()
                    for (p in q) {
                        if (input[p.first][p.second] == 9){
                            ans += 1
                            continue
                        }
                        if (p.first - 1 >= 0 && input[p.first -1][p.second] == input[p.first][p.second] + 1)
                            nq.add(Pair(p.first -1, p.second))
                        if (p.second - 1 >= 0 && input[p.first][p.second - 1] == input[p.first][p.second] + 1)
                            nq.add(Pair(p.first, p.second - 1))
                        if (p.first + 1 < input.size && input[p.first +1][p.second] == input[p.first][p.second] + 1)
                            nq.add(Pair(p.first + 1, p.second))
                        if (p.second + 1 < input[0].size && input[p.first][p.second + 1] == input[p.first][p.second] + 1)
                            nq.add(Pair(p.first, p.second + 1))
                    }
                    q = nq
                }
            }
        }
    }
    println(ans)
}