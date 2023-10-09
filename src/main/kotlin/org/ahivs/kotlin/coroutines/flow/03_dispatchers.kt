package org.ahivs.kotlin.coroutines.flow

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import java.lang.Thread.currentThread
import java.lang.Thread.sleep
import kotlin.system.measureTimeMillis

fun singleThreadBlock() {
    runBlocking {
        launch {
            sleep(500)
            println("First launch completed with = ${currentThread().name}")
        }
        launch {
            sleep(500)
            println("Second launch completed = ${currentThread().name}")
        }
    }
}

fun dispatchersIO() {
    runBlocking(context = Dispatchers.IO) {
        launch {
            sleep(500)
            println("First launch completed with = ${currentThread().name}")
        }
        launch {
            sleep(500)
            println("Second launch completed = ${currentThread().name}")
        }
    }
}

fun main() {
    val time = measureTimeMillis {
        singleThreadBlock()
        //dispatchersIO()
    }
    println("### Total time = $time")
}