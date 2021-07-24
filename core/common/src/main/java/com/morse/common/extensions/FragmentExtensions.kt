package com.expertapps.base.extensions

import android.app.Activity
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.core.content.ContextCompat
import androidx.core.view.isGone
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.expertapps.base.dialog.BaseDialogFragment
import com.expertapps.base.dialog.DialogConfig
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.transition.platform.MaterialArcMotion
import com.google.android.material.transition.platform.MaterialContainerTransform
import com.morse.common.BuildConfig
import com.morse.common.R

const val dialogConfigkKey = "DialogConfigration"


fun showDialog(
    fragmentManager: FragmentManager,
    ourTargetFragment: Fragment,
    ourDialogConfig: DialogConfig
) {
    val baseDialog = BaseDialogFragment()
    baseDialog.arguments = Bundle().apply {
        this.putParcelable(dialogConfigkKey, ourDialogConfig)
        this.putParcelable(dialogConfigkKey, ourDialogConfig)
    }
    baseDialog.setTargetFragment(ourTargetFragment, 0)
    baseDialog.show(fragmentManager, BaseDialogFragment.TAG)
}

fun showSnackbar(
    activity: Activity,
    message: String,
    time: Int? = null,
    retryFunctionalityName: String? = "RETRY",
    retryFunctionality: () -> Unit
) {
    message.let {
        val containerView: View? = activity?.findViewById(android.R.id.content)
        containerView?.let { contentView ->
            Snackbar.make(
                contentView,
                "${it}",
                time ?: Snackbar.LENGTH_SHORT
            )
                .setAction(retryFunctionalityName) { retryFunctionality.invoke() }
                .setActionTextColor(
                    ContextCompat.getColor(
                        activity,
                        R.color.orange_F87F0F
                    )
                )
                .show()
        }

    }
}

fun Fragment.withFragment(dataBinding: ViewDataBinding) {
    dataBinding.lifecycleOwner = this.viewLifecycleOwner
}


fun Fragment.showLog(message: String) {
    if (BuildConfig.DEBUG) {
        Log.i("Mohammed-Morse-Logger", message)
    }
}

fun Activity.showLog(message: String) {
    if (BuildConfig.DEBUG) {
        Log.i("Mohammed-Morse-Logger", message)
    }
}


@RequiresApi(Build.VERSION_CODES.LOLLIPOP)
fun Fragment.returnCardToOriginPosition(
    screenRoot: ViewGroup,
    cardRoot: View,
    recyclerviewItem: View,
    duration: Long
) {
    android.transition.TransitionManager.beginDelayedTransition(
        screenRoot,
        getTransform(cardRoot, recyclerviewItem, duration)
    )
    cardRoot?.isGone = true
    recyclerviewItem?.isGone = false
}

@RequiresApi(Build.VERSION_CODES.LOLLIPOP)
fun Fragment.animateCard(screenRoot: ViewGroup, cardRoot: View, recyclerviewItem: View) {
    android.transition.TransitionManager.beginDelayedTransition(
        screenRoot,
        getTransform(recyclerviewItem, cardRoot, 650)
    )
    recyclerviewItem?.isGone = true
    cardRoot?.isGone = false
}

@RequiresApi(Build.VERSION_CODES.LOLLIPOP)
fun Fragment.getTransform(
    mStartView: View,
    mEndView: View,
    customDuration: Long
): MaterialContainerTransform {
    return MaterialContainerTransform().apply {
        startView = mStartView
        endView = mEndView
        addTarget(mEndView)
        pathMotion = MaterialArcMotion()
        duration = customDuration
        scrimColor = Color.TRANSPARENT
    }
}