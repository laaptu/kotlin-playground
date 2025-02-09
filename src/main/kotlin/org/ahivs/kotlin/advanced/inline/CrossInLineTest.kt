package org.ahivs.kotlin.advanced.inline

inline fun executePrint(printer: () -> Unit) {
    println("### ExecutingPrint For Inline")
    printer()
}

fun inlineTest() {
    executePrint {
        // though this return is meant for this local function
        // it acts as Non-local function and returns from inlineTest()
        // since inline are copied directly within this function
        // it's return is also copied
        println("### Inline Test1")
        return
    }
    println("### Inline Test2")
}

inline fun executePrintWithCrossInline(crossinline printer: () -> Unit) {
    println("### ExecutingPrint For crossInline")
    printer()
}

fun crossInlineTest() {
    executePrintWithCrossInline {
        println("### Crosss Inline Test1")
        // it doesn't allow return
        //return
    }
    println("### Cross Inline Test2")
}

fun main() {
    //inlineTest()
    //crossInlineTest()
    provideInputTest()
}

inline fun provideInput(inputProvider: (String) -> String): String {
    return inputProvider("Hello World")
}

private fun provideInputTest() {
    println("### ProvideInput Test1")
    val input = provideInput { input ->
        val data = input + "Adding this"
        println(data)
        data
    }
    println("### ProvideInput Test2")
}