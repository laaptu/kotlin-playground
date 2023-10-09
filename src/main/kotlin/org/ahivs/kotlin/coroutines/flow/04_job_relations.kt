package org.ahivs.kotlin.coroutines.flow

import kotlinx.coroutines.*

fun main() = runBlocking {
    val parentJob = SupervisorJob()
    val combinedScope = CoroutineScope(Dispatchers.Default + parentJob)
    parentJob.cancel()
    val job1 = combinedScope.launch {
        delay(2000)
        println("Job 1 finished")
    }

    try {
        val job2 = combinedScope.launch {
            delay(1000)
            println("Job 2 about to throw an exception")
            throw RuntimeException("Job 2 exception")
        }
    } catch (exception: Exception) {

    }

    //job1.join()
    //delay(1200)
    //parentJob.cancel()
    // Note: We don't join job2 here as it throws an exception.
    // In a real-world scenario, you might want to handle this exception.
}
