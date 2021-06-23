package com.morse.animeslayer.flow_study.flow

import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*
import kotlin.system.measureTimeMillis

/*Tip for Android apps! You can use WhileSubscribed(5000) most of the time to keep the upstream flow active for 5 seconds more after
the disappearance of the last collector. That avoids restarting the upstream flow in certain situations such as configuration changes.
 This tip is especially helpful when upstream flows are expensive to create and when these operators are used in ViewModels.*/


// Diference between State Flow and Share Flow  >>> In StateFlow we can access the last emited value syncronuslly by .value and
// watch the buffer of list values with defaul size 64 item in list

fun main11() {
    val time = measureTimeMillis {
        runBlocking {
           val job1 =  GlobalScope.launch {
                val flow1 = createANewFlow(this)
                println("The HashCode of First Flow is ${flow1.hashCode()}")
                flow1.collect {
                    println("The Income Data from Flow1 is ${it}")
                }
            }
            job1.cancelAndJoin()
            val job2 =  GlobalScope.launch {
                val flow2 = createANewFlow(this)
                println("The HashCode of Second Flow is ${flow2.hashCode()}")
                flow2.onEach {
                    println("The Income Data from Flow2 is ${it}")
                }.launchIn(this)
            }
            job2.cancelAndJoin()
        }
    }
    println("The total time in execution is $time")
}

val flow =  flow {
    // kotlinx.coroutines.delay(1000)
    emit("Morse")
} // each new collector that not subscribed before items pushed ,
// give them the last 10 items you saved in the buffer

fun createANewFlow(coroutineScope: CoroutineScope) = flow.shareIn( coroutineScope ,SharingStarted.Eagerly , 10) //  use the SharingStarted.Eagerly policy to listen for updates even if there are no collectors.



/*

 NEVER use shareIn or stateIn to create a new flow that’s returned when calling a function.
 That’d create a new SharedFlow or StateFlow on each function invocation that will remain in memory until the scope is cancelled or is garbage collected when there are no references to it.

class UserRepository(
    private val userLocalDataSource: UserLocalDataSource,
    private val externalScope: CoroutineScope
) {
    // DO NOT USE shareIn or stateIn in a function like this.
    // It creates a new SharedFlow/StateFlow per invocation which is not reused!
    fun getUser(): Flow<User> =
        userLocalDataSource.getUser()
            .shareIn(externalScope, WhileSubscribed())

    // DO USE shareIn or stateIn in a property
    val user: Flow<User> =
        userLocalDataSource.getUser().shareIn(externalScope, WhileSubscribed())
}

*/


// <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<FINALLY>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>

/*   State Flow is a kind of optimization on Share Flow , because SharedFlow inherit from Flow and StateFlow is Inherit from ShareFlow*/
/*   We use StateFlow for caching Mecanism only                                                                                                                                */




fun main () {
   val job = runBlocking {
       val executor1 = ExecutorShared(this)
       println("The HashCode of First Flow is ${executor1.getFlow().hashCode()}")
       println("The HashCode of Second Flow is ${executor1.getFlow().hashCode()}")
       executor1.getFlow().onEach {
           println("The Message 1 is $it")
       }.launchIn(this)

       delay(4000)

       val listOfValues1 = executor1.getFlow().replayCache

       listOfValues1.forEach { println("The Cached Data 1 is $it") }

       delay(4000)

       val executor2 = ExecutorStated(this)

       executor2.getFlow().onEach {
           println("The Message 2 is $it")
       }.launchIn(this)

       delay(4000)

       val listOfValues2 = executor2.getFlow().replayCache

        listOfValues2.forEach { println("The Cached Data 2 is $it") }
   }
}

class ExecutorShared (private val coroutineScope: CoroutineScope){

    private val flow = flow {

        delay(1000)

        emit("Mohammed")

        delay(1000)

        emit("Ahmed")

        delay(1000)

        emit("Salma")

    }.shareIn(coroutineScope , SharingStarted.Eagerly , 2)

    fun getFlow () = flow

}

class ExecutorStated (private val coroutineScope: CoroutineScope){

    private val flow = flow {

        delay(1000)

        emit("Mohammed")

        delay(1000)

        emit("Ahmed")

        delay(1000)

        emit("Salma")

    }.stateIn(coroutineScope , SharingStarted.Eagerly , "Start")

    fun getFlow () = flow

}


// This is an Example for subscribe and git the last item for reply already