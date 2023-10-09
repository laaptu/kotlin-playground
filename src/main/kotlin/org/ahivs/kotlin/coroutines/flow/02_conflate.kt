package org.ahivs.kotlin.coroutines.flow

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.conflate
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking

fun delayedFlow(): Flow<Int> {
    return flow {
        for (i in 1..5) {
            delay(1000)
            println("Emitting value $i at ${getTime()}")
            emit(i)
        }
    }
}

fun withoutConflate() = runBlocking {
    delayedFlow().collect {
        println("Received value = $it at ${getTime()}")
        delay(2000)
    }
}

fun withConflate() = runBlocking {
    delayedFlow().conflate().collect {
        delay(2000)
        println("Received value = $it at ${getTime()}")
    }
}

fun getTime() = System.currentTimeMillis()/1000

var time = 0

fun main() {
    //withoutConflate()
    withConflate()
}