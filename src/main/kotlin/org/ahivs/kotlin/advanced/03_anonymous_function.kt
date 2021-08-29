package org.ahivs.kotlin.advanced.anonymous

fun operate(a: Int, action: (Int) -> Int): Int {
    return action(a)
}

fun main() {
    //lambda expression, where the type is inferred i.e. we don't have explicity say something about it
    //and as a result we cannot add multiple return types as shown in the commented portion
    operate(20) {
//        if(it>10)
//            return it*10
        it * it
    }

    //anonymoust function, we we define the function with fun keyword but without a name
    //and gives us flexibility to define multiple return statements
    operate(20, fun(x: Int): Int {
        if (x > 20)
            return x * 2
        else
            return x * x
    })
}

