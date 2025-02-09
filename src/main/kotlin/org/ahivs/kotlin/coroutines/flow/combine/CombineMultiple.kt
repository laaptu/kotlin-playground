package org.ahivs.kotlin.coroutines.flow.combine

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun main() {
    CombineMultiple.runCombine()
}

object CombineMultiple {

    fun runCombine() = runBlocking {
        // Create three MutableStateFlow instances
        val f1 = MutableStateFlow(1)
        val f2 = MutableStateFlow(2)
        val f3 = MutableStateFlow(3)

        // Combine f1 and f2
        val combinedF1F2 = combine(f1, f2) { value1, value2 ->
            value1 + value2  // Combine values from f1 and f2
        }.combine(f3) { a, b -> a + b }

        // Combine the result of f1 and f2 with f3
//        val finalFlow = combine(combinedF1F2, f3) { sumF1F2, valueF3 ->
//            sumF1F2 + valueF3  // Combine the result with f3
//        }

        // Launch a coroutine to collect the final flow
        val job = launch {
            combinedF1F2.collect { result ->
                println("Final result: $result")
            }
        }

        // Update values in MutableStateFlow
        delay(1000) // Wait a bit for the initial values to be processed
        println("Updating f3 to 5")
        f3.value = 5  // This will trigger the final flow to emit an updated value

        delay(1000) // Wait to observe the new output

        println("Updating f1 to 10")
        f1.value = 10  // This will trigger the final flow to emit a new value

        delay(1000) // Wait to observe the new output

        println("Updating f2 to 20")
        f2.value = 20  // This will trigger the final flow to emit a new value

        delay(1000) // Wait to observe the new output

        // Cancel the job after testing
        job.cancel()
    }
}