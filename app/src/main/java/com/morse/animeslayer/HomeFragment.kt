package com.morse.animeslayer

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.fragment.app.Fragment
import com.morse.animeslayer.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

    val binding: FragmentHomeBinding by lazy {
        FragmentHomeBinding.inflate(layoutInflater)
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
        with(binding) {
            this.meIconIv.setOnClickListener {
                it.startAnimation(
                    AnimationUtils.loadAnimation(requireContext() , R.anim.scale_click)
                )
                MenuBottomSheet().show(parentFragmentManager , MenuBottomSheet.TAG )
            }
        }
    }


}