package org.ahivs.kotlin.advanced

import kotlin.contracts.ExperimentalContracts
import kotlin.contracts.contract

sealed class User {

    class Authenticated : User() {
        fun getAccessToken() = "AccessToken123"
    }

    class Guest : User() {
        fun requestToSignUp() {
            println("You need to sign up")
        }
    }

    class UnknownType : User() {

    }

//    fun isAuthenticated(): Boolean = this is User.Authenticated

    @OptIn(ExperimentalContracts::class)
    fun isAuthenticated(): Boolean {
        // compiler communication code
        contract {
            // EFFECT              ->    CONDITION
            // when value is true -> we are making compiler understand how to treat this object as Authenticated
            returns(true) implies (this@User is Authenticated)
            // when value is false -> we are making compiler understand how to treat this object as Guest
            returns(false) implies (this@User is User.Guest)
            // returns (true,false,null) --->  implies ( is Type, !is Type, ==null, !=null) : Type and null checks only
            //                                 can't access the function of the particular type
        }
        return this is Authenticated
    }

}

fun performUserActionWithContract(user: User) {
    if (user.isAuthenticated()) {
        user.getAccessToken()
    } else {
        user.requestToSignUp()
    }
}

fun main() {
    val user = User.UnknownType()
    performUserActionWithContract(user)
}

data class Request(val body: String)
class APIService {
    fun process(request: Request?) {
        if (validate(request)) {
            println(request.body)
        }
    }

    @OptIn(ExperimentalContracts::class)
    private fun validate(request: Request?): Boolean {
        contract {
            returns(true) implies (request != null)
        }
        if (request == null)
            throw IllegalArgumentException("Undefined request")
        if (request.body.isBlank())
            throw IllegalArgumentException("Body is not provided")
        return true
    }
}