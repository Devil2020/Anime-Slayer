package com.morse.animeslayer.flow_study.datatstructure

fun Int.printLn (){
    println(">> value is $this")
}

fun main() {

    val array = Array<Int>(20) { 0 }

    array.forEach {
        println("Array : Value is $it")
    }


    val hashmap = HashMap<Char, Int>()
    hashmap.getOrPut('a') {
        1
    }.printLn()

    hashmap.getOrPut('a') {
        1
    }.printLn()

}