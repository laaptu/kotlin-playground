package org.ahivs.kotlin.coroutines

import kotlinx.coroutines.*
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain

suspend fun jobInstanceTest() {
    val job = Job()
    val scope = CoroutineScope(job+Dispatchers.Main)
    println("## parent scope context = ${scope.coroutineContext}")
    val startedJob1 = scope.launch(Dispatchers.IO) {
        delay(1000)
        println("This is the first job context = $coroutineContext")
    }
    val startedJob2 = scope.launch {
        delay(2000)
        println("This is the second job context = $coroutineContext")
    }
    println("### job =$job, job1 =$startedJob1, job2 = $startedJob2")
    job.children.forEach {
        println("## children job = $it")
    }


}

@OptIn(ExperimentalCoroutinesApi::class)
fun main() {
    Dispatchers.setMain(Dispatchers.Unconfined)
    runBlocking {
        jobInstanceTest()
        delay(5000)
        Dispatchers.resetMain()
    }

}