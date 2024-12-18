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
                pos = Pair(lvl.size, it.indexOf('@') * 2)
            }

            lvl.add(it.map { if (it == 'O') "[]" else (if (it == '@') "@." else "$it$it")}.joinToString("").toMutableList())
        } else {
            moves.addAll(it.toList())
        }
    }
    assert(pos.first != -1 && pos.second != -1)
    println(lvl.map {it.joinToString("")}.joinToString("\n"))
    for (m in moves) {
        println(m)
        var dir: Pair<Int, Int>
        if (m == '^')
            dir = Pair(-1, 0)
        else if (m == 'v')
            dir = Pair(1, 0)
        else if (m == '>')
            dir = Pair(0, 1)
        else
            dir = Pair(0, -1)

        val toMove = mutableSetOf<Triple<Int, Int, Char>>()
        var q = mutableSetOf(Pair(pos.first + dir.first, pos.second + dir.second))
        var canMove = lvl[pos.first + dir.first][pos.second + dir.second] != '#'
        while (q.size != 0 && canMove) {
            val eq = mutableSetOf<Pair<Int, Int>>() //extended queue
            for (el in q) { // expand []
                if (lvl[el.first][el.second] == '.')
                    continue
                if (lvl[el.first][el.second] == ']')
                    eq.add(Pair(el.first, el.second - 1))
                else if (lvl[el.first][el.second] == '[')
                    eq.add(Pair(el.first, el.second + 1))
                eq.add(el)
            }
            val nq = mutableSetOf<Pair<Int, Int>>() //next queue
            for (el in eq) {
                toMove.add(Triple(el.first, el.second, lvl[el.first][el.second]))
                if (lvl[el.first + dir.first][el.second + dir.second] == '#') {
                    canMove = false
                    break
                } else if (lvl[el.first + dir.first][el.second + dir.second] in "[]") {
                    nq.add(Pair(el.first + dir.first, el.second + dir.second))
                }
            }
            nq.removeAll(q)
            q = nq
        }

        if (canMove) {
            for (el in toMove) {
                lvl[el.first][el.second] = '.'
            }
            for (el in toMove) {
                lvl[el.first + dir.first][el.second + dir.second] = el.third
            }
            lvl[pos.first][pos.second] = '.'
            lvl[pos.first + dir.first][pos.second + dir.second] = '@'
            pos = Pair(pos.first + dir.first, pos.second + dir.second)
        }
        println(lvl.map {it.joinToString("")}.joinToString("\n"))
    }
    var ans = 0
    for (i in 0..<lvl.size) {
        for (j in 0..<lvl[0].size) {
            if (lvl[i][j] == '[')
                ans += 100 * i + j
        }
    }
    println(ans)
}