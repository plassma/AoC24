package lvl17

import java.io.File
import kotlin.math.pow


fun main() {
    val f = File("lvl17/input.txt")
    val lines = f.readLines()

    var A = "\\d+".toRegex().findAll(lines[0]).map { it.value.toInt() }.toList()[0]
    var B = "\\d+".toRegex().findAll(lines[1]).map { it.value.toInt() }.toList()[0]
    var C = "\\d+".toRegex().findAll(lines[2]).map { it.value.toInt() }.toList()[0]
    var program = "\\d+".toRegex().findAll(lines[4]).map { it.value.toInt() }.toList()

    fun getComboOp(i: Int, program: List<Int>): Int{
        var combo = program[i+1]
        if (combo == 4)
            combo = A
        else if (combo == 5)
            combo = B
        else if (combo == 6)
            combo = C
        else
            assert(false)
        return combo
    }

    var i = 0
    val out = mutableListOf<Int>()
    while (i < program.size) {
        if (program[i] == 0) { // adv
            val combo = getComboOp(i, program)
            A = (A / 2.0.pow(combo.toDouble()).toInt())
            i += 2
        } else if(program[i] == 1) { //bxl
            B = B.xor(program[i+1]) % 8
            i += 2
        } else if (program[i] == 2) { //bst
            B = getComboOp(i, program) % 8
            i += 2
        } else if(program[i] == 3) { //jnz
            if (A != 0) {
                i = program[i+1]
            } else
                i += 2
        } else if(program[i] == 4) { //bxc
            B = B.xor(C) % 8
            i += 2
        } else if(program[i] == 5){// out
            out.add(getComboOp(i, program) % 8)
            i += 2
        } else if(program[i] == 6) {//bdv
            val combo = getComboOp(i, program)
            B = (A / 2.0.pow(combo.toDouble()).toInt())
            i += 2
        } else if(program[i] == 7) { //cdv
            val combo = getComboOp(i, program)
            C = (A / 2.0.pow(combo.toDouble()).toInt())
            i += 2
        }
    }
    println(out.joinToString(","))
}