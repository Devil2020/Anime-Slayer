package com.morse.animeslayer.flow_study.flow

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.scan
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

        println("---------------------------------------------")

        delay(400)

        flowC().scan(0) { accumulator: Int, value: Int ->
            value.plus(accumulator)
        }.collect {
            printMessage(3, "The Result of scan is ${it}")
        }

        println("---------------------------------------------")

        delay(400)

        flowC().scan(0) { accumulator: Int, value: Int ->
            value.plus(accumulator)
        }.collectLatest {
            printMessage(3, "The Result of scan is ${it}")
        }

    }

}

fun flowC() = flow {
    emit(1)
    delay(100)
    emit(2)
    delay(100)
    emit(3)
    delay(100)
    emit(4)
    delay(100)
    emit(5)
    delay(100)
    emit(6)
    printThreadName(0)
}