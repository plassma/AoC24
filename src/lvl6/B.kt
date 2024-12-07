package lvl6

import java.io.File

val dirs = arrayOf(Pair(-1, 0), Pair(0, 1), Pair(1, 0), Pair(0, -1))

fun createsLoop(start: Pair<Int, Int>, obst: Set<Pair<Int, Int>>, h: Int, w: Int): Boolean {
    var dir = 0
    var pos = start
    val vis = mutableSetOf<Triple<Int, Int, Int>>()
    while (pos.first >= 0 && pos.second >= 0 && pos.first < h && pos.second < w) {
        if (vis.contains(Triple(pos.first, pos.second, dir)))
            return true
        vis.add(Triple(pos.first, pos.second, dir))
        while (true) {
            val newPos = Pair(pos.first + dirs[dir].first, pos.second + dirs[dir].second)
            if (obst.contains(newPos)) {
                dir = (dir + 1) % dirs.size
            } else {
                pos = newPos
                break
            }
        }
    }
    return false
}

fun main() {
    val obst = mutableSetOf<Pair<Int, Int>>()

    var pos: Pair<Int, Int>? = null

    var h  = 0
    var w = 0
    File("lvl6/input.txt").bufferedReader().forEachLine {
        w = it.length
        if (it.contains("^")) {
            pos = Pair(h, it.indexOf("^"))
        }

        it.forEachIndexed { index, c ->
            if (c == '#')
                obst.add(Pair(h, index))
        }
        h += 1
    }
    var ans = 0
    for (i in 0..<h) {
        for (j in 0..<w) {
            val nObst = Pair(i, j)
            if (! obst.contains(nObst)) {
                obst.add(nObst)
                if (createsLoop(pos!!, obst, h, w))
                    ans += 1
                obst.remove(nObst)
            }
        }
    }
    print(ans)

}