package com.expertapps.base.dialog

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.morse.common.R
import com.expertapps.base.extensions.dialogConfigkKey
import com.morse.common.databinding.FragmentBaseDialogBinding

class BaseDialogFragment : DialogFragment() {

    private var binding: FragmentBaseDialogBinding? = null
    private var dialogConfig: DialogConfig? =null
    private var onViewClickListener : View.OnClickListener?= View.OnClickListener{
        dismiss()
        (targetFragment as BaseDialogListener).onBaseDialogButtonClicked(it?.id!!)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        dialogConfig = arguments?.getParcelable(dialogConfigkKey)
        binding = FragmentBaseDialogBinding.inflate( layoutInflater , container , false).apply {
            dialogConfig = this@BaseDialogFragment.dialogConfig
        }
        binding?.positiveButton?.setOnClickListener(onViewClickListener)
        binding?.negativeRightButton?.setOnClickListener(onViewClickListener)
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
        const val TAG = "BaseDialogFragmentTag"
    }

}