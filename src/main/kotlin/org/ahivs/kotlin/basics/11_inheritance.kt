package org.ahivs.kotlin.basics.inheritance

//adding open only makes this class extendible else not
open class Person {
    fun callName() {
    }

    open fun callNameOpenly() {

    }
}

class Student : Person() {
    override fun callNameOpenly() {
        super.callNameOpenly()
    }
    //cannont override callName as it is not open
}

open class Employee : Person {
    constructor() : super() {
        //calling super or constrcutor of the Person
    }

    //now this function cannot be inherited by Waiter
    final override fun callNameOpenly() {
        super.callNameOpenly()
    }
}

class Waiter : Employee() {
    //cannot inherit callNameOpenly() as it is declared final

    init {

    }
}

//data class can also inherit
data class Worker(val name: String) : Person() {

}
