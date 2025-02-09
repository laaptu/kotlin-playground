package org.ahivs.kotlin.serialization

import kotlinx.serialization.Serializable
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

@Serializable
data class Data(val a: Int, val b: String)

fun main() {
   serializationTest()
    deserializationTest()
}

fun deserializationTest(){
    println("### DESERIALIZATION ###")
    val data = Json.decodeFromString<Data>(dataJson)
    println(data)
    val items = Json.decodeFromString<List<Data>>(itemsJson)
    println(items)
}

fun serializationTest(){
    println("#### SERIALIZATION ####")
    val json = Json.encodeToString(Data(10, "Hello World"))
    println(json)
    val items = listOf(
        Data(10, "Hello World 10"),
        Data(11, "Hello World 11"),
        Data(2, "Hello World 2")
    )
    println(Json.encodeToString(items))
}

val dataJson = """
    {"a":10,"b":"Hello World"}
""".trimIndent()

val itemsJson = """
    [{"a":10,"b":"Hello World 10"},{"a":11,"b":"Hello World 11"},{"a":2,"b":"Hello World 2"}]
""".trimIndent()