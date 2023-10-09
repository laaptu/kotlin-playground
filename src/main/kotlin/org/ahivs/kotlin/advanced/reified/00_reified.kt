package org.ahivs.kotlin.advanced.reified.basic

open class Actor
class Hero : Actor()
class Heroine : Actor()


fun <T : Actor> getActor() {
    println("Actor is guessed ")
}

inline fun <reified T : Actor> getActorReified() {
    println("Actor T is guessed = ${T::class.java}")
}


fun noReified() {
    getActor<Hero>()
    getActor<Heroine>()
}

fun withReified() {
    getActorReified<Hero>()
    getActorReified<Heroine>()
}