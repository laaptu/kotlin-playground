package org.ahivs.kotlin.coroutines

import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

suspend fun requestRemoteToken(): String {
    delay(2000)
    println("### Generating a new token")
    return "This is our token"
}

fun testingJobJoin(){
    runBlocking {
        val job = launch {
            requestRemoteToken()
        }
        // just wait for the job to complete
        // doesn't give back the result
        println("### job.join() = ${job.join()}")
    }
}

fun testingJobState(){
    runBlocking {
        val job = launch {
            requestRemoteToken()
        }
        println("### Job completed = ${job.isCompleted}")
    }
}

fun main() {
    testingJobState()
}