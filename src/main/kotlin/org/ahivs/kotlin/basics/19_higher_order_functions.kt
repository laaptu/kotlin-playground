package org.ahivs.kotlin.basics.functions

fun operation(x: Int, y: Int, fn: (Int, Int) -> Int) {
    println(fn(x, y))
}

fun doSum(x: Int, y: Int) = x + y

fun unaryOperation(x: Int, op: (Int) -> Int): Int {
    return op(x)
}

//if we only have function as argument, we can go towards the DSL kind of language
fun performOp(fn: (Int) -> Int) {

}

fun main() {
    operation(2, 10, ::doSum)
    operation(2, 10) { x: Int, y: Int -> x * y }
    operation(2, 10, { x, y -> x + y })
    val division = { x: Int, y: Int -> x / y }
    operation(2, 10, division)

    unaryOperation(10) { x -> x * x }
    //if it has only one argument, it can be replaced by i
    unaryOperation(10) { it * it }
    //anonymous function, we can do the same by declaring an anonymous function
    unaryOperation(10, fun(x: Int) = x * x)
    //or more in detail form
    unaryOperation(10, fun(x: Int): Int {
        val a = x * x
        return a
    })

    performOp { x -> x * 2 }

    //
    val db = Database()
    transaction(db) {
        println("Doing something on database")
        10
    }
}

class Database {
    fun commit() {

    }
}

fun transaction(db: Database, fn: () -> Int) {
    try {
        fn()
    } finally {
        db.commit()
    }

}

