package com.morse.common.extensions

import android.animation.ValueAnimator
import android.widget.TextView

// from 0 to 5
fun TextView.valueAnimateAscending(
    toValue: Int,
    duration: Long,
    onChangeValueListener: (String) -> Unit
) {
    try {
        val valueAnimation = ValueAnimator.ofInt(0, toValue)
        valueAnimation.addUpdateListener {
            onChangeValueListener.invoke(it.getAnimatedValue().toString())
        }
        valueAnimation.duration = duration
        valueAnimation.start()
    } catch (e: Exception) {

    }

}

// from 5 to 0
fun TextView.valueAnimateDescending(
    toValue: Int,
    duration: Long,
    onChangeValueListener: (String) -> Unit
) {
    try {
        val valueAnimation = ValueAnimator.ofInt(0, toValue)
        valueAnimation.addUpdateListener {
            onChangeValueListener.invoke((toValue - it.animatedValue as Int).toString())
        }
        valueAnimation.duration = duration
        valueAnimation.start()
    } catch (e: Exception) {

    }
}