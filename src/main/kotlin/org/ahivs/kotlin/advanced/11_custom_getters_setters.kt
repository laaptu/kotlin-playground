package org.ahivs.kotlin.advanced.custom.getter.setter

class Profit {
    var amount = 0.0f
        get() = field
        set(value) {
            if (value > 100)
                field = value
        }
}


fun main() {
    val profit = Profit()
    println("1. Profit amount = ${profit.amount}")
    profit.amount = 200f
    println("2. Profit amount = ${profit.amount}")
    profit.amount = 40f
    println("3. Profit amount = ${profit.amount}")
}

