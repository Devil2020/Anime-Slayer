package com.morse.animeslayer.flow_study.linear

import android.util.ArrayMap
import android.util.ArraySet
import android.util.SparseArray
import com.morse.animeslayer.flow_study.datatstructure.printLn
import java.util.*
import java.util.concurrent.ArrayBlockingQueue
import kotlin.collections.ArrayDeque
import kotlin.collections.ArrayList

fun main2 () {
    // java
    val a1 = Array(10){}
    val a2 = ArrayDeque<Int>()
    val a3 = ArrayList<Int>()
    val a6 = ArrayBlockingQueue<Int>(10)
    val a7 = SparseArray<Int>()
    //  Android
    val a4 = ArrayMap<Int , String>()
    val a5 = ArraySet<Int>()

    // Utils for it java

/*    java.util.Arrays.asList()
    java.util.Arrays.binarySearch()
    java.util.Arrays.copyOf()
    java.util.Arrays.copyOfRange()
    java.util.Arrays.fill()
    java.util.Arrays.deepToString()
    java.util.Arrays.deepEquals()
    java.util.Arrays.sort()
    java.util.Arrays.setAll()*/

}


fun main () {

    val v1 = Vector<Int>()
    v1.addElement(1)
    v1.addElement(2)
    v1.addElement(3)
    v1.addElement(4)
    v1.addElement(5)

    v1.get(0).printLn()

    var v2 =Vector<Int> ()
    v2 = v1


    val a1 = Array<Int>(10){ 0 }
    a1.set(0,0)
    a1.set(1,1)
    a1.set(2,2)
    a1.set(3,3)
    a1.set(4,4)
    a1.set(5,5)

    a1.get(1).printLn()

    var a2 =Array<Int> (10){0}
    a2 = a1
    a2[1].printLn()
}