package lvl18

import java.io.File
import java.util.PriorityQueue

val dirs = arrayOf(Pair(-1, 0), Pair(0, 1), Pair(1, 0), Pair(0, -1))
val A = 71
val BYTES = 1024

fun isSolvable(map: Array<Array<Boolean>>): Boolean {
    val vis = Array(A) { Array(A) { Int.MAX_VALUE } }

    val q = PriorityQueue<Triple<Int, Int, Int>> { a, b -> a.first - b.first }

    q.add(Triple(0, 0, 0))

    while (q.size > 0) {
        val n = q.poll()
        if (n.first >= vis[n.second][n.third])
            continue
        vis[n.second][n.third] = n.first
        for (dir in dirs) {
            val np = Triple(n.first + 1, n.second + dir.first, n.third + dir.second)
            if (np.second in 0..<A && np.third in 0..<A && map[np.second][np.third] && vis[np.second][np.third] > np.first)
                q.add(np)
        }
    }
    return vis[A - 1][A - 1] != Int.MAX_VALUE
}

fun main() {

    val map = Array(A) { Array(A) { true } }

    for (readLine in File("lvl18/input.txt").readLines()) {
        val coords = "\\d+".toRegex().findAll(readLine).map { it.value.toInt() }.toList()
        map[coords[1]][coords[0]] = false
        if (!isSolvable(map)) {
            println(readLine)
            break
        }
    }
}