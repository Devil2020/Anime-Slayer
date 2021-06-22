package com.morse.animeslayer.ui.fragments.menu.host

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.morse.animeslayer.databinding.FragmentMenuBottomSheetBinding
import com.morse.animeslayer.ui.fragments.menu.pages.MenuAdapter
import com.morse.animeslayer.ui.fragments.menu.pages.MenuItem
import com.morse.animeslayer.ui.fragments.menu.pages.MenuItemListener

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



}