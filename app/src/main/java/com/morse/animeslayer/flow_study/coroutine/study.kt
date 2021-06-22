package com.morse.animeslayer.flow_study

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.runBlocking

fun main() = consumeFlowWithCollectIndexed()

fun consumeFlowWithCollectIndexed () = runBlocking {
    provideFlowWithEmittionMuch()
        //.flowOn(Dispatchers.IO) // not effect on SharedFlow
        .map {
            println("Current Thread is ${Thread.currentThread().name}")
            "The Value is $it"
        }
        //.flowOn(Dispatchers.Main) // not effect on SharedFlow
        .catch { cause: Throwable ->
            println("the cause is ${cause.localizedMessage}")
            println("Current Thread is ${Thread.currentThread().name}")
        }.collectIndexed { index, value ->
            println("Success with value ${value} And Current Thread is ${Thread.currentThread().name}")
        }
}

fun consumeFlowWithCatchOperator () = runBlocking {
    provideFlowWithEmittionMuch()
        //.flowOn(Dispatchers.IO) // not effect on SharedFlow
        .map {
            println("Current Thread is ${Thread.currentThread().name}")
            "The Value is $it"
        }
        //.flowOn(Dispatchers.Main) // not effect on SharedFlow
        .catch { cause: Throwable ->
            println("the cause is ${cause.localizedMessage}")
            println("Current Thread is ${Thread.currentThread().name}")
        }.collect {
            println("Success with value ${it} And Current Thread is ${Thread.currentThread().name}")
        }
}

fun provideFlowWithEmittionMuch() = flow {

    emit("A")

    kotlinx.coroutines.delay(5000)

    emit("B")

    kotlinx.coroutines.delay(5000)

    emit("C")

    kotlinx.coroutines.delay(5000)

    emit("D")

    kotlinx.coroutines.delay(5000)

    emit("E")

    kotlinx.coroutines.delay(5000)

    throw Exception("error here ")

    kotlinx.coroutines.delay(5000)

    emit("G")

    kotlinx.coroutines.delay(5000)

    emit("H")

    kotlinx.coroutines.delay(5000)

    emit("I")

    kotlinx.coroutines.delay(5000)

    emit("J")

    kotlinx.coroutines.delay(5000)

    emit("K")

    kotlinx.coroutines.delay(5000)

    emit("L")

    kotlinx.coroutines.delay(5000)

    emit("M")

    kotlinx.coroutines.delay(5000)

    emit("N")

    kotlinx.coroutines.delay(5000)

    emit("O")

    kotlinx.coroutines.delay(5000)

    emit("P")

    kotlinx.coroutines.delay(5000)

    emit("Q")

    kotlinx.coroutines.delay(5000)

    emit("R")

    kotlinx.coroutines.delay(5000)

    emit("S")

    kotlinx.coroutines.delay(5000)

    emit("T")

    kotlinx.coroutines.delay(5000)

    emit("U")

    kotlinx.coroutines.delay(5000)

    emit("V")

    kotlinx.coroutines.delay(5000)

    emit("W")

    kotlinx.coroutines.delay(5000)

    emit("X")

    kotlinx.coroutines.delay(5000)

    emit("Y")

    kotlinx.coroutines.delay(5000)

    emit("Z")

    kotlinx.coroutines.delay(5000)
}