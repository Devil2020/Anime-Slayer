package com.morse.animeslayer.flow_study.flow

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.runBlocking

fun main() {
    runBlocking {
        returnANewFlow()
            .mapLatest {
                println("1- mapLatest The Value is ${it}")
                delay(200)
                println("2- mapLatest The Value is ${it}")
                it
            }.collect {
                println(" collect The Value is ${it}")
            }
    }


}


fun returnANewFlow() = flow {
    emit("a")
    delay(100)
    emit("b")
}