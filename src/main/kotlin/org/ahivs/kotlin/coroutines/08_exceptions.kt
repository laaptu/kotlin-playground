package org.ahivs.kotlin.coroutines

import kotlinx.coroutines.*

fun exceptionPropagation() {
    val scope = CoroutineScope(Job())
    scope.launch {
        val def = async {
            delay(500)
            throw Exception("First child throwing exception")
        }
        try {
            def.await()
        } catch (exception: Exception) {
            println("First child exception = ${exception.message}")
        }

        launch {
            delay(1000)
            println("Second child task completed")
        }
    }
    // why this will not be called is that scope is cancelled due to one of the child finishing
    // which will in turn not make this work
    scope.launch {
        launch {
            delay(1000)
            println("Third child task completed")
        }
    }
}

suspend fun exceptionPropagationCoroutineScope() {
    coroutineScope {
        launch {
            val def = async {
                delay(500)
                throw Exception("First child throwing exception")
            }
            try {
                def.await()
            } catch (exception: Exception) {
                println("First child exception = ${exception.message}")
            }

            launch {
                delay(1000)
                println("Second child task completed")
            }
        }
        // why this will not be called is that scope is cancelled due to one of the child finishing
        // which will in turn not make this work
        launch {
            launch {
                delay(1000)
                println("Third child task completed")
            }
        }
    }
}

fun exceptionPropagationSuperVisorJob() {
    val scope = CoroutineScope(SupervisorJob())
    scope.launch {
        val def = async {
            delay(500)
            throw Exception("First child throwing exception")
        }
//        try {
//            def.await()
//        }catch (exception:Exception){
//            println("There is exception =${exception.message}")
//        }
        launch {
            delay(1000)
            println("Second child task completed")
        }
    }
    // why this will not be called is that scope is cancelled due to one of the child finishing
    // which will in turn not make this work
    scope.launch {
        launch {
            delay(1000)
            println("Third child task completed")
        }
    }
}

suspend fun exceptionPropagationSuperVisorScope() {
    supervisorScope {
        launch {
            val def = async {
                delay(500)
                throw Exception("First child throwing exception")
            }
            try {
                def.await()
            } catch (exception: Exception) {
                println("There is exception =${exception.message}")
            }
            launch {
                delay(1000)
                println("Second child task completed")
            }
        }
        // why this will not be called is that scope is cancelled due to one of the child finishing
        // which will in turn not make this work
        launch {
            launch {
                delay(1000)
                println("Third child task completed")
            }
        }
    }
}

fun coroutineExceptionHandler0() {
    val handler = CoroutineExceptionHandler { _, exception ->
        println("### Exception caught = $exception")
    }
    val scope = CoroutineScope(Job() + handler)
    scope.launch {
        launch {
            delay(500)
            throw Exception("First child exception")
        }
        launch {
            delay(1000)
            println("Second child completed")
        }
    }
}

fun coroutineExceptionHandler1() {
    val handler = CoroutineExceptionHandler { _, exception ->
        println("### Exception caught = $exception")
    }
    val scope = CoroutineScope(Job())
    scope.launch(handler) {
        launch {
            delay(500)
            throw Exception("First child exception")
        }
        launch {
            delay(1000)
            println("Second child completed")
        }
    }
}

fun coroutineExceptionHandler2() {
    val handler = CoroutineExceptionHandler { _, exception ->
        println("### Exception caught = $exception")
    }
    val scope = CoroutineScope(Job())
    scope.launch {
        launch(handler) {
            delay(500)
            throw Exception("First child exception")
        }
        launch {
            delay(1000)
            println("Second child completed")
        }
    }
}


fun main() {
    runBlocking {
        //exceptionPropagation()
        //exceptionPropagationCoroutineScope()
        //exceptionPropagationSuperVisorJob()
        //exceptionPropagationSuperVisorScope()
        coroutineExceptionHandler0()
        delay(1500)
        println("Done!")
    }
}