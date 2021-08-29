package org.ahivs.kotlin

fun doSomeMath(add: (Int) -> Int) {
    add(20)
}

fun main() {
    doSomeMath {
        println(it * it)
        it * it
    }
}
