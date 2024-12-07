package lvl1

import java.io.File
import java.io.InputStream

fun main() {
    val inputStream: InputStream = File("lvl2/input.txt").inputStream()
    val l = mutableListOf<Int>()
    val r = mutableListOf<Int>()
    inputStream.bufferedReader().forEachLine {
        if (it.isNotEmpty()) {
            val lr = it.split("\\s+".toRegex())
            l.add(lr[0].toInt())
            r.add(lr[1].toInt())
        }
    }
    val occurences = r.groupingBy { it }.eachCount()
    var ans = 0
    l.forEach { ans += it * occurences.getOrDefault(it, 0) }
    println(ans)
}