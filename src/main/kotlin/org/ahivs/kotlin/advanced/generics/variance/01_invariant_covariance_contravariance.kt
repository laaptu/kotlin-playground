package org.ahivs.kotlin.advanced.generics.variance

fun <T> doSomeGenericTask(item: T) {

}

class Box<T>(val item: T) {

}

open class Animal
class Dog : Animal()

fun invariant() {
    val animal = Animal()
    val boxAnimal = Box<Animal>(animal)
    val dog = Dog()
    val boxDog = Box<Dog>(dog)
}

class BoxIn<in T>() {
    fun putInBox(item: T) {

    }
    // compiler complains here as T should be input
    //fun takOutFromBox():T{}
}

fun inVariant() {
    val boxInAnimal = BoxIn<Animal>()
    val boxInDog: BoxIn<Dog> = boxInAnimal

    boxInDog.putInBox(Dog())
    // compiler complains here
    //boxInDog.putInBox(Animal())

    boxInAnimal.putInBox(Animal())
    boxInAnimal.putInBox(Dog())
}

class BoxOut<out T>(val item: T) {
    fun takeOutFromBox(): T {
        return item
    }
    // compiler complains here T is declared out, but taken as in
    //fun putInBox(item:T){}
}

fun outVariant() {
    val animal = Animal()
    val dog = Dog()

}

fun main(){
    val animals = arrayOf(Animal(),Animal())
    val dogs = arrayOf(Dog(),Dog())
    operate(animals)
    //operate(dogs)
}

fun operate(animals:Array<Animal>){

}