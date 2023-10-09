package org.ahivs.kotlin.advanced.contracts

sealed class User {

    class Authenticated : User() {
        fun getAccessToken() = "AccessToken123"
    }

    class Guest : User() {
        fun requestToSignUp() {
            println("You need to sign up")
        }
    }
}


fun performUserAction(user: User) {
    if (user is User.Authenticated) {
        user.getAccessToken()
    } else  {
       // user.requestToSignUp()
    }
}


data class Request(val body: String)
class APIService {
    fun process(request: Request?) {
        if(validate(request)) {
            //println(request.body)
        }
    }

    private fun validate(request: Request?):Boolean {
        if (request == null)
            throw IllegalArgumentException("Undefined request")
        if (request.body.isBlank())
            throw IllegalArgumentException("Body is not provided")
        return true
    }
}


