package com.morse.animeslayer.flow_study

import kotlinx.coroutines.*
import kotlinx.coroutines.channels.consume
import kotlinx.coroutines.channels.produce
import kotlin.system.measureTimeMillis

fun main0() {
    val time = measureTimeMillis {
        runBlocking {
            for (i in 1..2) {
                launch {
                    // So No Concurrancy happened it will took 2 seconds for finishing 2 tasks >> Solution is give a new CoroutineContext
                    // Launch function will take the coroutine context already from there runBlocking
                    work(i)
                }
            }
        }
    }
    println("Done in $time ms")
}


fun main1() {
    val time = measureTimeMillis {
        runBlocking {
            for (i in 1..2) {
                launch(Dispatchers.IO) {    // Launch function will take the coroutine context already from there runBlocking
                    work(i)
                }
            }
        }
    }
    println("Done in $time ms")
}

// what if i use GlobalScope instead


fun main2() {
    val time = measureTimeMillis {
        runBlocking {
            for (i in 1..1) {
                val job = GlobalScope.launch {    // IT will not show any print
                    work(i)
                }
                job.join()
            }
        }
    }
    println("Done in $time ms")
}


fun main4() {
    val time = measureTimeMillis {
        runBlocking {
            val jobs = mutableListOf<Job>()
            for (i in 1..4) {
                jobs += CoroutineScope(Dispatchers.IO).launch {   // when we use Unconfined , the 4 service will take 4 seconds for finishinhg
                    work(i)
                }
                launch {
                    work(i)
                }
            }
            jobs.forEach { it.join() }
        }
    }
    println("Done in $time ms")
}

fun main() {
    val time = measureTimeMillis {
        runBlocking {
            GlobalScope.launch {
                delay(100)
                println("Work 1 done")
            }

            GlobalScope.launch {
                delay(100)
                println("Work2 done")
            }.join()
        }
    }
    println("Done in $time ms")
}

fun work(i: Int) {
    Thread.sleep(1000)
    println("Work $i done")
}

fun main8() =
    runBlocking {

        produce {
            send("Mohamed Morse")
        }.consume {
            println(">>>>>>>>>>>>>>>>>> Print Incomming Data 🔥 : ${this.receive()}")
        }

        coroutineScope {
            work(1)
            work(2)
            work(3)
        }
    }

/*  So Finally GlobalScope bad because

 * A global [CoroutineScope] not bound to any job.
 *
 * Global scope is used to launch top-level coroutines which are operating on the whole application lifetime
 * and are not cancelled prematurely.
 * Another use of the global scope is operators running in [Dispatchers.Unconfined], which don't have any job associated with them.
 *
 * Application code usually should use an application-defined [CoroutineScope]. Using
 * [async][CoroutineScope.async] or [launch][CoroutineScope.launch]
 * on the instance of [GlobalScope] is highly discouraged.

 So the solution to be better Structured concurrency , we can already launch to pre defined scope to manage its jops and join them already .

Summary :-

    if we create a new coroutine scope and then launch it , we must manage their jobs and start join them OR GlobalScope

    if we use structeredConcureeny to lauch new Coroutine , no need for handling their job .

 */