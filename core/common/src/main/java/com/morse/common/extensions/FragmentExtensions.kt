package com.expertapps.base.extensions

import android.app.Activity
import android.app.Fragment
import android.app.FragmentManager
import android.app.FragmentTransaction
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

fun addFragment(
    activity: AppCompatActivity,
    fragment: Fragment,
    replace: Boolean = false, addToBackStack: Boolean = true,
    tag: String = fragment.javaClass.simpleName,
    fragmentManager: FragmentManager = activity.supportFragmentManager,
    containerId: Int = R.id.container,
) {

    val transaction = fragmentManager.beginTransaction()

    if (replace) {
        transaction.replace(containerId, fragment, tag)
        transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
    } else {
        val oldFragment = getCurrentTopFragment(fragmentManager)
        oldFragment?.let { transaction.setMaxLifecycle(it, Lifecycle.State.STARTED) }
        transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
        //set maxLifecycle of CurrentTopFragment to STARTED, when fragment is being added only,
        //No need to setMaxLifecycle when fragment is being replaced
        transaction.add(containerId, fragment, tag)
    }

    if (addToBackStack)
        transaction.addToBackStack(tag)

    //this does not crash in case activity was not in correct state
    transaction.commitAllowingStateLoss()
}

private fun getCurrentTopFragment(fragmentManager: FragmentManager): Fragment? {
    try {
        val stackCount = fragmentManager.backStackEntryCount
        if (stackCount > 0) {
            val backEntry = fragmentManager.getBackStackEntryAt(stackCount - 1)
            return fragmentManager.findFragmentByTag(backEntry.name)
        } else {
            val fragments = fragmentManager.fragments
            if (fragments.size > 0) {
                for (f in fragments) {
                    if (f != null && !f.isHidden)
                        return f
                }
            }
        }
        return null
    } catch (e: KotlinNullPointerException) {
        return null
    }
}