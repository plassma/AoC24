package lvl2

import java.io.File
import java.io.InputStream
import kotlin.math.abs

fun main() {
    val inputStream: InputStream = File("lvl2/input.txt").inputStream()
    var ans = 0
    inputStream.bufferedReader().forEachLine {
        var dir = 0
        var prev = -1

        ans += 1
        run loop@{
            it.split("\\s+".toRegex()).forEach {
                val v = it.toInt()
                if (prev != -1) {
                    val diff = v - prev
                    if (diff == 0 || abs(diff) > 3) {
                        ans -= 1
                        return@loop
                    }

                    if (dir == 0)
                        dir = diff
                    else {
                        if (dir > 0 != diff > 0 || abs(diff) > 3) {
                            ans -= 1
                            return@loop
                        }

                    }
                }
                prev = v
            }
        }
    }
    print(ans)
}