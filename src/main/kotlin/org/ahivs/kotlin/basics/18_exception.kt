package org.ahivs.kotlin.basics.exceptions

class NotANumberException(message: String) : Throwable(message)

fun checkNumber(a: Any) {
    when (a) {
        is Int, Long, Double, Float -> println("This is number =$a")
        else -> throw NotANumberException("This is not a number")
    }
}

fun main(args: Array<String>) {
    try {
        checkNumber("Hello")
    } catch (e: NotANumberException) {
        println(e.message)
    }

    //using try catch finally as expression
    val result = try {
        checkNumber("A")
        20
    } catch (e: NotANumberException) {
        println(e.message)
        //okay we can return a value or else it will be by default Kotlin.Unit
        //0
    } finally {
        println("Nothing could be done here")
    }

    println(result)
}

