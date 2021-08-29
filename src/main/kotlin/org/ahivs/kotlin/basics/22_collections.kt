package org.ahivs.kotlin.basics

import java.util.*

fun main() {
    val immutableCities = listOf("A", "B", "C")
    //immutableCities.add(10)
    println(immutableCities.javaClass)
    val immCities = Arrays.asList("A", "B", "C")

    val mutableCities = mutableListOf("A", "B", "C")
    mutableCities.add("E")

    val immHashMap = hashMapOf(2 to "Two", 3 to "Three")
    val mutableHashMap = mutableMapOf(Pair(1, 2), Pair(3, 2))
    mutableHashMap[10] = 11
    mutableHashMap.forEach { (t, u) -> println("$t = $u") }
}
