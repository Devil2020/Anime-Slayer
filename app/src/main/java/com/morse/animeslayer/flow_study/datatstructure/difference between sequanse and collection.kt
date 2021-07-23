package com.morse.animeslayer.flow_study.datatstructure

fun main() {


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

    val sequence = (0..200).asSequence()
        .filter {
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