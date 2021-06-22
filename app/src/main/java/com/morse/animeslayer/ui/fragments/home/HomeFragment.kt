package com.morse.animeslayer.ui.fragments.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.navigation.fragment.findNavController
import com.expertapps.base.extensions.animateCard
import com.expertapps.base.extensions.returnCardToOriginPosition
import com.morse.animeslayer.R
import com.morse.animeslayer.databinding.FragmentHomeBinding
import com.morse.animeslayer.domain.AnimeListResponse
import com.morse.animeslayer.ui.fragments.menu.host.MenuBottomSheet
import com.morse.common.extensions.navigateSafe
import com.morse.common.utils.ItemOffsetDecoration


class HomeFragment : Fragment(), AnimeListListener {

    private val animeAdapter = AnimeListAdapter(this)
    val binding: FragmentHomeBinding by lazy {
        FragmentHomeBinding.inflate(layoutInflater)
    }
    lateinit var animeView: View

    lateinit var itemDecorator: ItemOffsetDecoration

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
                if (binding.currentAnime.cardRoot.visibility == View.VISIBLE) {
                    returnCardToOriginPosition(
                        binding.homeRoot,
                        binding.currentAnime.cardRoot,
                        animeView,
                        450
                    )
                } else {
                    animateCard(binding.homeRoot, binding.currentAnime.cardRoot, animeView)
                }
            }
            else -> {
                val options = Pair(binding.currentAnime.animeImageview, "animeImage")
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
        itemDecorator = ItemOffsetDecoration(
            requireContext(),
            R.dimen._9sdp
        )
        with(binding) {
            this.meIconIv.setOnClickListener(
                homeClickListener
            )
            this.searchIconIv.setOnClickListener(
                homeClickListener
            )
            this.currentAnime.cardRoot.setOnClickListener(
                homeClickListener
            )
            this.animeListRv.adapter = animeAdapter
            this.animeListRv.addItemDecoration(
                itemDecorator
            )
            this.currentAnime.cardRoot.setOnClickListener {
                returnCardToOriginPosition(
                    binding.homeRoot,
                    binding.currentAnime.cardRoot,
                    animeView,
                    650
                )
            }
        }
        return binding.root
    }

    override fun onAnimeClicked(animeView: View, anime: AnimeListResponse.Anime) {
        this.animeView = animeView
        if (binding.currentAnime.cardRoot.visibility == View.VISIBLE) {
            returnCardToOriginPosition(
                binding.homeRoot,
                binding.currentAnime.cardRoot,
                animeView,
                450
            )
        } else {
            animateCard(binding.homeRoot, binding.currentAnime.cardRoot, animeView)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding.animeListRv.removeItemDecoration(
            itemDecorator
        )
    }

}



