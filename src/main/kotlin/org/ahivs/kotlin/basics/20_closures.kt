package org.ahivs.kotlin.basics.closures

fun unaryOperation(n: Int, fn: (Int) -> Int) {
    fn(n)
}

fun main() {
    for (number in 1..10)
        unaryOperation(number) { n: Int ->
            println(n)
            n * 2
        }
}
