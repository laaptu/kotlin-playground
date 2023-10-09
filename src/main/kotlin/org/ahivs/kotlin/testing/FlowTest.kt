import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.runBlocking

fun main() = runBlocking {
    val flow1: Flow<Int> = flow {
        emit(1)
        delay(1000)
        emit(2)
        delay(1000)
        emit(3)
    }

    val flow2: Flow<Int> = flow {
        delay(500)
        emit(100)
        delay(500)
        emit(200)
        delay(500)
        emit(300)
    }

    val flow3: Flow<Int> = flow1.combine(flow2) { valueFromFlow1, valueFromFlow2 ->
        if (valueFromFlow2 != null) {
            valueFromFlow2
        } else {
            valueFromFlow1
        }
    }

    flow3.collect { value ->
        println(value)
    }
}
