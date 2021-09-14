package com.morse.animeslayer.flow_study.flow

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.runBlocking

/*Changes the context where this flow is executed to the given context. This operator is composable and affects only preceding operators that do not have its own context. This operator is context preserving: contextdoes not leak into the downstream flow.

For example:

withContext(Dispatchers.Main) {
    val singleValue = intFlow // will be executed on IO if context wasn't specified before
        .map { ... } // Will be executed in IO
        .flowOn(Dispatchers.IO)
        .filter { ... } // Will be executed in Default
        .flowOn(Dispatchers.Default)
        .single() // Will be executed in the Main

        الخلاصه , انها بتاثر على كل الى قبليها مش الى بعديها ذى ال الر اكس
}*/

fun printThreadName (index : Int) {
    println()
    println("#Thread-Index[${index}] : Current Thread Name is ${Thread.currentThread().name}")
    println()
}

fun printMessage (index : Int , messageContent : String) {
    println()
    println("#Message-Index[${index}] :  ${messageContent}")
    println()
}

fun main() {

    runBlocking {
        val singleValue = flowC() // will be executed on IO if context wasn't specified before
            .map {
                printThreadName(1)
                "The Value is $it"
            } // Will be executed in IO
            .flowOn(Dispatchers.IO)
            .filter {
                printThreadName(2)
                !it.equals("The Value is 1")
            } // Will be executed in Default
            .flowOn(Dispatchers.Default)
            .single() // Will be executed in the Main


        delay(50)

        printMessage(0 , "The SingleValue is ${singleValue}")
    }
}


fun main_ (){
    fun main() {
        runBlocking {
            val a = flowC().filter {
                it != 1
            }.catch {
                printMessage(1, "The Error Message is ${it.localizedMessage}")
            }
                .single()
            printMessage(1, "The Result of Single is ${a}")
        }
    }
}