package lvl5

import java.io.File


fun main() {
    val rules = ArrayList<Pair<Int, Int>>()
    val rulesMap = HashMap<Int, MutableSet<Int>>()
    var ans = 0

    var isUpdate = false
    File("lvl5/input.txt").bufferedReader().forEachLine {
        if (it.isEmpty()) {
            rules.forEach {
                if (rulesMap[it.second] == null)
                    rulesMap[it.second] = mutableSetOf()
                rulesMap[it.second]?.add(it.first)
            }
            isUpdate = true
            return@forEachLine
        }
        if (isUpdate) {
            val nums = it.split(",").map { it.toInt() }
            val remaining = nums.toMutableSet()
            var good = true
            run breaking@{
                nums.forEach {
                    remaining.remove(it)
                    rulesMap[it]?.forEach {
                        if (remaining.contains(it)) {
                            good = false
                            return@breaking
                        }
                    }
                }
            }
            if (good)
                ans += nums[nums.size / 2]
        } else {
            val nums = it.split("|").map { it.toInt() }
            assert(nums.size == 2)
            rules.add(Pair(nums[0], nums[1]))
        }

    }
    print(ans)
}
