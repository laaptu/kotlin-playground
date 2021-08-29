package org.ahivs.kotlin.advanced.lambdaextension

class Book {
    val type = "HardCover"
    fun read() {
        println("I am reading a $type book")
    }
}

//Simple extension function
fun Book.print() {
    read()
    println("I am printing a $type book")
}

//Lambda extension function
fun print(extFn: Book.() -> Unit) {
    //if we have to invoke the extension function,
    // we need to have the object of the book
    //extFn.invoke(Book())
    //OR
    val book = Book()
    book.extFn()
}

fun testingLambdaExtensionFunction() {
    print {
        read()
        println("I am printing a $type book")
    }
}

fun main() {
    //testingLambdaExtensionFunction()
    testingBuildString()
}


fun buildString(extFn: StringBuilder.() -> Unit): String {
    val stringBuilder = StringBuilder()
    //to invoke extension function we need object of receiver or type i.e.
    //StringBuilder
    stringBuilder.extFn()
    return stringBuilder.toString()
}

fun testingBuildString() {
    val str = buildString {
        append("a")
        append("b")
    }
    println(str)
}


class Table {
    val td = TD()

    init {
        println("I am creating table")
    }

    fun td(tdExtFn: TD.() -> Unit) {
        td.apply { tdExtFn }
    }
}

class TD {
    init {
        println("I am creating TD")
    }

    var totalDivision = 10
    fun tr(trExtFn: TR.() -> Unit) {
        TR().trExtFn()
    }
}

class TR {
    init {
        println("I am creating TR")
    }

    var totalRows = 5
}

fun table(tableExtFn: Table.() -> Unit): Table = Table().apply { tableExtFn }

fun testingDSL() {
    val table = table {
        td {
            totalDivision = 20
            tr {
                totalRows = 5
            }
        }
    }
}
