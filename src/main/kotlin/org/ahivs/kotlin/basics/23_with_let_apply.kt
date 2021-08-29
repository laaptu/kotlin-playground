package org.ahivs.kotlin.basics.withletapply

class Brick {
    fun printBrick() {

    }

    fun getBrick() = 20
    fun paintBrick() {

    }
}

fun main() {
    //with means just perform mutilple property calls of the object
    val brick = Brick()
    //brick.printBrick()
    //brick.getBrick()
    //brick.painBrick()

    with(brick) {
        printBrick()
        paintBrick()
        getBrick()
    }

    //apply means the same but the but returns the same object at last

    val brick1: Brick = brick.apply {
        printBrick()
        paintBrick()
        getBrick()
    }
    //let mainly beneficial for null checks and stuff
    // and whatever is the last statement will be the return type of it
    //if the last statement is not of any return type, then the default return type is
    // Unit


    val str: String? = "Heelo"
    val str1 = str?.let {
        it.substring(1)
        20
    }
    println(str1)
}
