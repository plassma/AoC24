package lvl17

import java.io.File
import java.lang.Math.pow
import kotlin.math.pow

fun runProgram(_A: Long, _B: Long, _C: Long, program: List<Int>): List<Int> {
    var A = _A
    var B = _B
    var C = _C

    fun getComboOp(i: Int, program: List<Int>): Long{
        var combo = program[i+1].toLong()
        if (combo == 4.toLong())
            combo = A
        else if (combo == 5.toLong())
            combo = B
        else if (combo == 6.toLong())
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
            A = (A / 2.0.pow(combo.toDouble()).toLong())
            i += 2
        } else if(program[i] == 1) { //bxl
            B = B.xor(program[i+1].toLong()) % 8.toLong()
            i += 2
        } else if (program[i] == 2) { //bst
            B = getComboOp(i, program) % 8.toLong()
            i += 2
        } else if(program[i] == 3) { //jnz
            if (A != 0.toLong()) {
                i = program[i+1]
            } else
                i += 2
        } else if(program[i] == 4) { //bxc
            B = B.xor(C) % 8.toLong()
            i += 2
        } else if(program[i] == 5){// out
            out.add((getComboOp(i, program) % 8.toLong()).toInt())
            if (out.size == program.size)
                return out
            if (out[out.size -1].toUInt() != program[out.size - 1].toUInt())
                return out
            i += 2
        } else if(program[i] == 6) {//bdv
            val combo = getComboOp(i, program)
            B = (A / 2.0.pow(combo.toDouble()).toLong())
            i += 2
        } else if(program[i] == 7) { //cdv
            val combo = getComboOp(i, program)
            C = (A / 2.0.pow(combo.toDouble()).toLong())
            i += 2
        }
    }
    return out
}


fun main() {
    val f = File("lvl17/input.txt")
    val lines = f.readLines()

    //var A = "\\d+".toRegex().findAll(lines[0]).map { it.value.toLong() }.toList()[0]
    var B = "\\d+".toRegex().findAll(lines[1]).map { it.value.toLong() }.toList()[0]
    var C = "\\d+".toRegex().findAll(lines[2]).map { it.value.toLong() }.toList()[0]
    var program = "\\d+".toRegex().findAll(lines[4]).map { it.value.toInt() }.toList()

    fun findA(A: Long, B: Long, C: Long, pos: Int): Long {
        if (pos == -1)
            return A
        for (i in 0..<8) {
            val nxt = runProgram(A * 8 + i, B, C, program)[0]
            if (nxt == program[pos]) {
                val e = findA(A * 8 + i, B, C, pos - 1)
                if (e != 0.toLong())
                    return e
            }
        }
        return 0
    }

    println(findA(0, B, C, program.size -1))
}