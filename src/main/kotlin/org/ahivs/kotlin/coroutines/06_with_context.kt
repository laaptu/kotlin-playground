package org.ahivs.kotlin.coroutines

import kotlinx.coroutines.*
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import kotlin.coroutines.CoroutineContext

fun withContextNonCancellable() {
    val mainScope = CoroutineScope(
        Dispatchers.Main + Job() + CoroutineName("MainScope")
    )
    val firstJob = mainScope.launch {
        println("First job = $coroutineContext")
        // if we want to make this run even if parent is cancelled
        // we will switch to special NonCancellable CoroutineContext
        withContext(NonCancellable) {
            delay(1000)
            println("Switched job = $coroutineContext")
        }
    }
    firstJob.cancel()
}

fun withContextCancellable() {
    val mainScope = CoroutineScope(
        Dispatchers.Main + Job() + CoroutineName("MainScope")
    )
    val firstJob = mainScope.launch {
        println("First job = $coroutineContext")
        withContext(Dispatchers.IO) {
            // this will be cancelled
            delay(1000)
            println("Switched job = $coroutineContext")
        }
    }
    firstJob.cancel()
}

fun main() {
    runBlocking {
        Dispatchers.setMain(Dispatchers.Unconfined)
        withContextCancellable()
        //withContextNonCancellable()
        delay(1000)
        Dispatchers.resetMain()
    }
}