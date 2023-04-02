package org.ahivs.kotlin.coroutines.flow

import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*


fun main() {
    //collectingNormalFlow()
    collectingStateFlow()
}

fun collectingNormalFlow() {
    runBlocking<Unit> {
        val delayFlow = createDelayFlow()

        launch {
            delay(3000)
            delayFlow.collect { value ->
                println("Collector 1 received $value ********")
            }
        }

        launch {
            delay(5000)
            delayFlow.collect { value ->
                println("Collector 2 received $value --------")
            }
        }
    }
}

fun collectingStateFlow() {
    runBlocking<Unit> {
        val stateFlow = createStateFlow()

        launch {
            stateFlow.collect { value ->
                println("Collector 1 received $value *********")
            }
        }

        launch {
            delay(3000)
            stateFlow.collect { value ->
                println("Collector 2 received $value ---------")
            }
        }
    }
}

fun createDelayFlow(): Flow<Int> = flow {
    repeat(5) { index ->
        delay(1000)
        emit(index)
        println("DelayFlow emitting $index")
    }
}


fun createStateFlow(): StateFlow<Int> = createDelayFlow()
    .stateIn(
        CoroutineScope(Dispatchers.Default),
        SharingStarted.WhileSubscribed(),
        0
    )
