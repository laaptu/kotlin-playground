package org.ahivs.kotlin.advanced.invoking.instances

//by default we can override the invoke function as per our need
class Manager {
    operator fun invoke(name: String) {
        println("I am invoking manager of $name")
    }
}

//we can add any input parameter as we like
class Employee {
    operator fun invoke(age: Int, salary: Int) {
        println("Employee age = $age and salary = $salary")
    }
}

fun main() {
    val manager = Manager()
    manager("Hello")

    val employee = Employee()
    employee(20, 180000)
}
