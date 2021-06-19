package com.morse.animeslayer.ui.fragments.home

import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.annotation.RequiresApi
import androidx.core.view.isGone
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.navigation.fragment.findNavController
import com.google.android.material.transition.platform.MaterialArcMotion
import com.google.android.material.transition.platform.MaterialContainerTransform
import com.morse.animeslayer.R
import com.morse.animeslayer.databinding.FragmentHomeBinding
import com.morse.animeslayer.domain.AnimeListResponse
import com.morse.animeslayer.ui.fragments.menu.MenuBottomSheet
import com.morse.common.extensions.navigateSafe
import com.morse.common.utils.ItemOffsetDecoration

class HomeFragment : Fragment(), AnimeListListener {

    private val animeAdapter = AnimeListAdapter(this)
    val binding: FragmentHomeBinding by lazy {
         FragmentHomeBinding.inflate(layoutInflater)
    }
    lateinit var animeView: View

    private val homeClickListener = View.OnClickListener {
        when (it.id) {
            R.id.me_icon_iv -> {
                it.startAnimation(
                    AnimationUtils.loadAnimation(requireContext(), R.anim.scale_click)
                )
                MenuBottomSheet().show(parentFragmentManager, MenuBottomSheet.TAG)
            }
            R.id.search_icon_iv -> {
                findNavController().navigateSafe(R.id.action_homeFragment_to_searchFragment)
            }
            R.id.cardOfAnimeDescribtion -> {
                if (binding.cardOfAnimeDescribtion.visibility == View.VISIBLE) {
                    returnCardToOriginPosition(450)
                } else {
                    animateCard(animeView)
                }
            }
            R.id.anime_detail_navigation -> {
                val options = kotlin.Pair(binding.animeImageview, "animeImage")
                val extras = FragmentNavigatorExtras(options)
                findNavController().navigateSafe(
                    R.id.action_homeFragment_to_animeDetailFragment,
                    navExtras = extras
                )
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        with(binding) {
            this.meIconIv.setOnClickListener(
                homeClickListener
            )
            this.searchIconIv.setOnClickListener(
                homeClickListener
            )
            this.cardOfAnimeDescribtion.setOnClickListener(
                homeClickListener
            )
            this.animeDetailNavigation.setOnClickListener(
                homeClickListener
            )
            this.animeListRv.adapter = animeAdapter
            this.animeListRv.addItemDecoration(
                ItemOffsetDecoration(
                    requireContext(),
                    R.dimen._9sdp
                )
            )
            this.closeCard.setOnClickListener {
                returnCardToOriginPosition(650)
            }
        }
        return binding.root
    }

    override fun onAnimeClicked(animeView: View, anime: AnimeListResponse.Anime) {
        if (binding.cardOfAnimeDescribtion.visibility == View.VISIBLE) {
            returnCardToOriginPosition(450)
        } else {
            animateCard(animeView)
        }
    }

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    private fun returnCardToOriginPosition(duration: Long) {
        android.transition.TransitionManager.beginDelayedTransition(
            binding.homeRoot,
            getTransform(binding.cardOfAnimeDescribtion, animeView, duration)
        )
        binding.cardOfAnimeDescribtion?.isGone = true
        animeView?.isGone = false
    }

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    fun animateCard(view: View) {
        animeView = view
        android.transition.TransitionManager.beginDelayedTransition(
            binding.homeRoot,
            getTransform(view, binding.cardOfAnimeDescribtion, 650)
        )
        view?.isGone = true
        binding.cardOfAnimeDescribtion?.isGone = false
    }

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    private fun getTransform(
        mStartView: View,
        mEndView: View,
        customDuration: Long
    ): MaterialContainerTransform {
        return MaterialContainerTransform().apply {
            startView = mStartView
            endView = mEndView
            addTarget(mEndView)
            pathMotion = MaterialArcMotion()
            duration = customDuration
            scrimColor = Color.TRANSPARENT
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

}



