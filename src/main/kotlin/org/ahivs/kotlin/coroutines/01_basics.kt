package org.ahivs.kotlin.coroutines.basics

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch


fun process(v: Int): Int {
    println("process thread = ${Thread.currentThread()}")
    return v
}

suspend fun requestToken(): String {
    return "hello"
}

fun main() {
    runOnNormalThread()
    runOnWorkerThread()
    //cannot call request token normally
    //                                                                                                                                                                                                                                                                                                                                                                                               requestToken()
    GlobalScope.launch {
        requestToken()
    }

}

fun runOnNormalThread() {
    println(Thread.currentThread())
    process(20)
}

fun runOnWorkerThread() {
    println("###------${Thread.currentThread()}")
    GlobalScope.launch {
        process(20)
    }
}
