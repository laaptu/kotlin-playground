package org.ahivs.kotlin.basics

fun dataTypes() {
    //All the data types are objects
    //all things in kotlin are object

    val a: Int = 30
    println(a.plus(10))
    val c: Double = 50.0
    println(c.inc())
    val f: Float = 60.0f
    println(f.minus(10))

    val l: Long = 20L
    //there is no implicit conversion
    //val l1: Long = a
    //so you have to explicit conversion
    val l1: Long = a.toLong()

    //string type
    var chr: Char = 'A'
    val str: String = "Hello"
    val newLine = "Hello\nDoing well?"
    val multiLineSimple = "Hello" +
            "How are you doing"
    val multiLineConcise = """
       Hello
       How are you doing there
    """
    //string interpolation
    val yrs = 10
    val message = "A decade is $yrs"
    //accessing method of the values in string interpolation
    val name = "Judi"
    val nameLength = "$name length = ${name.length}"
}
