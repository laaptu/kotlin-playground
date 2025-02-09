package org.ahivs.kotlin.serialization

import kotlinx.serialization.*
import kotlinx.serialization.json.*

// Sealed class with multiple subclasses
@Serializable
sealed class Shape {
    abstract val name: String

    @Serializable
    data class Circle(
        val radius: Double,
        override val name: String
    ) : Shape()

    @Serializable
    data class Rectangle(
        val width: Double, val height: Double,
        override val name: String
    ) : Shape()
}

@Serializable
data class Drawing(val shapes: List<Shape>)

fun main() {
    // Create instances of the sealed class
    val circle = Shape.Circle(3.0, "Circle")
    val rectangle = Shape.Rectangle(4.0, 5.0, "Rectangle")
    val shapes = listOf(
        circle,
        rectangle
    )

    var shape: Shape = circle
    shape = rectangle
    val drawing = Drawing(shapes)


    // Serialize the object to JSON
    val json = Json.encodeToString(drawing)
    println("Serialized: $json")

    // Deserialize it back to the original object
    val deserializedDrawing = Json.decodeFromString<Drawing>(json)
    println("Deserialized: $deserializedDrawing")
}
