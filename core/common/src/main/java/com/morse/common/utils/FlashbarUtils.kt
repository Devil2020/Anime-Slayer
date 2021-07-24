package com.morse.common.utils

import android.app.Activity
import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes
import androidx.annotation.StyleRes
import com.andrognito.flashbar.Flashbar
import com.andrognito.flashbar.Flashbar.ProgressPosition
import com.andrognito.flashbar.Flashbar.Vibration
import com.andrognito.flashbar.anim.FlashAnim
import com.morse.common.R


class FlashbarUtils {
    companion object {

        fun openFlashbar(
            activity: Activity,
            title: String,
            @StyleRes titleTextAppearance: Int = R.style.Baloothambi2_medium,
            message: String,
            @StyleRes messageTextAppearance: Int = R.style.Baloothambi2_medium,
            @DrawableRes background: Int = R.drawable.flashbar_fail_gradient,

            primaryActionText : String ="",
            @ColorRes primaryActionTextColor: Int = R.color.white,
            @StyleRes primaryActionTextAppearance: Int = R.style.Baloothambi2_medium,
            primaryActionTextSize: Float = 14.0f,
            listenToPrimaryActionClicks: (() -> Unit?)? = null,

            positiveActionText: String = "",
            @ColorRes positiveActionTextColor: Int = R.color.white,
            @StyleRes positiveActionTextAppearance: Int = R.style.Baloothambi2_medium,
            positiveActionTextSize: Float = 14.0f,
            listenToPositiveActionClicks: (() -> Unit?)? = null,

            negativeActionText: String = "",
            @ColorRes negativeActionTextColor: Int =  R.color.white,
            @StyleRes negativeActionTextAppearance: Int = R.style.Baloothambi2_medium,
            negativeActionTextSize: Float = 14.0f,
            listenToNegativeActionClicks: (() -> Unit?)? = null ,


        ) {
            Flashbar.Builder(activity)
                .gravity(Flashbar.Gravity.TOP)
                .title("\n\n\n\n $title ")
                .titleAppearance(titleTextAppearance)
                .message(message)
                .messageAppearance(messageTextAppearance)
                .duration(Flashbar.DURATION_LONG)
                .vibrateOn(Flashbar.Vibration.SHOW)
                .enableSwipeToDismiss()
                .backgroundDrawable(background)
                .enterAnimation(
                    FlashAnim.with(activity)
                        .animateBar()
                        .duration(750)
                        .alpha()
                        .overshoot()
                )
                .exitAnimation(
                    FlashAnim.with(activity)
                        .animateBar()
                        .duration(400)
                        .accelerateDecelerate()
                )

                .primaryActionText(primaryActionText)
                .primaryActionTextAppearance(primaryActionTextAppearance)
                .primaryActionTextColorRes(primaryActionTextColor)
                .primaryActionTextSizeInSp(primaryActionTextSize)
                .primaryActionTapListener(object :Flashbar.OnActionTapListener{
                    override fun onActionTapped(bar: Flashbar) {
                        listenToPrimaryActionClicks?.invoke()
                    }
                })

                .positiveActionText(positiveActionText)
                .positiveActionTextColorRes(positiveActionTextColor)
                .positiveActionTextSizeInSp(positiveActionTextSize)
                .positiveActionTextAppearance(positiveActionTextAppearance)
                .positiveActionTapListener(object : Flashbar.OnActionTapListener {
                    override fun onActionTapped(bar: Flashbar) {
                        listenToPositiveActionClicks?.invoke()
                    }
                })
                .negativeActionText(negativeActionText)
                .negativeActionTextColorRes(negativeActionTextColor)
                .negativeActionTextAppearance(negativeActionTextAppearance)
                .negativeActionTextSizeInSp(negativeActionTextSize)
                .negativeActionTapListener(object : Flashbar.OnActionTapListener {
                    override fun onActionTapped(bar: Flashbar) {
                        listenToNegativeActionClicks?.invoke()
                    }
                })
                .build()
                .show()
        }

    }
}