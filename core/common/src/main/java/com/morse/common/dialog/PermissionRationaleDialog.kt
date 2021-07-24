package com.expertapps.base.dialog

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.morse.common.R
import com.morse.common.databinding.FragmentPermissionRationaleDialogBinding


class PermissionRationaleDialog(
    val onPositiveClicked: () -> Unit,
    val onActionButtonButton: () -> Unit = {}
) : DialogFragment() {

    private var binding: FragmentPermissionRationaleDialogBinding? = null

    private var onViewClickListener: View.OnClickListener? = View.OnClickListener {
        when (it.id) {
            R.id.positiveButton -> {
                dismiss()
                onPositiveClicked()
            }

            R.id.actionButton -> {
                dismiss()
                onActionButtonButton()
            }
        }
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentPermissionRationaleDialogBinding.inflate(layoutInflater, container, false)
            .apply {
                lifecycleOwner = this@PermissionRationaleDialog
            }
        binding?.positiveButton?.setOnClickListener(onViewClickListener)
        binding?.actionButton?.setOnClickListener(onViewClickListener)
        return binding?.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        dialog?.window?.attributes?.windowAnimations = R.style.MyDialogAnimation
    }

    override fun onDestroyView() {
        super.onDestroyView()
        onViewClickListener = null
        binding = null
    }

    companion object {
        const val TAG = "PermissionDialogFragmentTag"
    }
}