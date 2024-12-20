package lvl20


import java.io.File
import java.util.*
import kotlin.math.abs


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

    fun cheat(y: Int, x: Int): Int {
        if (map[y][x] == '#')
            return 0
        val v = MutableList(map.size) { MutableList(map[0].size) { Int.MAX_VALUE } }
        val q = PriorityQueue<Triple<Int, Int, Int>> { a, b -> b.third - a.third }
        for (d in dirs) {
            if (y + d.first in map.indices && x + d.second in map[0].indices) {
                q.add(Triple(y + d.first, x + d.second, 1))
            }
        }
        val targets = mutableSetOf<Pair<Int, Int>>()
        while (!q.isEmpty()) {
            val n = q.poll()
            if (n.third >= v[n.first][n.second])
                continue
            v[n.first][n.second] = n.third

            if (n.third == 20 || map[n.first][n.second] != '#') {
                if (map[n.first][n.second] != '#' && vis[n.first][n.second] - vis[y][x] >= th + n.third) {
                    println("$x $y, ${n.first}, ${n.second}, ${vis[n.first][n.second] - vis[y][x] - n.third}")
                    targets.add(Pair(n.first, n.second))
                }
            }
            if (n.third == 20)
                continue
            for (d in dirs) {
                val n = Triple(n.first + d.first, n.second + d.second, n.third + 1)
                if (n.first in map.indices && n.second in map[0].indices && n.third <= v[n.first][n.second]) {
                    q.add(n)
                }
            }
        }
        return targets.size
    }

    var ans = 0
    for (i in map.indices) {
        for (j in map[0].indices) {
            ans += cheat(i, j)
        }
    }

    println(vis.map {it.map {if (it == Int.MAX_VALUE) "%02d".format(-1) else "%02d".format(it)}}.joinToString("\n"))
    println(map.joinToString ("\n"))
    println(ans)
}