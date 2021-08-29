package org.ahivs.kotlin.basics.interop

import java.io.IOException

class CustomerKotlin(val name: String) {
    //see how title cannot be accessed as field, but only accessed as property or function
    val title: String = "Engineer"

    //adding JVM field annotation we can access it as a field
    @JvmField
    val title1: String = "Senior"

    //here though we have default parameters
    //while accessing it from Java class we must pass paramters
    fun getInterval(time: Int = 30): Int {
        return if (time < 30)
            40
        else
            50
    }

    //with this we can call this function with
    // getInterval1(20) or getInterval1() as it adds overloading function
    @JvmOverloads
    fun getInterval1(time: Int = 30): Int {
        return if (time < 30)
            40
        else
            50
    }

    //if we want to change the function name
    @JvmName("getAddress")
    fun printAddress() = "Sydney"

    //by default the function signature cannot determine whether it throws exception or not
    // in kotlin
    fun getSalary(id: Int): Int {
        if (id < 0)
            throw IOException("Id must be greater than 100")
        return id * 200
    }

    @Throws(IOException::class)
    fun getSalary1(id: Int): Int {
        if (id < 0)
            throw IOException("Id must be greater than 100")
        return id * 200
    }
}

fun main() {
    val customerJava = CustomerJava()
    println(customerJava.age)
    println(customerJava.name)

    val customerKotlin = CustomerKotlin("Tom")
    customerKotlin.printAddress()
}

//accessing extension function from Java
fun CustomerKotlin.doSomething() {
    println(printAddress())
}
