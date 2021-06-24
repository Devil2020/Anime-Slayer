package com.morse.animeslayer.ui.fragments.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.expertapps.base.extensions.animateCard
import com.expertapps.base.extensions.returnCardToOriginPosition
import com.morse.animeslayer.R
import com.morse.animeslayer.databinding.FragmentHomeBinding
import com.morse.animeslayer.domain.AnimeListResponse
import com.morse.animeslayer.ui.fragments.menu.host.MenuBottomSheet
import com.morse.animeslayer.utils.render
import com.morse.common.extensions.navigateSafe
import com.morse.common.utils.ItemOffsetDecoration


class HomeFragment : Fragment(), AnimeListListener {

    private val animeAdapter = AnimeListAdapter(this)
    val binding: FragmentHomeBinding by lazy {
        FragmentHomeBinding.inflate(layoutInflater)
    }
    lateinit var animeView: View

    var isSearchClicked = false

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
            R.id.searchExtebdedFab -> {

                animateCard(binding.homeRoot, binding.searchDialog, binding.searchExtebdedFab)

            }

            R.id.closeSearch -> {

                returnCardToOriginPosition(binding.homeRoot, binding.searchDialog, binding.searchExtebdedFab , 400)

            }

            R.id.anime_detail_navigation -> {
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
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(binding) {
            meIconIv.setOnClickListener(
                homeClickListener
            )
            searchIconIv.setOnClickListener(
                homeClickListener
            )
            currentAnime.cardRoot.setOnClickListener(
                homeClickListener
            )

            searchExtebdedFab.setOnClickListener(
                homeClickListener
            )

            currentAnime.animeDetailNavigation.setOnClickListener(
                homeClickListener
            )

            closeSearch.setOnClickListener(
                homeClickListener
            )
            animeListRv.adapter = animeAdapter
            animeListRv.addItemDecoration(
                itemDecorator
            )
            currentAnime.cardRoot.setOnClickListener {
                returnCardToOriginPosition(
                    binding.homeRoot,
                    binding.currentAnime.cardRoot,
                    animeView,
                    650
                )
            }
            animeListRv.setOnScrollChangeListener { v, scrollX, scrollY, oldScrollX, oldScrollY ->
                if (scrollY > oldScrollY) {
                   searchExtebdedFab.shrink()
                } else {
                    searchExtebdedFab?.extend()
                }
            }
        }
    }

    override fun onAnimeLongClicked(animeImageView : ImageView,  anime: AnimeListResponse.Anime) {
        val options = Pair(animeImageView, "animeImage")
        val extras = FragmentNavigatorExtras(options)
        findNavController().navigateSafe(
            R.id.action_homeFragment_to_animeDetailFragment,
            navExtras = extras
        )
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
            binding.currentAnime.render(anime.imageUrl!!, anime.title!!, anime.synopsis!!)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding.animeListRv.removeItemDecoration(
            itemDecorator
        )
    }

}



