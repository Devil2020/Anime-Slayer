
package com.morse.animeslayer.flow_study.flow
/*
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.runBlocking

fun <U> Flow<U>.ofType(clazz: Class<U>?): Flow<U> {
    if (clazz == null) {
        throw NullPointerException()
    }
    return this
}


fun useCaseForLoadingTopRatedAnime() = flow {
    repeat(100) {
        emit("  The Value is $it    ")
        kotlinx.coroutines.delay(1000)
        if (it == 50) {
            throw NullPointerException()
        }
    }
}

*/
/*val topRatedAnnotateprocessor1 = flowOf("Loading").flatMapLatest {
    useCaseForLoadingTopRatedAnime()
        .onStart {
            emit(100)
        }
        .catch { emit(-100) }
        .onEmpty { emit(0) }
        .onEach { }
}*//*


val topRatedAnnotateprocessor2 = useCaseForLoadingTopRatedAnime()
//.onEach { this.emit("Success") }

val topRatedAnnotateprocessor3 = emptyFlow<String>().ofType()

fun main() {
    println("Start")
    runBlocking {
        println("Start Blocking")
        topRatedAnnotateprocessor2.onStart {
            this.emit("Loading")
        }
            .catch { this.emit("Error Catched") }
            .onEmpty { this.emit("Empty") }
            .onEmpty { println("Empty") }
            .collect {
                println("The Result is $it")
            }
        println("End Blocking")
    }
    println("End")
}*/
