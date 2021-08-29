package org.ahivs.kotlin.basics

fun variableDeclaration() {
    //mutable variables
    var streetName: String
    var streetNumber: Int = 20
    streetNumber = 30
    streetName = "George street"
    //Type inference where from value assigned it will take up the value
    var phoneNumber = 50

    //non mutable variables
    val address: String = "High street"
}
