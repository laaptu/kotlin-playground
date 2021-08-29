package org.ahivs.kotlin.advanced

/**
 *  The inner function is only accessbile by outerfunction meaning
 *  we cannot access outer function from other methods
 *  there can be unlimited inner functions and I assume unlimited nested inner
 *  function
 *
 * */
fun outerFunction(outerParam: String) {
    println("Outer Function outerParam = $outerParam")
    fun innerFunction(innerParam: String) {
        println("##Inner function inner param = $innerParam: outerParam = $outerParam")
    }

    innerFunction("Go inner")
}

fun main() {
    outerFunction("Hello")
}
