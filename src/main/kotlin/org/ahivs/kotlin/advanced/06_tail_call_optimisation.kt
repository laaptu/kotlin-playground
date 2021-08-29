package org.ahivs.kotlin.advanced

fun sumHeadRec(index: Int, arr: Array<Int>): Int {
    if (index == arr.size)
        return 0
    val curr = arr[index]
    return curr + sumHeadRec(index + 1, arr)
}

tailrec fun sumTailRec(index: Int, currSum: Int, arr: Array<Int>): Int {
    if (index == arr.size)
        return currSum
    return sumTailRec(index + 1, currSum + arr[index], arr)
}

fun main() {
    println(sumHeadRec(0, arrayOf(1, 2, 3)))
}
