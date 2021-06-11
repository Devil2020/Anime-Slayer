package com.morse.animeslayer

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.morse.animeslayer.databinding.FragmentMenuBottomSheetBinding
import com.morse.animeslayer.databinding.MenuItemLayoutBinding

class MenuBottomSheet : BottomSheetDialogFragment() {

    companion object {
        val TAG = MenuBottomSheet.javaClass.name
    }

    private val binding : FragmentMenuBottomSheetBinding by lazy {
        FragmentMenuBottomSheetBinding.inflate(layoutInflater)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding.menuRecyclerview){
            adapter = MenuAdapter()
        }
    }

}