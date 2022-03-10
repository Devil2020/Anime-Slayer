package com.morse.animeslayer.ui.TESTACTIVITY

import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.*

sealed class Intentions {
    object getNumbers : Intentions ()
    object retry : Intentions ()
    object swipeRefresh : Intentions ()
}

class TestViewModel : ViewModel() {

    private val flowA = flowOf("1Abc" , "1Def" , "1ghi" , "1jkl" , "1mno" , "1pqr")
    private val flowB = flowOf("2Abc" , "2Def" , "2ghi" , "2jkl" , "2mno" , "2pqr")
    private val flowC = flowOf("3Abc" , "3Def" , "3ghi" , "3jkl" , "3mno" , "3pqr")

   private val _stateFlow =
        flow {
            kotlinx.coroutines.delay(6000)
            emit(1)
            kotlinx.coroutines.delay(2000)
            emit(2)
            kotlinx.coroutines.delay(1000)
            emit(3)
            kotlinx.coroutines.delay(2000)
            emit(4)
            kotlinx.coroutines.delay(1000)
            emit(5)
            kotlinx.coroutines.delay(2000)
            emit(6)
            kotlinx.coroutines.delay(1000)
            emit(7)
            kotlinx.coroutines.delay(2000)
            emit(8)
            kotlinx.coroutines.delay(1000)
            emit(9)
            kotlinx.coroutines.delay(2000)
            emit(10)
            kotlinx.coroutines.delay(1000)
        }.shareIn(
            viewModelScope,
            SharingStarted.WhileSubscribed()
        )

/*    public val homeViewModelAnnotateProcessor = ObservableTransformer<HomeAction, HomeResult> {
        it?.publish {
            Observable.merge(
                it?.ofType(HomeAction.LoadPopularMovies::class.java)
                    .compose(popularMoviesAnnotateProcessor),
                it?.ofType(HomeAction.LoadTopRatedMovies::class.java)
                    .compose(topRatedMoviesAnnotateProcessor),
                it?.ofType(HomeAction.LoadIncomingMovies::class.java)
                    .compose(inComingMoviesAnnotateProcessor),
                it?.ofType(HomeAction.LoadNowPlayingMovies::class.java)
                    .compose(nowPlayingMoviesAnnotateProcessor)
            )
        }


    }*/

    private val result = _stateFlow

    fun executeIntention (intentions: Intentions) = when (intentions){
        is Intentions.getNumbers -> result
        is Intentions.retry -> result
        is Intentions.swipeRefresh -> result
    }

    fun executeIntentionWithTransfer (intentions: Flow<Intentions>) = intentions.transform<Intentions , String > {
        when (it){
            is Intentions.getNumbers -> flowA
            is Intentions.retry -> flowB
            is Intentions.swipeRefresh -> flowC
        }
    }


 /*   fun executeIntention (intentions: Intentions) = when (intentions){
        is Intentions.getNumbers -> flow {
            kotlinx.coroutines.delay(6000)
            emit(1)
            kotlinx.coroutines.delay(2000)
            emit(2)
            kotlinx.coroutines.delay(1000)
            emit(3)
            kotlinx.coroutines.delay(2000)
            emit(4)
            kotlinx.coroutines.delay(1000)
            emit(5)
            kotlinx.coroutines.delay(2000)
            emit(6)
            kotlinx.coroutines.delay(1000)
            emit(7)
            kotlinx.coroutines.delay(2000)
            emit(8)
            kotlinx.coroutines.delay(1000)
            emit(9)
            kotlinx.coroutines.delay(2000)
            emit(10)
            kotlinx.coroutines.delay(1000)
        }.shareIn(
            viewModelScope,
            SharingStarted.WhileSubscribed()
        )

        is Intentions.retry -> flow {
            kotlinx.coroutines.delay(6000)
            emit(1)
            kotlinx.coroutines.delay(2000)
            emit(2)
            kotlinx.coroutines.delay(1000)
            emit(3)
            kotlinx.coroutines.delay(2000)
            emit(4)
            kotlinx.coroutines.delay(1000)
            emit(5)
            kotlinx.coroutines.delay(2000)
            emit(6)
            kotlinx.coroutines.delay(1000)
            emit(7)
            kotlinx.coroutines.delay(2000)
            emit(8)
            kotlinx.coroutines.delay(1000)
            emit(9)
            kotlinx.coroutines.delay(2000)
            emit(10)
            kotlinx.coroutines.delay(1000)
        }.shareIn(
            viewModelScope,
            SharingStarted.WhileSubscribed()
        )

        is Intentions.swipeRefresh -> flow {
            kotlinx.coroutines.delay(6000)
            emit(1)
            kotlinx.coroutines.delay(2000)
            emit(2)
            kotlinx.coroutines.delay(1000)
            emit(3)
            kotlinx.coroutines.delay(2000)
            emit(4)
            kotlinx.coroutines.delay(1000)
            emit(5)
            kotlinx.coroutines.delay(2000)
            emit(6)
            kotlinx.coroutines.delay(1000)
            emit(7)
            kotlinx.coroutines.delay(2000)
            emit(8)
            kotlinx.coroutines.delay(1000)
            emit(9)
            kotlinx.coroutines.delay(2000)
            emit(10)
            kotlinx.coroutines.delay(1000)
        }.shareIn(
            viewModelScope,
            SharingStarted.WhileSubscribed()
        )

    }*/


}