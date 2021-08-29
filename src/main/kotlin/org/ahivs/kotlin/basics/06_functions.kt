package org.ahivs.kotlin.basics

fun print() {
    println("Print something")
}

fun main() {
    val print: Unit = print()
    println(print)
    println(returnSomething1(1, 2))


    defaultParams(10)
    defaultParams(10, 50)

    //the compiler will be confused as email has default value but address is not being passed
    //printName("Tom","Hollywood")
    printName("Tom", address = "Hollywood")
    printName(address = "Hollywood", name = "Tom")

    //multiple arguments of same time
    printAllName("Tom")
    printAllName("Tom", "Arnold", "Robin")

}


fun giveNothing(): Nothing {
    throw Exception("Nothing is there")
}

fun returnSomething(a: Int, b: Int): Int {
    val c = a + b
    return c
}

fun returnSomething1(a: Int, b: Int) = a + b

fun defaultParams(a: Int, b: Int = 20) = a + b


fun printName(name: String, email: String = "", address: String) {
    println("name = $name -- email = $email --- address = $address")
}


fun printAllName(vararg names: String) {
//    for (name in names)
//        println(name)
    deleteGatePrinting(*names)
}

fun deleteGatePrinting(vararg names: String) {
    for (name in names)
        println(name)
}
