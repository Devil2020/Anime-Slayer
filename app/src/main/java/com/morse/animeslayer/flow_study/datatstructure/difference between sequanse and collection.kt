package com.morse.animeslayer.flow_study.datatstructure

import android.annotation.SuppressLint
import android.util.ArrayMap
import android.util.ArraySet
import android.util.SparseArray
import androidx.annotation.Size
import java.util.concurrent.ArrayBlockingQueue

fun main1() {


    val collection = (0..200)
        .filter {
            println("Collection : Filter with ${it} Called")
            it % 2 == 0
        }
        .map {
            println("Collection : Map with ${it} Called")
            it * 2
        }
        .take(2)

    println("===============================================================")

    val sequence = (0..200).asSequence().filter {
        println("Sequance : Filter with ${it} Called")
        it % 2 == 0
    }
        .map {
            println("Sequance : Map with ${it} Called")
            it * 2
        }
        .take(2)

    println("===============================================================")

    sequence.forEach { println("Sequance Result is ${it}") }

    println("===============================================================")

    collection.forEach { println("Collection Result is ${it}") }
}

@SuppressLint("UseSparseArrays")
fun main () {
    executeMe ("1").printMe()
    val a1 = Array(10){}
    val a2 = ArrayDeque<Int>()
    val a3 = ArrayList<Int>()
    val a4 = ArrayMap<Int , String>()
    val a5 = ArraySet<Int>()
    val a6 = ArrayBlockingQueue<Int>(10)
    val a7 = SparseArray<Int>()
}

fun String.printMe () {
    println(this)
}

@Size( min = 2, max = 10)
fun executeMe (stringInput: String) : String {
    return stringInput
}