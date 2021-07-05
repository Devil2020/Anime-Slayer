package com.morse.animeslayer.ui.test

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.navigation.fragment.findNavController
import com.expertapps.base.extensions.showLog
import com.morse.animeslayer.R
import kotlinx.android.synthetic.main.fragment_a.*
import kotlinx.android.synthetic.main.fragment_b.fragmetAText
import kotlinx.coroutines.flow.collect

sealed class AFragmentIntents {

    object LoadCurrentSeason : AFragmentIntents()

    object LoadPopularAnime : AFragmentIntents()

    object LoadTopRatedAnime : AFragmentIntents()

    object LoadFavouriteAnime : AFragmentIntents()

}


sealed class AFragmentState {

    data class LoadCurrentSeasonState(val name: String) : AFragmentState()

    data class LoadPopularAnime(val name: String) : AFragmentState()

    data class LoadTopRatedAnime(val name: String) : AFragmentState()

    data class LoadFavouriteAnime(val name: String) : AFragmentState()

}

class AFragment : Fragment() {

    val viewmodel: TestActivityViewModel by activityViewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        showLog("FragmentA : onCreate")
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        showLog("FragmentA : onCreateView")
        return inflater.inflate(R.layout.fragment_a, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        root.setOnClickListener {
            val extra = FragmentNavigatorExtras(fragmetAText to "aaa")
            findNavController().navigate(R.id.action_AFragment_to_BFragment, null, null, extra)
        }
        showLog("FragmentA : onViewCreated")
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        showLog("FragmentA : onActivityCreated")
    }

    override fun onStart() {
        super.onStart()
        showLog("FragmentA : onStart")
    }

    override fun onResume() {
        super.onResume()
        showLog("FragmentA : onResume")
        lifecycleScope.launchWhenResumed {
            lifecycle.repeatOnLifecycle(Lifecycle.State.RESUMED) {
              /*  viewmodel.getNews.collect {
                    fragmetAText.text = "$it"
                }*/
            }
        }
    }

    override fun onPause() {
        super.onPause()
        showLog("FragmentA : onPause")
    }

    override fun onStop() {
        super.onStop()
        showLog("FragmentA : onStop")
    }

    override fun onDestroy() {
        super.onDestroy()
        showLog("FragmentA : onDestroy")
    }

}