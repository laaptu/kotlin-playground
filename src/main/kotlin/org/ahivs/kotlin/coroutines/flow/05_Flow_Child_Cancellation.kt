package org.ahivs.kotlin.coroutines.flow

import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*


fun main(){
    //childFlowCancellation()
    parentScopeCancellation()
}

fun parentScopeCancellation() = runBlocking {
    // Define four simple flows that emit values with delays
    val flow1 = flow {
        repeat(5) {
            delay(1000)
            emit("Flow1: $it")
        }
    }

    val flow2 = flow {
        repeat(5) {
            delay(1500)
            emit("Flow2: $it")
        }
    }

    val flow3 = flow {
        repeat(5) {
            delay(2000)
            emit("Flow3: $it")
        }
    }

    val flow4 = flow {
        repeat(5) {
            delay(2500)
            emit("Flow4: $it")
        }
    }

    // Combine the four flows
    val combinedFlow = combine(flow1, flow2, flow3, flow4) { f1, f2, f3, f4 ->
        "$f1, $f2, $f3, $f4"
    }

    // Launch a coroutine scope to collect from the combined flow
    val scope = CoroutineScope(Dispatchers.Default)
    scope.launch {
        try {
            combinedFlow.collect { value ->
                println(value)
            }
        } catch (e: Exception) {
            println("Caught exception: $e")
        }
    }

    // Cancel the scope after 5 seconds to simulate cancellation
    delay(5000)
    scope.cancel()
}




fun childFlowCancellation() = runBlocking {
    // Define four simple flows that emit values with delays
    val flow1 = flow {
        repeat(5) {
            delay(1000)
            emit("Flow1: $it")
        }
    }

    val flow2 = flow {
        repeat(5) {
            delay(1500)
            emit("Flow2: $it")
        }
    }

    val flow3 = flow {
        repeat(5) {
            delay(2000)
            emit("Flow3: $it")
        }
    }

    val flow4 = flow {
        // This flow will cancel itself after emitting a few values
        try {
            repeat(5) {
                delay(2500)
                emit("Flow4: $it")
            }
        } finally {
            throw CancellationException("Flow4 self-cancellation")
        }
    }

    // Combining the four flows
    val combinedFlow = combine(flow1, flow2, flow3, flow4) { f1, f2, f3, f4 ->
        "$f1, $f2, $f3, $f4"
    }

    // Launching a coroutine scope to collect from the combined flow
    val scope = CoroutineScope(Dispatchers.Default)
    scope.launch {
        try {
            combinedFlow.collect { value ->
                println(value)
            }
        } catch (e: Exception) {
            println("Caught exception: $e")
        }
    }

    // Keep the main thread alive long enough to observe the behavior
    delay(10000)
    scope.cancel()
}

