package lvl15

import java.io.File

fun main() {
    val lvl = mutableListOf<MutableList<Char>>()
    var isMap = true
    var pos = Pair(-1, -1)
    var moves = mutableListOf<Char>()
    File("lvl15/input.txt").bufferedReader().forEachLine {
        if (it.isEmpty()) {
            isMap = false
            return@forEachLine
        }
        if (isMap) {
            if ('@' in it) {
                pos = Pair(lvl.size, it.indexOf('@'))
            }

            lvl.add(it.toMutableList())
        } else {
            moves.addAll(it.toList())
        }
    }
    assert(pos.first != -1 && pos.second != -1)

    for (m in moves) {
        var dir: Pair<Int, Int>
        if (m == '^')
             dir = Pair(-1, 0)
        else if (m == 'v')
            dir = Pair(1, 0)
        else if (m == '>')
            dir = Pair(0, 1)
        else
            dir = Pair(0, -1)

        var x = pos.second + dir.second
        var y = pos.first + dir.first

        while (lvl[y][x] != '#' && lvl[y][x] != '.') {
            x += dir.second
            y += dir.first
        }
        if (lvl[y][x] == '#')
            continue
        lvl[y][x] = 'O'
        lvl[pos.first][pos.second] = '.'
        lvl[pos.first +dir.first][pos.second + dir.second] = '@'
        pos = Pair(pos.first + dir.first, pos.second + dir.second)
    }
    var ans = 0
    for (i in 0..<lvl.size) {
        for (j in 0..<lvl[0].size) {
            if (lvl[i][j] == 'O')
                ans += 100* i + j
        }
    }
    println(ans)
}