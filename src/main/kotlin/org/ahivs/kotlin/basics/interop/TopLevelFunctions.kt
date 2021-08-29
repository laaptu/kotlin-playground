@file:JvmName("Utility")

package org.ahivs.kotlin.basics.interop

fun getName() = "Tom"
val age = 20

//the const and val doesn't have any changes while accessing from Kotlin
// the only change it is seen while accessing from Java class
const val age1 = 30

fun main() {
    println(age)
    println(age1)
}
