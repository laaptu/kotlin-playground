package org.ahivs.kotlin.basics

object PI {
    val num = 3.14f;
    val name = "Life of Pi"
}

fun main() {
    println(PI.name)
    val localPI = object {
        val num = 3.14f
    }
    println(localPI.num)
}
