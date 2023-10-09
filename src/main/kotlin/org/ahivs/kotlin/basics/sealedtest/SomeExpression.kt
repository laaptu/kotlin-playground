package org.ahivs.kotlin.basics.sealedtest

import org.ahivs.kotlin.basics.sealedtest.Expr

class SomeExpression:Expr {

    fun test(expr: Expr){
        when(expr){
            is SomeExpression -> println("This is some expression")
            is NotANumber -> println("This is not a number")
            else -> println("Valid cases")
        }
    }
}