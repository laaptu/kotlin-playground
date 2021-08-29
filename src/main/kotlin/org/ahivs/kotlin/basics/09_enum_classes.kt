package org.ahivs.kotlin.basics

enum class Priority {
    NORMAL,

    //overriding values
    EXPRESS {
        override fun toString(): String {
            return "SIMPLE EXPRESS"
        }
    },
    URGENT
}

enum class Urgency(val level: Int) {
    NORMAL(20),
    EXPRESS(30),
    URGENT(40)
}

enum class Speed {
    FAST {
        override fun getSpeed() = 100
    },
    SLOW {
        override fun getSpeed() = 20
    }; //this is the only place in kotlin where we need to write semi colons

    abstract fun getSpeed(): Int
}

fun main() {
    val priority = Priority.NORMAL
    val urgency = Urgency.NORMAL
    println(priority)
    println(priority.name)
    println(Priority.EXPRESS)
    println(urgency)
    println(urgency.level)
    //this prints the index of the Urgency
    println(urgency.ordinal)

    for (p in Priority.values())
        println(p)
}
