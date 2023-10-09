package org.ahivs.kotlin.advanced.inline

@JvmInline
value class Seconds(val value: Long) {
    fun isPositive() = value >= 0
}

@JvmName("operateOnSeconds")
fun operateOnSeconds(seconds: Seconds) {
    println("### The seconds  = ${seconds.value}")
    println("### IsPositive = ${seconds.isPositive()}")
}

fun main() {
    operateOnSeconds(Seconds(1000))

    val secondList: List<Seconds> = listOf(Seconds(100), Seconds(200))
    println(secondList.first())
}

fun seconds(value: Int) = Seconds(value * 1000L)

//testing out nested inline class
@JvmInline
value class Age(val current: Int)

@JvmInline
value class Person(val age: Age)

fun calculatePersonAge(person: Person) {
    println("### person age = ${person.age}")
}