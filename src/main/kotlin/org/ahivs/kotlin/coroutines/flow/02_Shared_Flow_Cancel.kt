package org.ahivs.kotlin.coroutines.flow

import kotlinx.coroutines.channels.ConflatedBroadcastChannel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

private var send = true

fun main() {
    channelTest()
}

private fun channelTest() = runBlocking {
    val channel = ConflatedBroadcastChannel<Unit>()
    val closed = channel.asFlow().take(1)

    launch {
        closed.collect{
            println("Collect1")
        }
    }

    launch {
        closed.collect{
            println("Collect2")
        }
    }

    channel.send(Unit)
    channel.send(Unit)
    delay(500)
    channel.send(Unit)
    channel.close()
}

private fun flowTest() = runBlocking {
    val flow1 = MutableSharedFlow<Unit>(replay = 3)
    flow1.emit(Unit)
    flow1.emit(Unit)
    val flow = flow1.takeWhile { send }
    launch {
        flow.collect {
            println("Collected1")
        }
    }

    launch {
        flow.collect {
            println("Collected2")
        }
    }
    delay(500)
    flow1.emit(Unit)
    send = false

}