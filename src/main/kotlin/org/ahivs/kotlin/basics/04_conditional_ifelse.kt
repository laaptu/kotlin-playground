package org.ahivs.kotlin.basics

fun main() {
    val str = "hello"

    if (str != "abc")
        println("Not equal to abc")
    else
        println("Equal to abc")

    val someReturn = if (str != "abc")
        println("Not equal to abc")
    else
        println("Equal to abc")
    println(someReturn)

    val otherReturn = if (str != "abc")
        "Hello"
    else
        "Hi"
    println(otherReturn)

    val otherReturn1 = if (str != "abc") {
        println("Hello")
        20
    } else {
        println("Hi")
        30
    }
    println(otherReturn1)

    val target = "PS5"

    when (target) {
        is String -> println("This is string")
        else -> println("Not string")
    }

    val lenExpression = when (target.length) {
        10 -> println("length = 10")
        else -> println("smaller")
    }
    println(lenExpression)

}
