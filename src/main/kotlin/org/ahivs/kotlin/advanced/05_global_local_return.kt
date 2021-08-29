package org.ahivs.kotlin.advanced

fun globalReturn() {
    val nums = 1..30
    nums.forEach {
        if (it > 10)
            return
    }
    println("Function finished for Global")
}

fun localReturn() {
    val nums = 1..30
    nums.forEach {
        if (it > 10)
            return@forEach
    }
    println("Function finished for Local")
}

fun localReturnWithCustomLabel() {
    val nums = 1..30
    nums.forEach myLoop@{
        if (it > 10)
            return@myLoop
    }
    println("Function finished for Custom Label")
}


fun <T> Iterable<T>.forEachMod(action: (T) -> Unit): Unit {
    for (element in this) action(element)
}

fun globalReturnTest() {
    val nums = 1..30
    nums.forEachMod {
        if (it > 10)
            return@forEachMod
    }
}


fun functionDeclarationReturnIsLocal() {
    val nums = 1..30
    nums.forEach(fun(x) {
        if (x > 10)
            return
    })
    println("Function declarationReturn is local finished")
}

fun main() {
    functionDeclarationReturnIsLocal()
}
