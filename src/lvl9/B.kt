package lvl9

import java.io.File

fun main() {
    val input = File("lvl9/input.txt").bufferedReader().readLine()
    var id = 0
    var flag = false
    val disk = input.map {
        flag = !flag
        if (!flag)
            id += 1
        val r = it.toString().toInt()
        if (flag) MutableList(r) {id} else MutableList(r) {-1}
    }.flatten().toMutableList()

    var r = disk.size - 1

    while (r > 0) {
        while (disk[r] == -1 && r > 0)
            r -= 1
        val start = r
        while (disk[r] == disk[start] && r > 0)
            r -= 1
        val size = start - r

        var cont = 0
        var l = 0
        while ( l <= r) {
            if (disk[l] == -1)
                cont += 1
            else
                cont = 0
            if (cont == size)
                break
            l += 1
        }
        if (cont == size) {
            for (i in 0..<size) {
                disk[l-i] = disk[r+i+1]
                disk[r+i+1] = -1
            }
        }
    }

    println(disk.mapIndexed { index, c ->
        if (c != -1)
            index * c.toString().toLong()
        else 0
    }.sum())
}