package com.morse.animeslayer.flow_study

import kotlinx.coroutines.*
import kotlin.system.measureTimeMillis

// it will take 2 seconds for finish >> here structure concurrency it will take coroutine scope from run Blocking so advantage of concurrancy will removed
fun main6() {
    val time = measureTimeMillis {
        runBlocking {

            doSomeWorkHere()
            doSomeWorkHere()

        }
    }
    println("The Total time tooks is $time")
}

// it will take 2 seconds for finish >> here structure concurrency it will take coroutine scope from run Blocking so advantage of concurrancy will removed

fun main7() {
    val time = measureTimeMillis {
        runBlocking {
            for (i in 1..2) {
                coroutineScope {
                    doSomeWorkHere()
                }
            }
        }
    }
    println("The Total time tooks is $time")
}

fun main() {
    val time = measureTimeMillis {
        runBlocking {
            val jobs = mutableListOf<Job>()
            for (i in 1..2) {
                jobs +=CoroutineScope(Dispatchers.IO).launch {
                    doSomeWorkHere()
                }
            }
            jobs.forEach { it.join() }
        }
    }
    println("The Total time tooks is $time")
}



suspend private fun doSomeWorkHere() {
    delay(1000)
    println("Finish Work Here")
}

suspend fun returnValue () =20


/*  Summary >> we have 2 types for lauch coroutine 1- launch and it is no returned coroutine we didn`t  */