package org.ahivs.kotlin.coroutines.flow

import kotlinx.coroutines.flow.*
import kotlinx.coroutines.*


fun main() {
    sharedFlowSingleEmitAndClear()
    //sharedFlowTest1()
}

fun sharedFlowTest() = runBlocking {
    // this is working as we are emitting from some coroutine scope
    // Create a SharedFlow with a replay of 0
    val sharedFlow = MutableSharedFlow<Int>(replay = 0)


    // Collector 1 starts immediately
    launch {
        sharedFlow.collect {
            println("Collector 1 received: $it")
        }
    }

    // Launch a coroutine to emit values to the SharedFlow
    launch {
        repeat(5) { i ->
            delay(500)  // Delay to simulate some asynchronous operation
            val success = sharedFlow.emit(i)
            println("Emitted: $i and success = $success")
        }
    }


    // Delay for a bit and then start Collector 2
    delay(1500)
    launch {
        sharedFlow.collect {
            println("Collector 2 received: $it")
        }
    }

    // Let the program run for a while to observe the results
    delay(3000)
    launch {
        sharedFlow.collect {
            println("Collector 3 received :$it")
        }
    }
}

fun sharedFlowTest1() = runBlocking {

    // Create a SharedFlow with a replay of 0
    val sharedFlow = MutableSharedFlow<Int>(replay = 0)
    
    // here the collector cannot receive the value??? ,
    // it will only receive the value if extraBufferCapacity = 1 which means,
    // the value will be in buffer until collected
    // once collected, the buffer value will be removed and other collector
    // cannot collect it
    //val sharedFlow = MutableSharedFlow<Int>(extraBufferCapacity = 1)

    // Collector 1 starts immediately
    launch {
        sharedFlow.collect {
            println("Collector 1 received: $it")
        }
    }

    // Launch a coroutine to emit values to the SharedFlow
    repeat(5) { i ->
        delay(500)  // Delay to simulate some asynchronous operation
        val success = sharedFlow.tryEmit(i)
        println("Emitted: $i and success = $success")
    }


    // Let the program run for a while to observe the results
    delay(3000)

}

fun sharedFlowSingleEmitAndClear() = runBlocking {
    val sharedFlow = MutableSharedFlow<Int>(extraBufferCapacity = 1)
    launch {
        sharedFlow.collect {
            println("Collector 1 received: $it")
        }
    }
    delay(100)
    sharedFlow.tryEmit(10)
    delay(100)
    launch {
        sharedFlow.collect {
            println("Collector 2 received: $it")
        }
    }
    delay(3000)
}
