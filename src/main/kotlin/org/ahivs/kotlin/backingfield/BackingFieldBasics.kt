package org.ahivs.kotlin.backingfield

//https://kotlinlang.org/docs/reference/properties.html#backing-properties
class Person {
    private var _age = 0;
    val age: Int
        get() {
            return _age++
        }
}

fun main() {
    val person = Person()
    //person.age = 20
    println(person.age)
}
