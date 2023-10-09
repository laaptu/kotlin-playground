package org.ahivs.kotlin.testing

import io.mockk.every
import io.mockk.mockk
import io.mockk.mockkObject
import org.ahivs.kotlin.testing.Extensions.getStartEndTime
import org.ahivs.kotlin.testing.Extensions.isIdValid

data class Employee(val id: Int, val name: String)

fun main() {
    mockkObject(Extensions)
    
    val employee = mockk<Employee>()

    every { employee.getStartEndTime() } returns ("a" to "b")

    println(employee.getStartEndTime())  // This should print "false"
}
