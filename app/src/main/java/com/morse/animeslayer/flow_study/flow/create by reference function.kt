package com.morse.animeslayer.flow_study.flow

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.runBlocking

fun Flow<Any>.showHashCode() {
    println("The Hash Code is ${this.hashCode()}")
}

fun main() {

    runBlocking {

        val repository = NetworkLayerWithReference(this)

        async {

            val flow1 = repository.executeGetMoviesApi()

            flow1.showHashCode()

            flow1.collect {

                println(" << NetworkLayerWithReference >> The Flow #1 data is ${it}")

            }
        }

        println("======================================================================")

        async {


            val flow2 = repository.executeGetMoviesApi()

            flow2.showHashCode()

            flow2.collect {

                println(" << NetworkLayerWithReference >> The Flow #2 data is ${it}")

            }
        }

        println("======================================================================")

        // ======================================================================

        val repository3 = NetworkLayerWithoutReference()

        async {



            val flow3 = repository3.executeGetMoviesApi(this)

            flow3.showHashCode()

            flow3.collect {

                println(" << NetworkLayerWithoutReference >> The Flow #1 data is ${it}")

            }

        }

        println("======================================================================")

        async {

            val flow4 = repository3.executeGetMoviesApi(this)

            flow4.showHashCode()

            flow4.collect {

                println(" << NetworkLayerWithoutReference >> The Flow #2 data is ${it}")

            }

        }

        println("======================================================================")
    }


}

interface Network

class NetworkLayerWithReference constructor(coroutineScope: CoroutineScope) : Network {

    private val moviesFlow = flow {

        emit("Mohammed")

        kotlinx.coroutines.delay(1000)

        emit("Morse")

        kotlinx.coroutines.delay(1000)

        emit("01119551454")

    }.shareIn(coroutineScope, SharingStarted.Eagerly, 3)

    fun executeGetMoviesApi() = moviesFlow

}


// New Flow Returned Each Time

class NetworkLayerWithoutReference constructor() : Network {


    fun executeGetMoviesApi(coroutineScope: CoroutineScope) = flow {

        emit("Mohammed")

        kotlinx.coroutines.delay(1000)

        emit("Morse")

        kotlinx.coroutines.delay(1000)

        emit("01119551454")

    }.shareIn(coroutineScope, SharingStarted.Eagerly, 3)

}