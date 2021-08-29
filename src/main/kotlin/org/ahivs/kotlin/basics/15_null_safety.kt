package org.ahivs.kotlin.basics.nullsafety

fun main() {
    //Kotlin by default is null safety i.e. you cannot declare null by default
    val message: String = "Hello"
    //this cannot be written as
    //val msg:String = null
    println(message.length)
    //to work with null we need to do the following
    val nullMsg: String? = null
    //and we safely access this, if it is null, it won't be printed else it will be printed
    println(nullMsg?.length)
    //there is other way around as well, which will crash if there is null, it kind of saying
    // print length, even I am not checking null properly
    //println(nullMsg!!.length)

    //if value is mutable, then compiler is smart enough to check for us
    var msgNull: String? = null
    msgNull = "Hello"
    //smart casting
    println(msgNull.length)
}
