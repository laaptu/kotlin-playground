package org.ahivs.kotlin.advanced.inline

inline fun ref1(fn1: () -> Unit) {
    println("Ref1 start")
    //val ref = fn1
    println("Ref2 start")
}


inline fun ref2(fn1: () -> Unit) {
    println("Ref2 start")
    fn1()
    println("Ref2 end")
}

fun main() {
    val fn1: () -> Unit = {
        println("Fn1 executed")
    }
    ref2(fn1)
}
