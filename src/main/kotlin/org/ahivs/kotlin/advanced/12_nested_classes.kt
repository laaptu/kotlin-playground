package org.ahivs.kotlin.advanced

class Directory {
    val name = "DIRECTORY"

    //this class cannot access the Directory member variables and function
    private class PrivateAccess {
        fun cannotAccess() {

        }
    }

    //this class cannot access the Directory member variables and function
    class PublicAccess {
        fun hasAccess() {

        }
    }

    //this class can access the Directory member variables and function
    inner class InnerAccess {
        fun access() {
            println("The name of directory = $name")
        }
    }

    //the outer class can access all the classes
    fun doListing() {
        val noAccess = PrivateAccess()
        noAccess.cannotAccess()
        val access = PublicAccess()
        access.hasAccess()
    }
}

fun main() {
    val director = Directory()
    val giveAccess = Directory.PublicAccess()
    //val noAccess = Directory.PrivateAccess()
    //val access = Directory.InnerAccess()
    val access = Directory().InnerAccess()
}
