package org.ahivs.kotlin.basics

class Customer {
    var id = 0
    var name = ""
    val surname = "Cruise"
}

class Customer1(_id: Int, _name: String) {
    var id = _id
    var name = _name
}

class Customer2(var id: Int, var name: String)
//Customer1 and Customer2 way of declaration is same,
// but customer2 is preferred way

//having default constructor arguments
class Customer3(var id: Int, var name: String = "Tom")

//using init block
class Customer4(var id: Int, var name: String) {
    init {
        name = name.toUpperCase()
    }
}

//secondary constructor
class Customer5(var id: Int, var name: String) {
    init {
        name = name.toUpperCase()
    }

    //this(20,"Tom") will call the default constructor or we can modify this
    // constructor(id:Int,name:String,email:String): this(id,name)
    constructor(email: String) : this(20, "Tom") {
        //this block is init() for this constructor call or to say it will be executed
        // after init() call
        println(email)
    }
}

//custom getter and setter
class Customer6 {
    var name: String = ""
        set(value) {
            //field is the one that sets the name = value.toUppercase
            //you can add your custom logic out here
            field = value.toUpperCase()
        }
        //we can even have a function block to add more logic out here
        get() = name.toLowerCase()

    //normal function
    fun printName() {
        println(name)
    }

    //private function
    private fun printNamePrivately() {
        println(name)
    }
}

fun main() {


    val customer3 = Customer3(20)
    val customer32 = Customer3(20, "Cruise")


    val customer = Customer()
    customer.id = 20
    customer.name = "Tom"
}
