package org.ahivs.kotlin.basics.sealedtest

sealed class God

sealed class Urgency(open val level: Int) {
    class Normal(val type: String) : Urgency(10)
    object Express : Urgency(20)
    data class Urgent(val speed: Int, override val level: Int) : Urgency(level)
}

sealed interface Expr
data class Const(val number: Double) : Expr
data class Sum(val e1: Expr, val e2: Expr) : Expr
object NotANumber : Expr