package lvl12

import java.io.File

fun main() {
    val input = mutableListOf<MutableList<Char>>()
    File("lvl12/input.txt").bufferedReader().forEachLine {
        input.add(it.toMutableList())
    }
    var ans = 0
    for (i in 0..<input.size) {
        for (j in 0..<input[0].size) {
            if (input[i][j] != ' ') {
                val l = input[i][j]
                var a = 0
                var q = mutableListOf(Pair(i, j))
                val f = mutableMapOf<Pair<Char, Int>, MutableList<Int>>()
                var c = 0
                val vis = mutableSetOf<Pair<Int, Int>>()
                while (q.size > 0) {
                    val nq = mutableListOf<Pair<Int, Int>>()
                    for (p in q) {
                        if (p in vis)
                            continue
                        vis.add(p)
                        input[p.first][p.second] = ' '
                        a += 1
                        if (p.first > 0 && input[p.first - 1][p.second] == l)
                            nq.add(Pair(p.first - 1, p.second))
                        else {
                            if (Pair(p.first - 1, p.second) !in vis) {
                                c += 1
                                val t =  f.getOrDefault(Pair('D', p.first - 1), mutableListOf())
                                t.add(p.second)
                                f[Pair('D', p.first - 1)] = t
                            }

                        }
                        if (p.first < input.size - 1 && input[p.first + 1][p.second] == l)
                            nq.add(Pair(p.first + 1, p.second))
                        else {
                            if (Pair(p.first + 1, p.second) !in vis) {
                                c += 1
                                val t =  f.getOrDefault(Pair('U', p.first + 1), mutableListOf())
                                t.add(p.second)
                                f[Pair('U', p.first + 1)] = t
                            }
                        }
                        if (p.second > 0 && input[p.first][p.second - 1] == l)
                            nq.add(Pair(p.first, p.second - 1))
                        else {
                            if (Pair(p.first, p.second - 1) !in vis) {
                                val t =  f.getOrDefault(Pair('L', p.second - 1), mutableListOf())
                                t.add(p.first)
                                f[Pair('L', p.second - 1)] = t
                            }
                        }
                        if (p.second < input[0].size - 1 && input[p.first][p.second + 1] == l)
                            nq.add(Pair(p.first, p.second + 1))
                        else {
                            if (Pair(p.first, p.second + 1) !in vis) {
                                val t =  f.getOrDefault(Pair('R', p.second + 1), mutableListOf())
                                t.add(p.first)
                                f[Pair('R', p.second + 1)] = t
                            }
                        }
                    }
                    q = nq
                }
                var sides = 0

                for (s in f.values) {
                    s.sort()
                    var p = s[0] - 2
                    for (v in s){
                        if (p < v-1)
                            sides += 1
                        p = v
                    }
                }

                println("$l: $a * ${sides}")
                ans += a * sides
            }
        }
    }
    println(ans)
}