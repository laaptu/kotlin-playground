package org.ahivs.kotlin.basics

data class Employee(val name: String, val id: Int)
class Person(val name: String, val id: Int)

fun main() {
    val employee1 = Employee("Tom", 20)
    val employee2 = Employee("Tom", 20)

    println(employee1 == employee2)

    val person1 = Person("Tom", 20)
    val person2 = Person("Tom", 20)
    println(person1 == person2)
}
