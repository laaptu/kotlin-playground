package org.ahivs.kotlin.advanced.reified.basic

open class Actor
class Hero : Actor()
class Heroine : Actor()


fun <T : Actor> getActor() {
    println("Actor is guessed ")
}

inline fun <reified T : Actor> getActorReified() {
    println("Actor T is guessed = ${T::class.java}")
    when (T::class.java) {
        Hero::class.java -> println("This is Hero")
        Heroine::class.java -> println("This is Heroine")
        else -> println("This is Actor")
    }

}


fun noReified() {
    getActor<Hero>()
    getActor<Heroine>()
}

fun withReified() {
    getActorReified<Hero>()
    getActorReified<Heroine>()
}