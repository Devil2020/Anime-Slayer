package com.morse.animeslayer.flow_study.flow

import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.runBlocking

fun main() {

    runBlocking {

        val shareFlow = MutableSharedFlow<String>(0)
        
        shareFlow.collect {

            println(" The Total Count of Subscribtions is ${shareFlow.subscriptionCount}")
        }

        shareFlow.collect {

            println(" The Total Count of Subscribtions is ${shareFlow.subscriptionCount}")
        }

        shareFlow.collect {

            println(" The Total Count of Subscribtions is ${shareFlow.subscriptionCount}")

        }

        shareFlow.emit("20")

        println(" The Total Count of Subscribtions is ${shareFlow.subscriptionCount}")
        
    }
}
