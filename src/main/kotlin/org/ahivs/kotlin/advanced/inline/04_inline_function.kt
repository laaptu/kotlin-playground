package org.ahivs.kotlin.advanced.inline

inline fun withInline(op: () -> Unit) {
    println("NO INLINE")
    println("Before some operation")
    op()
    println("After some operation")
}


inline fun withInline1(op: () -> Unit, noinline op1: () -> Int) {
    println("With Inline 1")
    println("Op() before")
    op()
    println("Op() finished")
    println("op1() before")
    op1()
    println("op1() finished")
}


fun main() {
    withInline {
        println("This is the main operation")
    }
}

fun anotherMethod() {
    withInline {
        println("This is another method")
    }
}

