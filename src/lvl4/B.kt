package lvl4

import java.io.File


fun searchX(input: ArrayList<String>, x: Int, y: Int): Int {
    if (x < 1 || y < 1 || x > input[0].length - 2 || y > input.size - 2)
        return 0
    if (input[y][x] == 'A' && (
                (input[y + 1][x + 1] == 'S' && input[y - 1][x + 1] == 'S' && input[y - 1][x - 1] == 'M' && input[y + 1][x - 1] == 'M') ||
                (input[y + 1][x + 1] == 'M' && input[y - 1][x + 1] == 'M' && input[y - 1][x - 1] == 'S' && input[y + 1][x - 1] == 'S') ||
                (input[y + 1][x + 1] == 'M' && input[y - 1][x + 1] == 'S' && input[y - 1][x - 1] == 'S' && input[y + 1][x - 1] == 'M') ||
                (input[y + 1][x + 1] == 'S' && input[y - 1][x + 1] == 'M' && input[y - 1][x - 1] == 'M' && input[y + 1][x - 1] == 'S'))
    )
        return 1
    return 0
}

fun main() {
    val input = ArrayList<String>()

    File("lvl4/input.txt").bufferedReader().forEachLine { input.add(it) }
    var ans = 0

    for (y in 0..<input.size) {
        for (x in 0..<input[0].length) {
            ans += searchX(input, x, y)
        }
    }
    print(ans)

}