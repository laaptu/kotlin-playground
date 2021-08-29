package org.ahivs.kotlin.advanced.infix

/**
 *  Applicable to the extension function or member function that has only one parameter then
 *  we can add infix notation for better readability
 * */

fun String.shouldBeEqualTo(param: String): Boolean = param == this

//the above function can be converted to infix notation as follows
infix fun String.shouldBeEqualTo1(param: String): Boolean = param == this

fun main() {
    val a = "Hello"
    a.shouldBeEqualTo("Hello")
    a shouldBeEqualTo1 "Hello"

    val material = Material()
    material.addColor("Red")
    material addColor1 "White"
}

class Material {
    fun addColor(color: String) {
        println("Added color = $color")
    }

    infix fun addColor1(color: String) {
        println("Add color1 = $color")
    }
}
