package lvl6

import java.io.File


fun main() {
    val dirs = arrayOf(Pair(-1, 0), Pair(0, 1), Pair(1, 0), Pair(0, -1))
    var dir = 0

    val obst = mutableSetOf<Pair<Int, Int>>()
    val vis = mutableSetOf<Pair<Int, Int>>()

    var pos: Pair<Int, Int>? = null

    var i  = 0
    var j = 0
    File("lvl6/input.txt").bufferedReader().forEachLine {
        j = it.length
        if (it.contains("^")) {
            pos = Pair(i, it.indexOf("^"))
        }

        it.forEachIndexed { index, c ->
            if (c == '#')
                obst.add(Pair(i, index))
        }
        i += 1
    }

    while (pos!!.first >= 0 && pos!!.second >= 0 && pos!!.first < i && pos!!.second < j) {
        vis.add(pos!!)
        while (true) {
            var newPos = Pair(pos!!.first + dirs[dir].first, pos!!.second + dirs[dir].second)
            if (obst.contains(newPos)) {
                dir = (dir + 1) % dirs.size
            } else {
                pos = newPos
                break
            }
        }
    }
    println(vis.size)
}