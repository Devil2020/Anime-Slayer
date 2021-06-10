package com.expertapps.base.extensions

import android.app.Activity
import android.os.Bundle
import android.view.View
import androidx.core.content.ContextCompat
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.morse.common.R
import com.expertapps.base.dialog.BaseDialogFragment
import com.expertapps.base.dialog.DialogConfig
import com.google.android.material.snackbar.Snackbar
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
    retryFunctionalityName : String ?= "RETRY" ,
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



