package org.ahivs.kotlin.coroutines

import kotlinx.coroutines.*

fun normalJobException() {
    val scope = CoroutineScope(Job())
    val firstJob = scope.launch {
        delay(1000)
        println("First job completed")
    }

    val secondJob = scope.launch {
        delay(1000)
        throw Exception("Error in completing second job")
    }
    val thirdJob = scope.launch {
        delay(2000)
        println("Third job completed")
    }
}

fun supervisorJobException() {
    val scope = CoroutineScope(SupervisorJob())
    val firstJob = scope.launch {
        delay(1000)
        println("First job completed")
    }

    val secondJob = scope.launch {
        delay(1000)
        throw Exception("Error in completing second job")
    }
    val thirdJob = scope.launch {
        delay(2000)
        println("Third job completed")
    }
}

fun supervisorNotAsParent() {
    val mainJob = Job()
    val mainScope = CoroutineScope(mainJob)
    val superVisorJob = SupervisorJob()
    mainScope.launch(superVisorJob) {
        println("## superVisorJob = $superVisorJob,\ncurrent context job = ${coroutineContext.job} \n mainJob  = $mainJob")
        superVisorJob.children.forEach {
            println("### children Jobs = $it")
        }
        launch {
            delay(500)
            throw Exception("Child 1 encountered error")
            //println("### Child1 job completed")
        }
        launch {
            delay(1000)
            println("## Child2 job completed")
        }
    }

    mainScope.launch {
        launch {
            delay(1000)
            println("### Third child completed")
            println("### mainJob cancelled = ${mainJob.isCancelled}")
        }
    }
}

fun supervisorAsParent1() {
    val mainJob = Job()
    val mainScope = CoroutineScope(mainJob)
    val superVisorJob = SupervisorJob()
    mainScope.launch(superVisorJob) {
        supervisorScope {
            launch {
                delay(500)
                throw Exception("Child 1 encountered error")
                //println("### Child1 job completed")
            }
            launch {
                delay(1000)
                println("## Child2 job completed")
            }
        }
    }
}

fun supervisorAsParent2() {
    val mainJob = SupervisorJob()
    val mainScope = CoroutineScope(mainJob)
    mainScope.launch {
        supervisorScope {
            launch {
                delay(500)
                throw Exception("Child 1 encountered error")
                //println("### Child1 job completed")
            }
            launch {
                delay(1000)
                println("## Child2 job completed")
            }
        }
    }
}

fun main() {
    runBlocking {
        //normalJobException()
        //supervisorJobException()
        //supervisorNotAsParent()
        supervisorAsParent2()
        delay(3000)
    }
}