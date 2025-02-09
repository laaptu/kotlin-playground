package org.ahivs.kotlin.coroutines.flow

import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*

fun main() = runBlocking {
    // Create a scope for emitting the flow
    val flow = MutableSharedFlow<Int>()
    val emitScope = CoroutineScope(Dispatchers.Default)

    // Create a scope for collecting the flow


    // Flow that emits values in the emitScope
    emitScope.launch {
        for (i in 1..10) {
            delay(500) // Simulate work (suspension point)
            flow.emit(i) // Emit value
            println("Emitted: $i")
        }
    }
    runInAnotherScope(flow)

    // Wait for the flow collection to finish
    delay(3000) // Wait some more time to see if the collection from collectScope still works

    println("Main program ends")
}

fun runInAnotherScope(flow: Flow<Int>) {
    val collectScope = CoroutineScope(Dispatchers.IO)
    val mainScope = CoroutineScope(Dispatchers.IO)
    mainScope.launch {
        // Launch the collection in a different collectScope
        val jobs = mutableListOf<Job>()
        collectScope.launch {
            flow.collect { value ->
                println("Collected in collectScope1: $value")
                cancel()
            }
        }.also { jobs.add(it) }


        collectScope.launch {
            flow.collect { value ->
                println("Collected in collectScope2: $value")
            }
        }.also { jobs.add(it) }

        // Simulate some time delay before cancelling scopes
        delay(2000) // Allow some collection to happen
        println("Canceling collectScope...")

        //collectScope.cancel() // Cancel the collectScope
        jobs.forEach { it.cancel() }
    }

}
