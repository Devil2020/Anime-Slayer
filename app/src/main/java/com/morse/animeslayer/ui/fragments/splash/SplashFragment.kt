package com.morse.animeslayer.ui.fragments.splash

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Transformations
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.morse.animeslayer.R
import com.morse.animeslayer.databinding.FragmentSplashBinding
import com.morse.animeslayer.utils.repeatImages
import com.morse.common.extensions.navigateSafe
import com.morse.common.extensions.valueAnimateDescending
import kotlinx.coroutines.flow.*

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
        binding.splashRoot.repeatImages()
        animateSkipCount()
    }

    private fun animateSkipCount() {
        with(binding.skipAfter5SecondsTv) {
            valueAnimateDescending(10, 100000) {

                when (it) {
                    "1000" -> navController.navigateSafe(R.id.action_go_to_homeFragment)
                    else -> this.text = getString(R.string.skip_after_seconds_label, it)
                }
            }
        }
    }

    override fun onResume() {
        super.onResume()

    }

    inner class Events {

        // replay cache >> how much item should i pushed to new collector

        private val aFlow = MutableSharedFlow<Int>(replay = 2 , extraBufferCapacity =0  )

    }

}

/*
    1- What is difference between Map , FlatMap , ConcatMap and SwitchMap ?

                          Map               FlatMap                ConcatMap            SwitchMap


exist in collection       yes                yes                      no                     no

exist in Stream           yes                yes                      yes                    yes

purpose           [ change the shape of   [ it used for concat  [ it used for merge      [ Switch Map it is
                   the value inside it ,   2 Lists into only     2 Streams and the          very difference ,
                   like from string to    one List so we can     difference is that         it only return the
                   int or append some     merge 2 lists in one   the data emitted by        last Observable only ]
                   words to string ]      and in Stream , it     stream will be ordered
                                          returns another Stream with same order of
                                          the order may be not   emittion ]
                                          Sorted with emittion
                                          time ]





*/