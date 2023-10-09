package org.ahivs.kotlin.coroutines

import kotlinx.coroutines.*
import java.lang.Exception
import kotlin.coroutines.coroutineContext


fun main() {
    //runForeverCancellationThroughYield()
    // Ensure active is not working
    runForeverCancellationThroughEnsureActive()
}

private fun runForeverCancellationThroughYield() {
    runBlocking {
        val job = launch {
            runForeverYield()
        }
        delay(1000)
        job.cancel()
    }
}

suspend fun runForeverYield() {
    while (true) {
        println("Running......")
        yield()
        println("Forever it seems ........")
    }
}

private fun runForeverCancellationThroughEnsureActive() {
    runBlocking {
        // only works with launch(Dispatcher.IO)
        val job = launch(Dispatchers.IO) {
            runForeverEnsureActive()
        }
        // meaning if I do the following
//        val job = launch {
//            runForeverEnsureActive()
//        }
        // it doesn't work
        delay(1000)
        job.cancel()
    }
}

suspend fun runForeverEnsureActive() {
    while (true) {
        println("Running ......")
        coroutineContext.ensureActive()
        println("Forever it seems with ensure active ........")
    }
}


