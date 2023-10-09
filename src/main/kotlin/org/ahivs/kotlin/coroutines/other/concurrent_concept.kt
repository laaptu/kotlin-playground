package org.ahivs.kotlin.coroutines.other

import kotlinx.coroutines.Dispatchers.Default
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun main() {
    //threadConcurrentExample()
    coroutineConcurrentExample()
}

fun threadConcurrentExample() {
    val thread1 = Thread { accessResourceThread("Thread1") }
    val thread2 = Thread { accessResourceThread("Thread2") }

    thread1.start()
    Thread.sleep(2000) // Stagger the start of thread2
    thread2.start()
}

fun accessResourceThread(threadName: String) {
    var localStatus = "Starting"
    println("$threadName: $localStatus")

    Thread.sleep(5000) // Simulate work with delay
    localStatus = "Checking cache"
    println("$threadName: $localStatus")

    Thread.sleep(3000) // Simulate more work with delay
    localStatus = "Loading item"
    println("$threadName: $localStatus")

    println("$threadName finished.")
}


fun coroutineConcurrentExample() = runBlocking {
    launch(Default) { accessResourceCoroutine("Coroutine1 - Default") }
    delay(2000) // Stagger the start of the second coroutine
    launch(IO) { accessResourceCoroutine("Coroutine2 - IO") }
}

suspend fun accessResourceCoroutine(coroutineName: String) {
    var localStatus = "Starting"
    println("$coroutineName: $localStatus [${Thread.currentThread().name}]")

    delay(5000) // Simulate work with non-blocking delay
    localStatus = "Checking cache"
    println("$coroutineName: $localStatus [${Thread.currentThread().name}]")

    delay(3000) // Simulate more work with non-blocking delay
    localStatus = "Loading item"
    println("$coroutineName: $localStatus [${Thread.currentThread().name}]")

    println("$coroutineName finished. [${Thread.currentThread().name}]")
}

