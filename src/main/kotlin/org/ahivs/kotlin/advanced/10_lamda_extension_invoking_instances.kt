package org.ahivs.kotlin.advanced.lambda.invoking

//THIS IS NOT COMPLETE, LEAVING IT AS THIS LIKE NOW
//NEED TO HAVE MORE CONCRETE EXAMPLE TO WORK WITH
class Request(val method: String, val query: String)
class Status(var code: Int, var description: String)
class Response(var contents: String, var status: Status) {
    operator fun invoke(statusExtFn: Status.() -> Unit) {
        status.statusExtFn()
    }
}

class RouteHandler(val request: Request) {
    fun response(respExtFn: Response.() -> Unit): Response {
        val response = Response("Dummy",
                Status(200, "Dummy Descriptions"))
        response.respExtFn()
        return response
    }


}

fun routeHandler(path: String, f: RouteHandler.() -> Unit): RouteHandler.() -> Unit = f

fun main() {
    routeHandler("/index.html") {

        if (request.query == "hello") {
            response {
                contents = "GREETING"

            }
        }
    }
}
