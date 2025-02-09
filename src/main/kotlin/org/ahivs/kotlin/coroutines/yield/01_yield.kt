package org.ahivs.kotlin.coroutines.yield

import kotlinx.coroutines.*

fun yield1() = runBlocking {
    launch {
        repeat(3) {
            println("First Coroutine working... $it")
            yield()  // Giving a chance for other coroutines to run
        }
    }

    launch {
        delay(100)
        println("Second coroutine is running!")
    }

    launch {
        println("Third Coroutine is running!")
    }
}

fun yield2() = runBlocking {
    val job = launch {
        repeat(5) {
            println("Working $it...")
            delay(10)
            yield()  // Checks if the coroutine is cancelled before continuing
        }
    }

    delay(50)
    println("Cancelling coroutine...")
    job.cancelAndJoin()
    println("Coroutine stopped!")
}


fun main() {
    yield1()
}
