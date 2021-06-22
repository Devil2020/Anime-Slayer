package com.morse.animeslayer.ui.activity

import android.util.Log
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow

sealed class Result {

    object Nothing : Result()

    data class NewDataComing (val myData : String) : Result ()

}

class HomeSharedViewModel : ViewModel() {

    var _messageSender = MutableStateFlow<Result>(Result.Nothing)  // Not create a new one

   fun sendNewMessage (message : String){
       _messageSender.tryEmit(Result.NewDataComing(message))
   }

    fun getSubscriberForIt () =  MutableStateFlow<Result>(Result.Nothing)   // create new One

    override fun onCleared() {
        super.onCleared()
        Log.i("morse" , "View model is Destroyed")
    }

}