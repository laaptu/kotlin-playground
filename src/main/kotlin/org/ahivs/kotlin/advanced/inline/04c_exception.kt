package org.ahivs.kotlin.advanced.inline

inline fun doSomeOperation(fn1: () -> Unit) {
    println("doSomeOperation start")
    fn1()
    throw Exception("Throwing exception for fun")
    println("do Some Operation end which will never be called due to exception above ")
}

fun main() {
    doSomeOperation {
        println("This is function1")
    }
}
