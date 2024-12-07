package lvl2

import java.io.File
import java.io.InputStream
import kotlin.math.abs

fun checkReport(report: List<Int>, i: Int = 0, canSkip: Boolean = true, dir: Int = 0): Boolean {
    if (i == report.size - 1)
        return true
    val diff = report[i] - report[i + 1]

    if (diff == 0 || abs(diff) > 3 || (dir != 0 && (dir > 0 != diff > 0))) {
        if (!canSkip)
            return false

        if (i == 0) {
            if (checkReport(report, i + 1, false, 0))
                return true
        } else {
            val newDiff = report[i-1] - report[i+1]
            if (abs(newDiff) <= 3 && newDiff != 0 && checkReport(report, i+1, false, newDiff))
                return true
        }
        if (i == report.size - 2)
            return true
        val newDiff = report[i] - report[i+2]
        return abs(newDiff) <= 3 && newDiff != 0 && (newDiff > 0 == dir > 0) && checkReport(report, i+2, false, newDiff)

    }

    if (i == 0 && checkReport(report, i+1, false, 0))
        return true

    return checkReport(report, i+1, canSkip, diff)
}

fun main() {
    val inputStream: InputStream = File("lvl2/input.txt").inputStream()
    var ans = 0

    val fehler = "24 21 22 24 25 26 27".split("\\s+".toRegex()).map {it.toInt()}
    print(checkReport(fehler))

    inputStream.bufferedReader().forEachLine {
        val line = it.split("\\s+".toRegex()).map { it.toInt() }
        ans += if (checkReport(line)) 1 else 0
    }
    print(ans)
}