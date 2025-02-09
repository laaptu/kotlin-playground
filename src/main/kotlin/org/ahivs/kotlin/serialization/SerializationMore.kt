package org.ahivs.kotlin.serialization

import kotlinx.serialization.Serializable
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

enum class Light {
    Led,
    Halogen
}

@Serializable
data class HeadLights(val serial: Int, val light: Light)

@Serializable
data class Vehicle(val vin: Long, val headLights: HeadLights)

@Serializable
data class ShowRoom(val stock: List<Vehicle>)

data class Hello(val id: Int, val greeting: String)
val vehicleJson = """
    {"vin":1000,"headLights":{"serial":20,"light":"Led"}}
""".trimIndent()

fun serialization() {
    val headLights = HeadLights(20, Light.Led)
    val vehicle = Vehicle(1000L, headLights)
    println(Json.encodeToString(vehicle))

    val showRoom = ShowRoom(listOf(vehicle))
    println(Json.encodeToString(showRoom))

    val hello = Hello(10,"Hi there")
    println(Json.encodeToString(hello))
}

fun main() {
    serialization()
}