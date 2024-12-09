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

    var l = 0
    var r = disk.size - 1

    while (true) {
        while (disk[l] != -1)
            l += 1
        while (disk[r] == -1)
            r -= 1
        if (l >= r)
            break
        disk[l] = disk[r].also  {disk[r] = disk[l]}
    }

    println(disk.mapIndexed { index, c ->
        if (c != -1)
            index * c.toString().toLong()
        else 0
    }.sum())
}