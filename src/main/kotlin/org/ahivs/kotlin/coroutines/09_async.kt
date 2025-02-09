package org.ahivs.kotlin.coroutines.async

import kotlinx.coroutines.*
import kotlin.system.measureTimeMillis
import kotlin.time.measureTime

suspend fun taskOne(): String {
    delay(1000) // Simulate a 1-second delay
    return "Task One Completed"
}

suspend fun taskTwo(): String {
    delay(1000) // Simulate a 1-second delay
    return "Task Two Completed"
}

fun main() = runBlocking {
    val totalTime = measureTimeMillis {
        val result = withContext(Dispatchers.IO) {
            val taskOneDeferred = async { taskOne() }
            val taskTwoDeferred = async { taskTwo() }

            val taskOneResult = taskOneDeferred.await()
            val taskTwoResult = taskTwoDeferred.await()

            "$taskOneResult and $taskTwoResult"
        }
        println(result)
    }

    println("Total time taken: $totalTime ms")
}