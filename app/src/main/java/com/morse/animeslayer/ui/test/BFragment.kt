package com.morse.animeslayer.ui.test

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.transition.TransitionInflater
import com.expertapps.base.extensions.showLog
import com.morse.animeslayer.R
import kotlinx.android.synthetic.main.activity_test.*
import kotlinx.android.synthetic.main.fragment_b.*
import kotlinx.coroutines.flow.collect
import java.util.concurrent.TimeUnit


class BFragment : Fragment() {

    val viewmodel : TestActivityViewModel by activityViewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        showLog("FragmentB : onCreate")
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        sharedElementEnterTransition =
            TransitionInflater.from(context).inflateTransition(android.R.transition.move)
        postponeEnterTransition(250, TimeUnit.MILLISECONDS)
        showLog("FragmentB : onCreateView")
        return inflater.inflate(R.layout.fragment_b, container, false)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        showLog("FragmentB : onViewCreated")
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        showLog("FragmentB : onActivityCreated")
    }

    override fun onStart() {
        super.onStart()
        showLog("FragmentB : onStart")
    }

    override fun onResume() {
        super.onResume()
        showLog("FragmentB : onResume")
        lifecycleScope.launchWhenResumed {
            lifecycle.repeatOnLifecycle(Lifecycle.State.RESUMED){
                /*viewmodel.getNews.collect {
                    fragmetAText.text = "$it"
                }*/
            }
        }
    }

    override fun onPause() {
        super.onPause()
        showLog("FragmentB : onPause")
    }

    override fun onStop() {
        super.onStop()
        showLog("FragmentB : onStop")
    }

    override fun onDestroy() {
        super.onDestroy()
        showLog("FragmentB : onDestroy")
    }
}