package org.ahivs.kotlin.backingfield

//https://kotlinlang.org/docs/reference/properties.html#backing-properties
class Person {
    private var _age = 0;
    val age: Int
        get() {
            return _age++
        }
    var name:String = "Tom Cruise"
        private set(value)  {
            field = value
        }
}

fun main() {
    val person = Person()
    //person.age = 20
    println(person.age)
}
