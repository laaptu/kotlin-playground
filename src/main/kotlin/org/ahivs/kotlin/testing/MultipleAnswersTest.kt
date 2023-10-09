package org.ahivs.kotlin.testing

import io.mockk.every
import io.mockk.mockk
import java.util.*

fun main() {
    val mockList = mockk<MutableList<String>>(relaxed = true)

    every { mockList.add(any()) } answers {
        println("First answer: ${it.invocation.args[0]}")
        true
    }

    every { mockList.add(any()) } answers {
        println("Second answer: ${it.invocation.args[0]}")
        false
    }

    every { mockList.add(any()) } returns true
    val added = mockList.add("Hello")
    println(added)

    val calendar = Calendar.getInstance(TimeZone.getTimeZone("Australia/Sydney"))
    println(calendar)

}

data class Phone(val id: Int)