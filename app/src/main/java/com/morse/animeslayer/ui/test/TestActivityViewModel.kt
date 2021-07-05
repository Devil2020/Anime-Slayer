package com.morse.animeslayer.ui.test

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.*

/*


What i learning when i use  MutableSharedFlow in ViewModdel to post events in main Activity ?

    >> Start it from viewmodel it self in init , Not in Activity because it will create a new reference
     and will start from start and the old reference will continue to emit


*/


/*
class TestActivityViewModel : ViewModel() {


    private val newFlow = MutableStateFlow(-1)
    val getNews: Flow<Int> = newFlow.apply {
        onEmpty { Log.i("yamany" , "onEmpty Called" ) }
        onStart { Log.i("yamany" , "onEmpty Called" ) }
        onSubscription { Log.i("yamany" , "onEmpty Called" ) }
        onCompletion { Log.i("yamany" , "onEmpty Called" ) }
    }


    init {
//        pushNews()
    }

    fun pushNews() {
        viewModelScope.launch {
            repeat(10) {

                Log.i("Mohammed-Viewmodel-Log" , " The Reference of newFlow is ${newFlow.hashCode()}")
                Log.i("Mohammed-Viewmodel-Log" , " The Reference of getNews is ${getNews.hashCode()}")
                newFlow.emit(it)
                Log.i("Mohammed-Viewmodel-Log" , "The CurrentValue is $it")
                delay(1000)

            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        Log.i("TestActivityViewModel" , "onCleared ViewModel")
    }

}*/


// -------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

fun <U> Flow<U>.ofType(clazz: Class<U>?): Flow<U> {
    if (clazz == null) {
        throw NullPointerException()
    }
    return this
}


fun useCaseForLoadingTopRatedAnime() = flow {
    repeat(100) {
        emit(it)
    }
}

val topRatedAnnotateprocessor1 = flowOf("Loading").flatMapLatest {
    useCaseForLoadingTopRatedAnime()
        .onStart {
            emit(100)
        }
        .catch { emit(-100) }
        .onEmpty { emit(0) }
        .onEach {  }
}

val topRatedAnnotateprocessor2 = flow {
    useCaseForLoadingTopRatedAnime()
        .onStart {
            this@flow.emit("Loading")
        }
        .catch { this@flow.emit("Error Catched") }
        .onEmpty { this@flow.emit("Empty") }
        .onEach { this@flow.emit("Success") }
}


class TestActivityViewModel : ViewModel() {

    val aFragmentIntent = MutableSharedFlow<AFragmentIntents>()

    val flow1 = MutableSharedFlow<Int>(5)
    val flow2 = MutableSharedFlow<Int>(5)
    val flow3 = MutableSharedFlow<Int>(5)

    val fff = flow<String> {
    //       merge(this.o)
    }
}

