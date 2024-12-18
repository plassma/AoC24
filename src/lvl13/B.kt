package lvl13

import java.io.File

fun main() {
    val reader = File("lvl13/input.txt").bufferedReader()
    var line = reader.readLine()
    var ans: Long = 0
    while (line != null) {
        val a = "\\d+".toRegex().findAll(line).map { it.value.toLong() }.toList()
        val b = "\\d+".toRegex().findAll(reader.readLine()).map { it.value.toLong() }.toList()
        val p = "\\d+".toRegex().findAll(reader.readLine()).map { it.value.toLong() + 10000000000000 }.toList()


        val ka = (p[1] * b[0] - p[0] * b[1]) / (a[1] * b[0] - b[1] * a[0])
        val kb = - (p[1] * a[0] - p[0] * a[1]) / (a[1] * b[0] - b[1] * a[0])

        if (a[0] * ka + b[0] * kb == p[0] && a[1] * ka + b[1] * kb == p[1])
            ans += ka * 3 + kb

        line = reader.readLine()
        if (line != null && line.isEmpty())
            line = reader.readLine()
    }
    println(ans)
}