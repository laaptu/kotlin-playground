package org.ahivs.kotlin.basics.extension

class Car {
    fun drive() {

    }

    fun getFuelType(): Int {
        println("Diesel")
        return 20
    }
}

fun Car.printColor(color: String) {
    println(color)
    drive()
}

//this function won't have preference as it has the same method signature as that of
//function inside the class
fun Car.getFuelType(): Int {
    println("Petrol")
    return 30
}

fun main() {
    val car = Car()
    car.drive()
    car.printColor("Red")
    car.getFuelType()

    //extension function are statically resolved
    //meaning whatever type is declared, it will take that function
    val base: Base = Inherited()
    base.callClass()
    val base1: Inherited = Inherited()
    base1.callClass()
}

open class Base
class Inherited : Base()

fun Base.callClass() {
    println("This is base class")
}

fun Inherited.callClass() {
    println("This is inherited class")
}

