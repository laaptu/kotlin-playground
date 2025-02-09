package org.ahivs.kotlin.coroutines.flow

import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*

// Flow 1: A never-ending flow
val flow1 = flow {
    emit("Flow1: Emitting first value")
    delay(1000) // Simulate delay
    emit("Flow1: Emitting second value")
    delay(5000) // Simulate an infinite delay (or long-running flow)
    // No more emissions, making it seem "stuck"
}

// Flow 2: A finite flow
val flow2 = flow {
    emit("Flow2: Emitting first value")
    delay(1000)
    emit("Flow2: Emitting second value")
}

// Flow 3: Another finite flow
val flow3 = flow {
    emit("Flow3: Emitting first value")
    delay(1000)
    emit("Flow3: Emitting second value")
}

fun main() {
    //singleCollectionTest()
    multipleCollectionTest()
}

fun multipleCollectionTest() = runBlocking {
    // Test 2: Concurrent collection (separate coroutines)
    println("\nStarting concurrent test:")
    launch { flow1.collect { value -> println(value) } }
    launch { flow2.collect { value -> println(value) } }
    launch { flow3.collect { value -> println(value) } }
}

fun singleCollectionTest() = runBlocking {
    // Test 1: Sequential collection (all flows in one coroutine)
    println("Starting sequential test:")
    launch {
        flow1.collect { value ->
            println(value) // Blocks here due to infinite delay in flow1
        }
        flow2.collect { value ->
            println(value) // Never reached
        }
        flow3.collect { value ->
            println(value) // Never reached
        }
    }
}
