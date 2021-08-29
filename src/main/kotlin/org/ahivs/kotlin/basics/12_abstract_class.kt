package org.ahivs.kotlin.basics

abstract class Actor {
    abstract fun act()

    //we can even add variables out her
    val title = "Action Star"
    //and function along with abstract function
    fun getActionTitle(): String = title
}

class HollywoodActor : Actor() {
    override fun act() {
        println("I am acting for hollywood")
    }

}
