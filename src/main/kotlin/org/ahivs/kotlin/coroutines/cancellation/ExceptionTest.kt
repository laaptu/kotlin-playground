package org.ahivs.kotlin.coroutines.cancellation

import kotlinx.coroutines.*

fun main() = runBlocking {
    println("Starting program...")
    val exceptionHandler =
        CoroutineExceptionHandler { _, exception -> println("CoroutineExceptionHandler got $exception") }
    val scope = CoroutineScope(Dispatchers.Default + exceptionHandler)
    scope.launch {
        getDataFromServer()
    }

    delay(3000) // Give coroutines time to run
    println("Program finished.")
}

suspend fun CoroutineScope.getDataFromServer() {
    val deferred1 = async {
        delay(1000)
        println("Deferred 1 completed")
    }

    val deferred2 = async {
        delay(500)
        throw RuntimeException("Error in Deferred 2") // ❌ This will cause cancellation!
    }

    val deferred3 = async {
        delay(1500)
        println("Deferred 3 completed")
    }

    awaitAll(deferred1, deferred2, deferred3) // ❌ Will throw due to deferred2

}
