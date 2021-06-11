package com.morse.animeslayer

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.morse.animeslayer.databinding.FragmentSplashBinding
import com.morse.common.extensions.navigateSafe
import com.morse.common.extensions.valueAnimateDescending

class SplashFragment : Fragment() {

    private val binding: FragmentSplashBinding by lazy {
        FragmentSplashBinding.inflate(layoutInflater)
    }

    private val navController: NavController by lazy {
        findNavController()
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
            valueAnimateDescending(5, 4000) {

                when (it) {
                    "0" -> navController.navigateSafe(R.id.action_go_to_homeFragment)
                    else -> this.text = getString(R.string.skip_after_seconds_label, it)
                }
            }
        }
    }

}