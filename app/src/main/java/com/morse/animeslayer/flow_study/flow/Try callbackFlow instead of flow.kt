package com.morse.animeslayer.flow_study.flow

import kotlinx.coroutines.awaitCancellation
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.runBlocking

fun main () {

    runBlocking {
      val result =  tryCallbackFlow().collect{
            println("We Recieve data in ${Thread.currentThread().name}")
        }
    }

}


private fun tryCallbackFlow () = callbackFlow {
    kotlinx.coroutines.delay(1000)
    offer("Morse")
    println("We Send data in ${Thread.currentThread().name}")
    awaitClose{
        println("Await here")
    }
}