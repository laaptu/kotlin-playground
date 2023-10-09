package org.ahivs.kotlin.testing

object Extensions {
    fun Employee.isIdValid() = id > 0
    fun Employee.getStartEndTime():Pair<String,String> = id.toString() to name
}