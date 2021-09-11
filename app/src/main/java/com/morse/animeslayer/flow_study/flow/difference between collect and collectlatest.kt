package com.morse.animeslayer.flow_study.flow

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking

/*
*  The Main Difference Between Collect and CollectLatest ?
*       -> is in CollectLatest when the function triggered it will cancel the previous one and execute the new one
*       look on the sample below to undestand
*
*
*
* */

fun main() {

    runBlocking {

        flowC().collectLatest { value ->
            println("Collecting $value")
            delay(100) // Emulate work
            println("$value collected")
        }

        println("---------------------------------------------")

        delay(400)

        flowC().collect { value ->
            println("Collecting $value")
            delay(100) // Emulate work
            println("$value collected")
        }


    }

}

fun flowC() = flow<Int> {
    emit(1)
    delay(50)
    emit(2)
}