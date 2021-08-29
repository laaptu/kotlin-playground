package org.ahivs.kotlin.basics.casting

open class Person

class Employee : Person() {
    fun getVacation() {

    }
}

class Contractor : Person()

fun main() {
    val person = getPerson()
    if (person is Employee)
        person.getVacation()

    val item: Any = 10
    ////this will throw error
    //val itemString = item as String
    //println(itemString.length)
    //the below is saying that I will try to convert it to string, but if it fails, it will be null
    val itemString1 = item as? String
    println(itemString1?.length)
}

fun getPerson(): Person {
    return Contractor()
}

