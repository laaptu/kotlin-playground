package org.ahivs.kotlin.coroutines.flow

import kotlinx.coroutines.flow.*
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking

fun main() = runBlocking {
    // Define two sample flows
    val flowA: Flow<String> = flow {
        emit("A1")
        delay(100)
        emit("A2")
        delay(100)
        emit("A3")
    }

    val flowB: Flow<String> = flow {
        emit("B1")
        delay(150)
        emit("B2")
    }

    // Using zip
    flowA.zip(flowB) { a, b ->
        println("zip: $a - $b")
    }.collect()

    // Using combine
    combine(flowA, flowB) { a, b ->
        println("combine: $a - $b")
    }.collect()
}
