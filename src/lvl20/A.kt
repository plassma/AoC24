package lvl20


import java.io.File
import java.util.*
import kotlin.math.abs

val dirs = arrayOf(Pair(-1, 0), Pair(0, 1), Pair(1, 0), Pair(0, -1))

fun main() {
    val map = mutableListOf<MutableList<Char>>()
    var s = Pair(-1, -1)
    var e = Pair(-1, -1)
    File("lvl20/input.txt").readLines().forEach {
        if ('E' in it) {
            e = Pair(map.size, it.indexOf('E'))
        }
        if ('S' in it) {
            s = Pair(map.size, it.indexOf('S'))
        }
        map.add(it.toMutableList())
    }
    val vis = MutableList(map.size) { MutableList(map[0].size) { Int.MAX_VALUE } }
    val pq = PriorityQueue<Array<Int>> { a, b -> a[0] - b[0] }

    pq.add(arrayOf(0, 0, 1, s.first, s.second))

    var dist = -1
    while (pq.size > 0) {
        val n = pq.poll()

        if (n[3] == e.first && n[4] == e.second) {
            if (dist == -1) {
                dist = n[0]
                println(dist)
            }
        }
        if (vis[n[3]][n[4]] >= n[0]) {
            vis[n[3]][n[4]] = n[0]
            var dir = dirs.indexOf(Pair(n[1], n[2]))
            for (d in dirs.indices) {
                val dn = (dir + d) % 4
                val nxt = Pair(n[3] + dirs[dn].first, n[4] + dirs[dn].second)
                if (map[nxt.first][nxt.second] != '#' && vis[nxt.first][nxt.second] >= n[0] + 1)
                    pq.add(arrayOf(n[0] + 1, dirs[dn].first, dirs[dn].second, nxt.first, nxt.second))
            }
        }
    }
    val th = 100
    var ans = 0

    for (i in map.indices) {
        for (j in map[0].indices) {
            for (d in dirs) {
                val n = Pair(i + 2 * d.first, j + 2 * d.second)
                if (n.first in map.indices && n.second in map[0].indices) {
                    val a = vis[i][j]
                    val b = vis[n.first][n.second]
                    if (a != Int.MAX_VALUE && b != Int.MAX_VALUE && a - b >= th + 2 && vis[i + d.first][j+d.second] == Int.MAX_VALUE) {
                        ans += 1
                        map[i+d.first][j+d.second] = 'X'
                        map[n.first][n.second] = 'X'
                    }
                }
            }
        }
    }

    //println(vis.map {it.map {if (it == Int.MAX_VALUE) "%05d".format(-1) else "%05d".format(it)}}.joinToString("\n"))
    //println(map.joinToString ("\n"))
    println(ans)
}