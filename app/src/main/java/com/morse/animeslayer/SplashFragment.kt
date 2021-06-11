package com.morse.animeslayer

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.morse.animeslayer.databinding.FragmentSplashBinding
import com.morse.common.extensions.valueAnimateDescending

class SplashFragment : Fragment() {

    private val binding: FragmentSplashBinding by lazy {
        FragmentSplashBinding.inflate(layoutInflater)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        animateSkipCount()
    }

    private fun animateSkipCount() {
        with(binding.skipAfter5SecondsTv) {
            valueAnimateDescending(5, 6000) {
                this.text = getString(R.string.skip_after_seconds_label, it)
            }
        }
    }

}