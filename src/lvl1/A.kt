package lvl1

import java.io.File
import java.io.InputStream
import kotlin.math.abs

fun main() {
    val inputStream: InputStream = File("lvl1/sample.txt").inputStream()
    val l = mutableListOf<Int>()
    val r = mutableListOf<Int>()
    inputStream.bufferedReader().forEachLine {
        if (it.isNotEmpty()) {
            val lr = it.split("\\s+".toRegex())
            l.add(lr[0].toInt())
            r.add(lr[1].toInt())
        }
    }

    var ans = 0
    l.zip(r).forEach { ans += abs(it.first - it.second) }
    println(ans)
}