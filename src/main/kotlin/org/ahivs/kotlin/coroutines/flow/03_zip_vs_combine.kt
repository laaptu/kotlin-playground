package org.ahivs.kotlin.coroutines.flow

import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*

fun main() {
    //example2()
    combineTest()
}

private fun example1() = runBlocking {
    // Define two sample flows
    val flowA: Flow<String> = flow {
        emit("A1")
        delay(100)
        emit("A2")
        delay(100)
        emit("A3")
    }

    val flowB: Flow<String> = flow {
        emit("B1")
        delay(150)
        emit("B2")
    }

    // Using zip
    flowA.zip(flowB) { a, b ->
        println("zip: $a - $b")
    }.collect()

    // Using combine
    combine(flowA, flowB) { a, b ->
        println("combine: $a - $b")
    }.collect()
}

private fun example2() = runBlocking {
    // Define two sample flows
    val flowA: Flow<String> = flow {
        delay(100)
        emit("A1")
        delay(100)
        emit("A2")
        delay(100)
        emit("A3")
    }

    val flowB: Flow<String> = flow {
        emit("B1")
        delay(150)
        emit("B2")
    }

    // Using zip
    flowA.zip(flowB) { a, b ->
        println("zip: $a - $b")
    }.collect()

    // Using combine
    combine(flowA, flowB) { a, b ->
        println("combine: $a - $b")
    }.collect()

    merge(flowA, flowB).collect { a ->
        println("merge : $a")
    }
}

private fun combineTest() = runBlocking {
    val timesheet = Timesheet(10)
    val remoteTimesheet = MutableStateFlow(timesheet)
    val inProgressTimesheet = MutableStateFlow(timesheet)

    launch {
        combine(remoteTimesheet, inProgressTimesheet) { remote, inProgress ->
            println("### onCombine $remote $inProgress")
            remote?.endTime != inProgress?.endTime
        }.collect{
            println("### EndTimeChanged = $it")
        }
    }
    launch {
        inProgressTimesheet.collect{
            println("### inProgress timesheet changed = $it")
        }
    }

    delay(500)
    launch {
        inProgressTimesheet.emit(timesheet.copy(endTime = 20))
    }
    delay(500)
    cancel()

}


data class Timesheet(val endTime: Int)