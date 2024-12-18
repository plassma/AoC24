package lvl16

import java.io.File
import java.util.LinkedList
import java.util.PriorityQueue

val dirs = arrayOf(Pair(-1, 0), Pair(0, 1), Pair(1, 0), Pair(0, -1))

fun main() {
    val map = mutableListOf<MutableList<Char>>()
    var s = Pair(-1, -1)
    var e = Pair(-1, -1)
    File("lvl16/input.txt").readLines().forEach {
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
    var ans = 0
    while (pq.size > 0) {
        val n = pq.poll()

        if (n[3] == e.first && n[4] == e.second) {
            if (dist == -1) {
                dist = n[0]
                println(dist)
            }
            if (dist == n[0])
                ans += 1
        }
        if (vis[n[3]][n[4]] >= n[0]) {
            vis[n[3]][n[4]] = n[0]
            var dir = dirs.indexOf(Pair(n[1], n[2]))
            var nxt = Pair(n[3] + dirs[dir].first, n[4] + dirs[dir].second)
            if (map[nxt.first][nxt.second] != '#' && vis[nxt.first][nxt.second] >= n[0] + 1)
                pq.add(arrayOf(n[0] + 1, dirs[dir].first, dirs[dir].second, nxt.first, nxt.second))
            for (d in arrayOf(1, 3)) {
                val dn = (dir + d) % 4
                nxt = Pair(n[3] + dirs[dn].first, n[4] + dirs[dn].second)
                if (map[nxt.first][nxt.second] != '#' && vis[nxt.first][nxt.second] >= n[0] + 1001)
                    pq.add(arrayOf(n[0] + 1001, dirs[dn].first, dirs[dn].second, nxt.first, nxt.second))
            }
        }
    }

    println(vis.map {it.map {if (it == Int.MAX_VALUE) "%05d".format(-1) else "%05d".format(it)}}.joinToString("\n"))
    println(map.joinToString ("\n"))

    val q = LinkedList<Triple<Int, Int, Pair<Int, Int>>>()
    q.add(Triple(e.first, e.second, Pair(0, 0)))
    val spots = mutableSetOf<Pair<Int, Int>>()
    while (q.size > 0) {
        val m = q.poll()
        spots.add(Pair(m.first, m.second))
        val v = vis[m.first][m.second]
        for (d in dirs)  {
            val n = Pair(m.first + d.first, m.second + d.second)
            if (v > vis[n.first][n.second]) {
                if (n !in spots) {
                    map[n.first][n.second] = 'O'
                    q.add(Triple(n.first, n.second, d))
                }
            }
        }
        val skip = Pair(m.first + m.third.first * 2, m.second + m.third.second * 2)
        if ((vis[m.first + m.third.first][m.second + m.third.second] != Int.MAX_VALUE) && (skip.first in 0..<map.size) && (skip.second in 0..<map[0].size) && v == 2 + vis[skip.first][skip.second]) {
            if (skip !in spots) {
                map[skip.first][skip.second] = 'O'
                q.add(Triple(skip.first, skip.second, m.third))
            }
        }
    }

    println(spots.size)
    println(vis[e.first][e.second])
}