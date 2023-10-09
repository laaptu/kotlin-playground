package org.ahivs.kotlin

import java.util.Calendar

fun doSomeMath(add: (Int) -> Int) {
    add(20)
}

fun main() {
    doSomeMath {
        println(it * it)
        it * it
    }
}

data class AreaInfo(val areaId: Int)
data class ChildArea(
    var startTime: Calendar,
    val endTime: Calendar,
    val area: AreaInfo
)

sealed class Children {
    data class Rostered(val id: Int) : Children()
    data class Unscheduled(val id: Int) : Children()
}


data class ChildUITest<E : Children>(
    val areas: List<E>
)

data class ChildUIArea(
    val areaName: String,
    val time: String,
    val areaType: ChildAreaType,
    val areaAddress: String? = null,
    val hasError: Boolean = false
)

enum class ChildAreaType {
    NotWorked,
    Working,
    WorkingOver,
    Worked,
    ToBeWorked
}
