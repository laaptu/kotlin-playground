package org.ahivs.kotlin.advanced.reified

fun <T> generics(item: T) {
    // cannot get T::class
    // class cannot be found from T
    when (item) {
        is String -> println("This is string")
        is Int -> println("This is Int")
        is Build -> println("This is Build")
        else -> println("Couldn't find the Type of this item $item")
    }
}

inline fun <reified T> genericsReified(item: T) {
    // with Reified we can get class
    println("The class type is ${T::class}")
    when (item) {
        is String -> println("This is string")
        is Int -> println("This is Int")
        is Build -> println("This is Build")
        else -> println("Couldn't find the Type of this item $item")
    }
}

val list: List<Any> = listOf("foo", "bar", 123, true, Build(123), Build(245))

fun main() {
    withReified()
    withoutReified()
}

private fun withReified() {
    println("### WITH REIFIED ########")
    genericsReified<String>("Hello")
    genericsReified<Int>(20)
    genericsReified<Build>(Build(9))

}

private fun withoutReified() {
    println("### WITHOUT REIFIED ########")
    generics<String>("Hello")
    generics<Int>(20)
    generics<Build>(Build(9))

}

private data class Build(val version: Int)


