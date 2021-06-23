package com.morse.animeslayer.flow_study.flow

import kotlinx.coroutines.async
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.runBlocking

/*


 we have 3 options for laucnh a coroutine scope in Ui okay ?
    1- use lifecycleScope.launch in both [fragment or activity]
    2- use lifeCycleScope.launchWhenX in both [ fragment or Activity ]
    3- use lifeCycle.repeatOnLifeCycle


    Disadvantage of all types :-

    1- use lifecycleScope.launch -> the view will recieve the updates until destroy so the
    Producer and it will be network layer to ui will be active and take resources from Ram .

    2- lifeCycleScope.launchWhenX -> view collection will not active so will not recieve any thing
    but the Producer will be active and still take resources from Ram .

    3- lifeCycle.repeatOnLifeCycle -> view collection will suspend and also the producer for it



*/

fun main() {
    val flow = MutableStateFlow(10)
    runBlocking {
        async {
            flow.
            onCompletion {
                println("Finish 1")
            }
                .collect {
                println("Consumer 1 is ${it}")
            }
        }

        async {
            flow.
            onCompletion {
                println("Finish 1")
            }
                .collect {
                println("Consumer 2 is ${it}")
            }
        }

        async {
            flow.subscriptionCount.collect {
                println("Consumers Count is ${it}")
            }
        }
    }

}