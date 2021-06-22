package com.morse.animeslayer.flow_study.flow

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.runBlocking

/*

   Before Any Thing Coroutine Flow runs in 3 stages :-

   1- Producer >>> so we produce the data by emit item .
   2- Intermediate >>> it is a stage after emit called and before consume / Collect .
   3- Consumer >>> In CollectBody Function .

    Okay , Now Flow by default run code in the same CoroutineContext that will collect on it , so we can change
*/

fun main (){

    runBlocking {

        runFlowWithCustomDispatcherMainThreadFlow().onEach {
            println("The Incoming data is ${it}")
        }.launchIn(this)

    }

}


fun runFlowInDefaultMainThreadFlow () = flow {
    delay(1000)
    println("The Current thread is ${Thread.currentThread().name}")
    emit("Mohammed morse")
}.flowOn(Dispatchers.IO)
    .map { 1 }


fun runFlowWithCustomDispatcherMainThreadFlow () = flow {
    delay(1000)
    println("The Current thread is ${Thread.currentThread().name}")
    emit("Mohammed morse")
}.flowOn(Dispatchers.IO)