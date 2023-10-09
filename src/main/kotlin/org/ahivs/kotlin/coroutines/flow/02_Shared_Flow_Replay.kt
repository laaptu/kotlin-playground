package org.ahivs.kotlin.coroutines.flow

import kotlinx.coroutines.cancel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun main() {
    replayCount()
}

fun replayCount() {
    runBlocking {
        val flow = getFlow()

        // Subscriber 1 collects immediately
        flow.collect { value ->
            println("Subscriber 1 collected: $value")
            if (value == 2)
                cancel()
        }

        // Subscriber 2 collects later
        launch {
            flow.collect { value ->
                println("Subscriber 2 collected: $value")
            }
        }

        // Subscriber 3 collects even later
        launch {
            flow.collect { value ->
                println("Subscriber 3 collected: $value")
            }
        }

        // New subscriber collects
        launch {
            flow.collect { value ->
                println("New Subscriber collected: $value")
            }
        }
    }
}

private suspend fun getFlow(): SharedFlow<Int> {
    val flow = MutableSharedFlow<Int>(replay = 3)
    flow.emit(1)
    flow.emit(2)
    flow.emit(3)
    return flow
}

