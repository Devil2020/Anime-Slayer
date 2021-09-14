package com.morse.animeslayer.flow_study.flow

import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.currentCoroutineContext
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.buffer
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.conflate
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.runBlocking
import kotlin.system.measureTimeMillis

/*                                          [ Buffer ]

*  It buffers flow emissions via channel of a specified capacity and runs collector in a separate coroutine,
  Normally, flows are sequential. It means that the code of all operators is executed in the same coroutine.
   Consider the following code using onEach and collect operators:
            flowOf("A", "B", "C")
                .onEach  { println("1$it") }
                .collect { println("2$it") }
            It is going to be executed in the following order by the coroutine Q that calls this code:

            Q : -->-- [1A] -- [2A] -- [1B] -- [2B] -- [1C] -- [2C] -->--

            So if the operator's code takes considerable time to execute, then the total execution time is going to be the sum of execution times for all operators.

            The buffer operator creates a separate coroutine during execution for the flow it applies to. Consider the following code:

            flowOf("A", "B", "C")
                .onEach  { println("1$it") }
                .buffer()  // <--------------- buffer between onEach and collect
                .collect { println("2$it") }
            It will use two coroutines for execution of the code. A coroutine Q that calls this code is going to execute collect, and the code before buffer will be executed in a separate new coroutine P concurrently with Q:

            P : -->-- [1A] -- [1B] -- [1C] ---------->--  // flowOf(...).onEach { ... }

                                  |
                                  | channel               // buffer()
                                  V

            Q : -->---------- [2A] -- [2B] -- [2C] -->--  // collect

            When the operator's code takes some time to execute, this decreases the total execution time of the flow.
            A channel is used between the coroutines to send elements emitted by the coroutine P to the coroutine Q.
            If the code before buffer operator (in the coroutine P) is faster than the code after buffer operator (in the coroutine Q),
            then this channel will become full at some point and will suspend the producer coroutine P until the consumer coroutine Q catches up.
            The capacity parameter defines the size of this buffer.

            By default, the emitter is suspended when the buffer overflows , so here we must add size for it .

            buffer(onBufferOverflow = BufferOverflow.DROP_LATEST )

            with Strategy when the buffer is full , is Suspend or dropLatest or what .

--------------------------------------------------------------------------------------------------------------------------------------------------------------------
                                          [ Conflate ]

*     Conflates flow emissions via conflated channel and runs collector in a separate coroutine.
      Here is the difference between Buffer and Conflate < The effect of this is that emitter is never suspended due to a slow collector >
      but collector always gets the most recent value emitted.

      so Conflate is a ShortCut of Buffer but with fixed buffersize and BufferFlowStrategy and they equal [ size -> 0 , strategy -> Drop_Oldest ] , so we always get latest item in the
      example belows .


----------------------------------------------------------------------------------------------------------------------------------------------------------------------

                                 [ Difference between buffer and Conflate ]

      Under the Hood conflate operator called buffer but with default values as buffer size is 0 so he will no cache any items if collector
      can`t process incomes data , plus on that is the strategy is DROP_OLDEST .

*/

fun main() {
    // Dispatchers.Unconfined
    val time = measureTimeMillis {
        runBlocking {
            println("-------------------------------------Test For is Running in Different Coroutine Context-------------------------------------------")
            flowC()
                .onEach {
                    println("OnEach triggered now ${it}")
                    delay(20)
                }
                .buffer(onBufferOverflow = BufferOverflow.DROP_LATEST)
                .collect {
                    println("Collect triggered now ${it}")
                    delay(250)
                }

            delay(5000)

            println("-----------------------------Usage of Conflate --------------------------------")

            flowF().conflate()
                .onEach {
                    printMessage(
                        0,
                        " > OnEach Reference: CoroutineContext is ${currentCoroutineContext().hashCode()}"
                    )
                    delay(1000)
                }.collect {
                    printMessage(
                        1,
                        " > collect Reference: CoroutineContext is ${currentCoroutineContext().hashCode()}"
                    )
                    printMessage(
                        2,
                        " > collect Value: The Value is ${it}"
                    )
                }

            delay(5000)

            println("----------------------------Convert Buffer to act as Conflate -----------------------------------------------")

            flowF().buffer(0, onBufferOverflow = BufferOverflow.DROP_OLDEST)
                .onEach {
                    printMessage(
                        3,
                        " > OnEach Reference: CoroutineContext is ${currentCoroutineContext().hashCode()}"
                    )
                    delay(1000)
                }.collect {
                    printMessage(
                        4,
                        " > collect Reference: CoroutineContext is ${currentCoroutineContext().hashCode()}"
                    )
                    printMessage(
                        5,
                        " > collect Value: The Value is ${it}"
                    )
                }

            delay(5000)

            println("------------------------------Test if Change Buffer Size What will Happen -----------------------------------------------")

            flowF().buffer(2, onBufferOverflow = BufferOverflow.DROP_OLDEST)
                .onEach {
                    printMessage(
                        6,
                        " > OnEach Reference: CoroutineContext is ${currentCoroutineContext().hashCode()}"
                    )
                    delay(1000)
                }.collect {
                    printMessage(
                        7,
                        " > collect Reference: CoroutineContext is ${currentCoroutineContext().hashCode()}"
                    )
                    printMessage(
                        8,
                        " > collect Value: The Value is ${it}"
                    )
                }

        }
    }
    println("=========================================================")
    println("The Total Time is ${time}")
}