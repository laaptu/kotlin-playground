package org.ahivs.kotlin.advanced.inline

inline fun callTwo(noinline fn1: () -> Int, fn2: (Int) -> String) {
    println("CallTwo Start")
    fn1()
    fn2(10)
    println("CallTwo End")
}

fun main() {
    callTwo({
        println("This is fn1 returning 20")
        20
    }, {
        println("This is fn2 input = $it returning some string")
        it.toString()
    })
}
