package org.ahivs.kotlin.coroutines

import kotlinx.coroutines.*

fun unDispatchedTest() = runBlocking(Dispatchers.Default) {
    val job = launch(
        Dispatchers.IO,
        start = CoroutineStart.UNDISPATCHED
    ) {
        println("### launch 1 -> $coroutineContext ${Thread.currentThread().name}")
        yield()
        println("### launch 2 -> $coroutineContext ${Thread.currentThread().name}")
    }
    println("### runBlocking ->$coroutineContext  ${Thread.currentThread().name} ")
}

fun main() {
    //unDispatchedTest()
//    orderOfExecutionInRunBlocking()
    orderOfExecutionWithDelayInRunBlocking()
}

fun orderOfExecutionInRunBlocking() = runBlocking {
    launch { println("First task complete") }
    launch { println("Second task complete") }
    println("Third task completed")
}

fun orderOfExecutionWithDelayInRunBlocking() = runBlocking {
    launch {
        delay(200)
        println("First task complete")
    }
    launch {
        delay(100)
        println("Second task complete")
    }
    println("Third task completed")
}