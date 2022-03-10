package com.morse.animeslayer.flow_study.coroutine

import com.expertapps.base.extensions.launch
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking

fun main() {
/* The Child Job Cancelled When Parent Job is Canceled already , so parent manage childrens */
    runBlocking {
        val parentJob = launch {
            val childJob = launch {
                var count = 1
                while (count <= 5) {
                    println("Count: $count")
                    delay(100)
                    count++
                }
            }
        }
        delay(250)
        println("Cancelling parent job")
        parentJob.cancel()
    }

}