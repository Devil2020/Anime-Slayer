package com.morse.animeslayer.ui.fragments.splash

import android.animation.ValueAnimator
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.morse.animeslayer.R
import com.morse.animeslayer.databinding.FragmentSplashBinding
import com.morse.animeslayer.utils.closeVideo
import com.morse.animeslayer.utils.manageVideo
import com.morse.animeslayer.utils.releaseVideo
import com.morse.common.extensions.navigateSafe
import com.morse.common.extensions.valueAnimateDescending

class SplashFragment : Fragment() {
    private val duration = 20L
    private var binding: FragmentSplashBinding? = null
    private val navController: NavController by lazy {
        findNavController()
    }
    private var valueAnimator: ValueAnimator? = null
    var root: View? = null

    lateinit var mediaController: android.widget.MediaController

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSplashBinding.inflate(layoutInflater)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mediaController = android.widget.MediaController(requireContext())
        animateSkipCount()
        skipNow()
        binding?.splashVideoView?.let {
            manageVideo(it) {
                it.pause()
                it.stopPlayback()
                valueAnimator?.pause()
                valueAnimator?.removeAllUpdateListeners()
                navController.navigateSafe(R.id.action_go_to_homeFragment , Bundle().apply {
                    putString("message" , "Okay , Now we can that also the extra that we will put into the navigation between fragment will add also to saved State Handle 🚀")
                })
            }
        }
    }

    private fun skipNow() {
        binding?.splashInfo?.skipAfter5SecondsTv?.setOnClickListener {
            binding?.splashVideoView?.let { it1 ->
                releaseVideo(it1)
                valueAnimator?.pause()
                valueAnimator?.removeAllUpdateListeners()
                navController.navigateSafe(R.id.action_go_to_homeFragment)
            }
        }
    }

    private fun animateSkipCount() {
        with(binding?.splashInfo?.skipAfter5SecondsTv) {
            valueAnimator = this?.valueAnimateDescending(20, duration) {
                this.text = getString(R.string.skip_after_seconds_label, it)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding?.splashVideoView?.let {
            closeVideo(it)
        }
        binding = null
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