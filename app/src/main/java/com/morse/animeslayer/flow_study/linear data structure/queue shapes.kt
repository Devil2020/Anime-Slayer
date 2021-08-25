package com.morse.animeslayer.flow_study.linear

import java.util.*
import java.util.concurrent.*

fun main () {

    val q1 = PriorityQueue<Int>()
    val q2 = ArrayBlockingQueue<Int>(10)
    val q4 = DelayQueue<Delayed>()
    val q5 = LinkedTransferQueue<Int>()
    val q6 = LinkedBlockingQueue<Int>()
    val q3 = ConcurrentLinkedQueue<Int>()

}