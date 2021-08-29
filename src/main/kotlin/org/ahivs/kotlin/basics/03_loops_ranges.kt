package org.ahivs.kotlin.basics

fun main() {
    //ranges
    //https://kotlinlang.org/docs/reference/ranges.html
    for (a in 1..10)
        println(a)

    //we can even create a list of same like
    println("-------")
    val b = 1..10
    for (n in b)
        println(n)

    //reverse range
    for (a in 10 downTo 1)
        println(a)

    //range with step
    for (a in 1..10 step 2)
        println(a)

    for (a in 10 downTo 1 step 2)
        println(a)

    //iterating overlist
    val capitals = listOf("Madrid", "Kathmandu", "Canberra", "Tokyo")
    for (c in capitals)
        println(c)

    var i = 10
    while (i > 0)
        println(i--)

    do {
        var x = 10
        x--
    } while (x > 0)

    //loop label which helps us easier to break out of the loop when needed

    firstloop@ for (i in 1..10)
        for (j in 1..10) {
            if (i * j > 100)
                break@firstloop
        }

}

