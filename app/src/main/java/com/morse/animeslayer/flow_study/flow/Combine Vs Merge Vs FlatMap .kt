package com.morse.animeslayer.flow_study.flow

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.runBlocking

/*
*
*
*   Difference Between Merge and Compine ?
*       is when i make 2 flows with fixed length of numbers and merge them , so in onCollect with trigger
*       Twice , first for the First Flow and Second for the Second One .
*
*       But When i use Combine , the 2 Lists become only one List
*
*
*
* */


suspend fun main() {

    runBlocking {


        val flowA = flowA()
        val flowB = flowB()

        /*      println("Start flatMapLatest")

              flowA.flatMapLatest {
                  println("=================The Total Size is ${it.size}==============")
                  if (it.size == 5) {
                      return@flatMapLatest flowA
                  } else {
                      return@flatMapLatest flowB
                  }
              }.onStart { println("===============================") }
                  .onCompletion { println("============================") }
                  .collect {
                      delay(1000)
                      println(it)
                      delay(1000)
                  }*/

/*
        println("Start combine")
        flowA.combine(flowB) { a: List<Int>, b: List<Int> ->
            a + b
        }
            .onStart { println("===============================") }
            .onCompletion { println("============================") }
            .collect {
                delay(1000)
                println(it)
                delay(1000)
            }
*/

          println("Start Merge")

          merge(flowA, flowB)
              .onStart { println("---------------------------") }
              .onCompletion { println("---------------------------") }
              .collect {
                  delay(1000)
                  println(it)
                  delay(1000)
              }
     //   flowA.emit("FlowA New Value Emitted")

        delay(1000)
    }


}


suspend fun flowA() = MutableStateFlow((1..10).toList())

suspend fun flowB() = MutableStateFlow((1..6).toList())

/*
FlowA Value is 0FlowB Value is 0
FlowA Value is 1FlowB Value is 1
FlowA Value is 2FlowB Value is 2
FlowA Value is 3FlowB Value is 3
FlowA Value is 4FlowB Value is 4
FlowA Value is 4FlowB Value is 5
FlowA Value is 4FlowB Value is 6
FlowA Value is 4FlowB Value is 7
FlowA Value is 4FlowB Value is 8
FlowA Value is 4FlowB Value is 9*/




