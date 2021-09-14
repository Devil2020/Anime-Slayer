package com.morse.animeslayer.flow_study.flow

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.buffer
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.zip
import kotlinx.coroutines.runBlocking

/*

    Zip Operator is used for geting flowNumber1 and flowNumber2 and i can make change of their result as
    if i flow of phone number as 01119551454 , 01124130001 and a flow of name as Morse , Heba  , in transformation function i can make the result flow of string
    with value is "The Phone Number of ${phoneNumber} belongs to ${name}"

    > But it have only one issue is will terminate if one of the two flows finish before other

    Combine Operator work like Zip Operator but have only one difference ,is when one flow length is smaller than nother flow the zib operator
    will pause when the smallest size of items finish , but in Compine operator will not pause ever , it will continue and will repeat
    to the last item until finish like example below .

*/

fun main() {

    runBlocking {

        flowD().zip(flowE()) { income1: Int, income2: Int ->
            printMessage(
                0,
                "* - Zip :[$income1 , $income2] The Result is ${income1 + income2}"
            )
            " * - Zip :[$income1 , $income2] The Result is ${income1 + income2}"
        }
            .buffer(1000).collect {
                printMessage(1, " The Result is $it ")
            }


        delay(600)

        println("-----------------------------------------------------------------------------------")

        flowG()
            .combine(flowF()) { a: Int, b: Int ->
                printMessage(
                    2,
                    "* - Combine : [$a,$b] the Result of Both Value Calculation is ${a * b}"
                )
                a * b
            }.collect {

                printMessage(3, "* - Combine Collect : The Result of Both Value Calculation is ${it}")
            }
    }

}