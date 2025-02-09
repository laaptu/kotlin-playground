package org.ahivs.kotlin.coroutines.flow

import kotlinx.coroutines.*

fun main() = runBlocking {
    val userScopeCoroutineScope = CoroutineScope(Dispatchers.IO)
    val listenerScope = CoroutineScope(SupervisorJob() + userScopeCoroutineScope.coroutineContext)

    // Launch coroutines in both scopes
    userScopeCoroutineScope.launch {
        repeat(5) { i ->
            println("User scope is active: $i")
            delay(100)
        }
    }

    listenerScope.launch {
        repeat(5) { i ->
            println("Listener scope is active: $i")
            delay(100)
        }
    }

    // Delay a bit, then cancel listenerScope
    delay(250)
    println("Cancelling listenerScope")
    userScopeCoroutineScope.cancel()

    // Delay again to observe the behavior
    delay(2000)
    println("Finished observing")
}
