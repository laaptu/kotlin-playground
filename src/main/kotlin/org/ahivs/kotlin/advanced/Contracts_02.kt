package org.ahivs.kotlin.advanced

fun main() {
    val items: List<Int>? = listOf(1, 2, 3, 4)
    if (items.isNullOrEmpty()) {
        println("Null or Empty")
    } else {
        items.first()
    }

}

