package lvl4

import java.io.File


fun search(input: ArrayList<String>, x: Int, y: Int, i: Int, dx: Int, dy: Int, word: String = "XMAS"): Int {
    if (i == word.length)
        return 1
    if (x >= input[0].length || x < 0)
        return 0
    if (y >= input.size || y < 0)
        return 0
    if (input[y][x] != word[i])
        return 0
    return search(input, x + dx, y + dy, i+1, dx, dy, word)
}

fun main() {
    val input = ArrayList<String>()

    File("lvl4/input.txt").bufferedReader().forEachLine { input.add(it) }
    var ans = 0

    for (y in 0..<input.size) {
        for (x in 0..<input[0].length) {
            ans += search(input, x, y, 0, 0, 1)
            ans += search(input, x, y, 0, 1, 0)
            ans += search(input, x, y, 0, 0, -1)
            ans += search(input, x, y, 0, -1, 0)
            ans += search(input, x, y, 0, 1, 1)
            ans += search(input, x, y, 0, -1, -1)
            ans += search(input, x, y, 0, 1, -1)
            ans += search(input, x, y, 0, -1, 1)
        }
    }
    print(ans)

}