package org.ahivs.kotlin.basics.tuples

fun getCapitalAndPopulation(): Pair<String, Long> {
    return Pair("Canberra", 20000)
}

fun getCapitalContinentPopulation(): Triple<String, String, Long> = Triple("Canberra", "Australia", 20000)

fun main() {
    val result = getCapitalAndPopulation()
    println(result.first)
    println(result.second)

    val (capital, population) = getCapitalAndPopulation()
    println(capital)
    println(population)

    val (capital1, continent1, population1) = getCapitalContinentPopulation()
    println(capital1)
    println(continent1)
    println(population1)

    //deconstructing on country
    val country = Country("Canberra", 2000)
    //OR if it is data class only, won't run on normal class
    val (cap, pop) = Country("Canberra", 2000)
    println(cap)

    //deconstructing in list
    val capitalCountries = listOf(Pair("Kathmandu", "Nepal"), "Canberra" to "Australia")
    for ((capital, country) in capitalCountries)
        println("$country = $capital")
}

data class Country(val capital: String, val population: Long)
