package com.morse.animeslayer.flow_study.coroutine

import kotlinx.coroutines.*
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onStart

/*
fun main(arg: Array<String>) {
    println("Execution Starting ")
    println("---------------------------------------------")
    runBlocking { // this will block main thread
        launch {
            println("runblocking Started")
            delay(  1000) // delays of 1 Seconds
            println("runblocking Terminated")
        }
        println("---------------------------------------------")
        GlobalScope.launch { // global scope
            println("Global Scope Started")
            delay(500)
            println("Global Scope Terminated")
        }
        println("---------------------------------------------")
        coroutineScope {
            launch {
                println("Coroutine Scope Started")
                delay(1500)
                println("Coroutine Scope Terminated")
            }
        }
        println("---------------------------------------------")
        println("Execution Terminated")
    }
}*/

/*

// Suspend function working
fun main() {
    // application is not published
    // developer and QA can not Work Simultaneously
    // they need to work sequentially
    GlobalScope.launch {
        val millis = measureTimeMillis {
            val developerCount = getNumberDevelopers()
            val qaCount = getNumberQAEngineer()
            println("total team member seq ${developerCount + qaCount}")
        }
        println("time taken to complete seq $millis")
    }// above corountine will work sequentially
    // By Default they work like normal functions

    // application already published
    // New Bugs are getting found
    // Developer and QA Can Work Simultaneously
    GlobalScope.launch {
        val millis = measureTimeMillis {
            val developerCount =async { getNumberDevelopers() }
            val qaCount = async { getNumberQAEngineer() }
            println("total team member async ${developerCount.await() +  qaCount.await() }")
        }
        println("time taken to complete async $millis")

    } // above corountine will work simultaneously
    Thread.sleep(3000)
}

suspend fun getNumberQAEngineer(): Int {
    delay(1000) // we are getting in data of QA Engineers from DB
    return 23
}

suspend fun getNumberDevelopers(): Int {
    delay(1000) // we are getting in data of Developers Engineer from DB
    return 46
}*/


/*
fun main(arg: Array<String>) {

    runBlocking {

        returnMyStateFlow().collect {

            println(it)

        }
        println("------------------------------------------")

        returnMyStateFlow().collect {

            println(it)

        }


        returnMyStateFlow().collect {

            println(it)

        }

        returnMyStateFlow().collect {

            println(it)

        }


        delay(5000)

    }

    println("=============== End ==============")


}


fun returnMyStateFlow() = flow {

    repeat(1_000) {
        this.emit("The Value is $it")
    }

}*/
/*


fun main() {
    val testSharedFlow = MutableSharedFlow<String>()
    runBlocking {
        CoroutineScope(Job()).launch {
            testSharedFlow
                .onStart { println("start") }
                .collect { println(it) }
        }
    }

        testSharedFlow.tryEmit("a")
        testSharedFlow.tryEmit("b")

}

*/

suspend fun main() {
    val testSharedFlow = MutableSharedFlow<String>(extraBufferCapacity = 0)



    runBlocking {
        println("1- Send Items in ${Thread.currentThread().name} Thread")

            CoroutineScope(Job()).launch {

            println("Send Items in ${Thread.currentThread().name} Thread")
            testSharedFlow.emit("a")
            testSharedFlow.emit("b")

        }

        testSharedFlow
            .onStart { println(" 2- start in ${Thread.currentThread().name} Thread") }
            .collect { println(it) }


    }


}

/*
suspend fun main() {
    val testSharedFlow = MutableSharedFlow<String>(extraBufferCapacity = 0)
    runBlocking {
        CoroutineScope(Job()).launch {
            testSharedFlow
                .onStart { println("start") }
                .collect { println(it) }
        }
        delay(1000) // to give enough time for println to be executed before execution finishes
    }


    testSharedFlow.tryEmit("a").printValue()
    testSharedFlow.tryEmit("b").printValue()




}
*/

fun Boolean.printValue() = println("The Boolean Value is ${this}")
