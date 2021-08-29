package org.ahivs.kotlin.advanced.inline

fun noInline(op: () -> Unit) {
    println("NO INLINE")
    println("Before some operation")
    op()
    println("After some operation")
}


fun main() {
    noInline {
        println("This is the main operation")
    }
}

