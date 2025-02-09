package org.ahivs.kotlin.coroutines.errorhandling

import kotlinx.coroutines.*

fun main() {
    exceptionHandler5()
}

fun exceptionHandler() = runBlocking {
    val coroutineScope = CoroutineScope(Dispatchers.IO)
    // any exception on launch will be passed onto coroutine scope
    val job = coroutineScope.launch {
        println("Throwing exception from launch")
        throw IndexOutOfBoundsException()
    }
    // if we won't add delay, there is chance we won't see the exception
    // given the program exits and the point to reach IndexBoundOfException is never reached
    delay(100)
}

fun exceptionHandler1() = runBlocking {
    val exceptionHandler = CoroutineExceptionHandler { _, exception -> println("Caught $exception") }
    // now we added exception handler, all the exception that is propagated to coroutineScope
    // will be caught
    val coroutineScope = CoroutineScope(Dispatchers.IO + exceptionHandler)
    val job = coroutineScope.launch { // root coroutine with launch
        println("Throwing exception from launch")
        throw IndexOutOfBoundsException()
    }
    // Let's wait for the job to be completed here
    job.join()
}

fun exceptionHandler2() = runBlocking {
    val coroutineScope = CoroutineScope(Dispatchers.IO)
    // the other way around is to catch the exception on launch
    val job = coroutineScope.launch {
        println("Throwing exception from launch")
        try {
            throw IndexOutOfBoundsException()
        } catch (e: Exception) {
            println("Caught $e")
        }
    }
    job.join()
}

fun exceptionHandler3() = runBlocking {
    val coroutineScope = CoroutineScope(Dispatchers.IO)
    // the other way around is to catch the exception on launch
    val job = coroutineScope.launch {
        println("Starting more action ")
        try {
            doAction1()
            doAction2()
        } catch (e: Exception) {
            println("Caught $e")
        }
    }
    job.join()
}

fun exceptionHandler4() = runBlocking {
    val coroutineScope = CoroutineScope(Dispatchers.IO)
    // the other way around is to catch the exception on launch
    val job = coroutineScope.launch {
        println("Starting child jobs using launch")
        try {
            // it won't be able to catch this exception
            // as child exception is passed now passed onto parent
            // or to say launch doesn't rethrow the exception
            launch {
                doAction1()
            }.join()
            launch {
                doAction2()
            }.join()
        } catch (e: Exception) {
            println("Caught $e")
        }
    }
    job.join()
}

fun exceptionHandler5() = runBlocking {
    val exceptionHandler = CoroutineExceptionHandler { _, exception -> println("Caught $exception") }
    val coroutineScope = CoroutineScope(Dispatchers.IO + exceptionHandler)
    // the other way around is to catch the exception on launch
    val job = coroutineScope.launch {
        println("Starting child jobs using launch")
        // now the exception is caught
        launch {
            doAction1()
        }.join()
        launch {
            doAction2()
        }.join()

    }
    job.join()
}

suspend fun doAction1() {
    println("Doing action 1")
    delay(100)
    throw IndexOutOfBoundsException()
}

suspend fun doAction2() {
    println("Doing action 2")
    delay(200)
    println("Completed action 2")
}

fun exceptionHandlerAsync() = runBlocking {
    val exceptionHandler = CoroutineExceptionHandler { _, exception -> println("Caught $exception") }
    val coroutineScope = CoroutineScope(Dispatchers.IO + exceptionHandler)
    val job = coroutineScope.launch { // root coroutine with launch
        val async1 = async {
            delay(300)
            println("Starting ascyn1")
            throw ArithmeticException()
        }

        val async2 = async {
            delay(500)
            println("Starting ascyn2")
            throw ArithmeticException()
        }
        awaitAll(async1, async2)
    }
    job.join()
}