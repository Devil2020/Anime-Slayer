package com.morse.animeslayer.flow_study.flow

import kotlinx.coroutines.flow.fold
import kotlinx.coroutines.flow.reduce
import kotlinx.coroutines.flow.scan
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.runBlocking

/*
   The First Section of this [ toList () ] ->  is a way for collect the flow and there we can get last and it is very important one ,

   The Second Section of this [Difference between Reduce and Scan and Fold] ?

        > The difference between them is the initial value , in scan we must add it to be the starter value , but in reduce

                it will use the first value in it to be the starter value .

        > Reduce will Return a Value not Flow , Scan will return Flow not Value .

        > For get last Value for scan we will use .toList().last()

        * - So  Reduce and Fold , the difference between  them is one will take an initial value and else will not take an initial value , Fold will have initial Value ,

        in both they will return a value not flow .

        * - So Scan have initial value but return Flow .

*/

fun main() {
    runBlocking {
        val a = flowC().reduce { accumulator, value ->
            value.plus(accumulator)
        }

        printMessage(1, "The Result of reduce is ${a}")

        // Equals

        val b = flowC().fold(50) { accumulator: Int, value: Int ->
            value.plus(accumulator)
        }

        printMessage(2, "The Result of fold ${b}")

        // Equals

        val c = flowC().scan(50) { accumulator: Int, value: Int ->
            value.plus(accumulator)
        }.toList().last()

        printMessage(3, "The Result of scan with conflate + first is ${c}")


    }
}