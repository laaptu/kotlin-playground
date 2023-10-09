package org.ahivs.kotlin.coroutines.`coroutine-builders`

import kotlinx.coroutines.*
import kotlin.coroutines.EmptyCoroutineContext
import kotlin.coroutines.coroutineContext

fun main1() {
//    runWithCustomScope()
//    runWithRunBlocking()
//    asyncTestWithRunBlocking()
     runWithEmptyCoroutineContext()
}

suspend fun main(){
    threadSwitch()
}

fun runWithRunBlocking() {
    println("### Running with run blocking")
    runBlocking {
        threadSwitch()
    }
}

fun runWithCustomScope() {
    println("### Running with custom scope")
    val coroutineScope = CoroutineScope(context = Dispatchers.Default)
    coroutineScope.launch {
        threadSwitch()
    }
    Thread.sleep(2000)
}

fun runWithEmptyCoroutineContext() {
    println("### Running with empty coroutine context")
    val coroutineScope = CoroutineScope(context = EmptyCoroutineContext)
    coroutineScope.launch {
        threadSwitch()
    }
    Thread.sleep(2000)
}

suspend fun threadSwitch() {
    println(Thread.currentThread().name)
    withContext(Dispatchers.IO) {
        println("Dispatcher -> ${Thread.currentThread().name}")
    }
    println(Thread.currentThread().name)
}


fun asyncTestWithRunBlocking() = runBlocking {
    println("#### AsyncTest with runblocking ")
    println(Thread.currentThread().name)

    coroutineScope {
        launch {
            asyncTask()
        }
    }
    withContext(Dispatchers.IO) {
        println("Dispatcher -> ${Thread.currentThread().name}")
    }
    println(Thread.currentThread().name)
}

suspend fun asyncTask() {
    delay(1000)
    println("### Async task completed")
}