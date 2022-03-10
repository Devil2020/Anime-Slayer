package com.morse.animeslayer.flow_study.flow

import kotlinx.coroutines.currentCoroutineContext
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.runBlocking

/*

    Map -> Change the value of Producer /  Emitter .

    MapLatest -> It will cancel the previous Map if the Emitter send item fast than mapLatest handle of map to new item shape >  you will get always the last item in collect for it .

    > the main differernce between map a his tree and flatmap and his tree is map will return value but flatmap will return flow .

    ------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

    Hint in General ::: flatMap and with it family it will return a Flow<Flow<Type>> this is in general and Both FlatMapConcat or flatMapMerge are a shortcut for muliple operator

    FlatMapConcat -> it will apply map first then apply flattenConcat why ? for convert Flow < Flow < Type > > to Flow<Type>  .

    FlatMapMerge -> it will apply map first then apply flattenMerge why ? for convert Flow < Flow < Type > > to Flow<Type>  .

    Difference between them ?

        -> is flatMapMerge (concurrencyNumber : Int){} apply flattenMerge Concurrency not map okay , but in flatMapConcat {} will run map and flattenConcat sequental .

        flattenMerge will work with unique behaviour depend on the Concurrency Number in General , so if it equal 0 , it will crach but if it equal 1 so it will work as flattenConcat

        else it will have a custom behaviour but in general will return ChannelFlowMerge and it is a big Story .

    In General :: we can use flatMapConcat or flatMapLatest to setup MVI in ViewModel .

*/

fun main() {

    runBlocking {

        println("--------------------------------Now run Map ---------------------------------------------")

        flowF().map {
            printMessage(0, " > Map Operator -> the incoming item is $it ")
            delay(100)
            "The Map Usgae for change Int to String and the real value is $it"
        }.collect {
            printMessage(1, it)
        }

        delay(1000)
        println("--------------------------------Now run MapLatest ---------------------------------------------")

        flowF().mapLatest {
            printMessage(2, " > Map Operator -> the incoming item is $it ")
            delay(100)
            "The Map Usgae for change Int to String and the real value is $it"
        }.collect {
            printMessage(3, it)
        }

        delay(1000)
        println("--------------------------------Now run FlatMapLatest ---------------------------------------------")

        flowF().flatMapLatest {
            printMessage(4, " > Map Operator -> the incoming item is $it ")
            delay(100)
            flowOf("The Map Usgae for change Int to String and the real value is $it")
        }.collect {
            printMessage(5, it)
        }


        delay(1000)
        println("--------------------------------Now run flatMapConcat ---------------------------------------------")

        flowF().flatMapConcat {
            printMessage(6, " > Map Operator -> the incoming item is $it ")
            when (it) {
                1 -> delay(100)
                2 -> delay(200)
                3 -> delay(20)
                4 -> delay(10)
                5 -> delay(250)
            }
            flowOf("The Map Usgae for change Int to String and the real value is $it")
        }.collect {
            printMessage(7, it)
            printMessage(
                -1,
                "The Current CoroutineContext is ${currentCoroutineContext().hashCode()}"
            )
        }

        delay(1000)
        println("--------------------------------Now run flatMapMerge ---------------------------------------------")

        flowF().flatMapMerge(40) {
            printMessage(8, " > Map Operator -> the incoming item is $it ")
            when (it) {
                1 -> delay(100)
                2 -> delay(200)
                3 -> delay(20)
                4 -> delay(10)
                5 -> delay(250)
            }
            flowOf("The Map Usgae for change Int to String and the real value is $it")
        }.collect {
            printMessage(9, it)
            printMessage(
                -2,
                "The Current CoroutineContext is ${currentCoroutineContext().hashCode()}"
            )
        }


    }

}