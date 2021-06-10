package com.expertapps.connect.utils

import android.app.Activity
import android.graphics.Rect
import android.util.DisplayMetrics
import android.view.View
import android.view.ViewGroup
import android.view.ViewTreeObserver
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.distinctUntilChanged
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.withContext

sealed class KeyboardStatus {
    object Opened : KeyboardStatus ()
    object Closed : KeyboardStatus ()
}

class KeyBoardUtils(private val activity: Activity) {
    private var rootView: ViewGroup? = null
    private val TAG = "KEY-BOARD"
    private val MIN_KEYBOARD_HEIGHT_RATIO = 0.15
    private var keyboardFlow = MutableLiveData<KeyboardStatus>()
    private lateinit var globalLayoutListener :  ViewTreeObserver.OnGlobalLayoutListener

    private fun getRoot(): ViewGroup? {
        return rootView ?: (activity.findViewById<View>(android.R.id.content) as ViewGroup?).apply {
            rootView = this
        }
    }

    private fun getWindowHeight(): Int {
        return DisplayMetrics().let {
            activity.windowManager.defaultDisplay.getMetrics(it)
            it.heightPixels
        }
    }

    fun getKeyboardStatus ()  : MutableLiveData<KeyboardStatus> {
        observeOn()
        return keyboardFlow
    }
    private fun observeOn () {
        globalLayoutListener = ViewTreeObserver.OnGlobalLayoutListener {
            val rect = Rect().apply { getRoot()?.getWindowVisibleDisplayFrame(this) }
            val keyboardHeight = getWindowHeight().minus(rect.height())
            if (keyboardHeight > getWindowHeight().times(MIN_KEYBOARD_HEIGHT_RATIO)) {
                keyboardFlow.postValue(KeyboardStatus.Opened)
            } else {
                keyboardFlow.postValue(KeyboardStatus.Closed)
            }
        }
        getRoot()?.viewTreeObserver?.addOnGlobalLayoutListener(globalLayoutListener)
    }

    fun removeObservation() {
        getRoot()?.viewTreeObserver?.removeOnGlobalLayoutListener(globalLayoutListener)
    }

    fun isKeyboardOpened () = keyboardFlow.value == KeyboardStatus.Opened

}