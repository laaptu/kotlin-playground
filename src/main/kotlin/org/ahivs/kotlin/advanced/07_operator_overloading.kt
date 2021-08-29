package org.ahivs.kotlin.advanced

data class Tree(val type: String, val age: Int) {
    operator fun plus(tree: Tree): Tree {
        val newType = type + tree.type
        val newAge = age + tree.age
        return Tree(newType, newAge)
    }
}

operator fun Tree.minus(tree: Tree): Tree {
    val diff = type.length - tree.type.length
    val newType = type.substring(diff)
    val newAge = age - tree.age
    return Tree(newType, newAge)
}

fun main() {
    val tree = Tree("Green", 20) - Tree("Red", 30)
    println(tree)


}
