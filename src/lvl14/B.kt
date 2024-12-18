package lvl14

import java.awt.image.BufferedImage
import java.io.BufferedReader
import java.io.File
import javax.imageio.ImageIO

fun main() {
    val positions = mutableListOf<Pair<Int, Int>>()
    val vels = mutableListOf<Pair<Int, Int>>()

    File("lvl14/input.txt").bufferedReader().forEachLine {
        val coords = "(-*\\d+)".toRegex().findAll(it).map {it.value.toInt()}.toList()
        positions.add(Pair(coords[0], coords[1]))
        vels.add(Pair(coords[2], coords[3]))
    }

    val X = 101
    val Y = 103

    val configs = mutableSetOf<List<Pair<Int, Int>>>()

    for (i in 0..<7492) {
        for (j in 0..<positions.size) {
            val x = (positions[j].first + vels[j].first + X) %X
            val y = (positions[j].second + vels[j].second + Y) % Y
            positions[j] = Pair(x, y)
        }
        val current = positions.toList()
        if (current in configs) {
            println("repeated after $i !!!")
            break
        }
        configs.add(current)

        val map = MutableList(Y) { MutableList(X) {0} }
        for (p in positions) {
            map[p.second][p.first] += 1
        }
        val img = BufferedImage(X, Y, BufferedImage.TYPE_BYTE_GRAY)
        for (x in 0..<X) {
            for (y in 0..<Y)
                img.setRGB(x, y, if (map[y][x] != 0) 0 else 0xffffff)
        }
        ImageIO.write(img, "PNG", File("lvl14/img_$i.png"))
    }

}