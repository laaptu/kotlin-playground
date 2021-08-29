package org.ahivs.kotlin.basics

interface Repository {
    fun getRepo()

    //interfaces in kotlin can have default method which is different from that of Java
    fun printRepo() {
        println("I am printing the repo")
    }

    //but it cannot be initialized as
    // val repoName:String = "Hello"
    val repoName: String
        get() = "Hello"

    val repoVersion: Int
}

class BookRepository : Repository {

    //need to override this value or provide this value as constructor parameter
    override val repoVersion: Int
        get() = 20

    override fun getRepo() {
        println(" I am getting the repo")
    }

    //overriding the function of interface
    override fun printRepo() {
        super.printRepo()
    }
}

interface Interface1 {
    fun printName(){
        println("Printing for Interface1")
    }
}

interface Interface2 {
    fun printName(){
        println("Printing for Interface2")
    }
    fun getVersion(): Int
}

class DoubleInterface : Interface1, Interface2 {

    override fun getVersion(): Int {
       return 20
    }

    override fun printName() {
        //here it is unclear which of Interface1 or Interface2
        //so we are explicitly saying to call for interface1
        super<Interface1>.printName()
    }

}
