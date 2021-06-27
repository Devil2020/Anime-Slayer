package com.morse.animeslayer.ui.fragments.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.transition.TransitionInflater
import com.expertapps.base.extensions.animateCard
import com.expertapps.base.extensions.returnCardToOriginPosition
import com.morse.animeslayer.R
import com.morse.animeslayer.databinding.FragmentAnimeDetailBinding
import com.morse.animeslayer.domain.AnimeCharactersResponse
import com.morse.animeslayer.domain.AnimeListResponse
import com.morse.animeslayer.ui.fragments.home.AnimeListAdapter
import com.morse.animeslayer.ui.fragments.home.AnimeListListener
import com.morse.common.utils.ItemOffsetDecoration
import java.util.concurrent.TimeUnit

class AnimeDetailFragment : Fragment(), CharacterListener, AnimeListListener {

    private val binding: FragmentAnimeDetailBinding by lazy {
        FragmentAnimeDetailBinding.inflate(layoutInflater)
    }

    lateinit var characterView: View

    lateinit var recommendedView: View

    private val detailClickListener = View.OnClickListener {
        when (it.id) {
            R.id.closeDetailButton -> {
                findNavController().popBackStack()
            }
            R.id.manageFavouriteFab -> {
            }
            R.id.manageWebsiteFab -> {
            }
            R.id.recommendedAnime -> {
                if (binding.recommendedAnime.cardRoot.visibility == View.VISIBLE) {
                    returnCardToOriginPosition(
                        binding.detailRoot,
                        binding.recommendedAnime.cardRoot,
                        recommendedView,
                        450
                    )
                } else {
                    animateCard(
                        binding.detailRoot,
                        binding.recommendedAnime.cardRoot,
                        characterView
                    )
                }
            }
            R.id.characterAnime -> {
                if (binding.characterAnime.cardRoot.visibility == View.VISIBLE) {
                    returnCardToOriginPosition(
                        binding.detailRoot,
                        binding.characterAnime.cardRoot,
                        characterView,
                        450
                    )
                } else {
                    animateCard(binding.detailRoot, binding.characterAnime.cardRoot, characterView)
                }
            }
            R.id.playVideo -> {
                animateCard(binding.detailRoot, binding.videoAnime.root, binding.playVideo)
            }
            R.id.closeVideoFab -> {
                returnCardToOriginPosition(
                    binding.detailRoot,
                    binding.videoAnime.root,
                    binding.playVideo,
                    450
                )
            }
        }
    }

    private val characterAdapter = CharactersAdapter(this)

    private val recommendationAdapter = AnimeListAdapter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        sharedElementEnterTransition =
            TransitionInflater.from(context).inflateTransition(android.R.transition.move)
        postponeEnterTransition(250, TimeUnit.MILLISECONDS)
        with(binding) {
            imageTest =
                "https://cdn.myanimelist.net/s/common/store/cover/3638/2620c52ffcc6246fedc66e55e960e7e8ee692763da429986459f86dde14be714/l@2x.jpg"
            scoreTestKey = "Score"
            scoreTestValue = "#1960"

            studioTestKey = "Studio"
            studioTestValue = "Mohammed morse and Ahmed morse"

            durationTestKey = "Duration :"
            durationTestValue = "25 min per ep"

            dateTestKey = "Date :"
            dateTestValue = "Apr 6, 2020 to ?"

            detailScrollable.setOnScrollChangeListener { v, scrollX, scrollY, oldScrollX, oldScrollY ->
                if (scrollY > oldScrollY) {
                    manageFavouriteFab?.shrink()
                    manageWebsiteFab?.shrink()
                } else {
                    manageFavouriteFab?.extend()
                    manageWebsiteFab?.extend()
                }
            }

            charactersRecyclerview.adapter = characterAdapter

            recommendationsRecyclerview.adapter = recommendationAdapter

            recommendationsRecyclerview.addItemDecoration(
                ItemOffsetDecoration(
                    requireContext(),
                    R.dimen._9sdp
                )
            )

            closeDetailButton.setOnClickListener(detailClickListener)

            manageFavouriteFab.setOnClickListener(detailClickListener)

            manageWebsiteFab.setOnClickListener(detailClickListener)

            recommendedAnime.cardRoot.setOnClickListener(detailClickListener)

            characterAnime.cardRoot.setOnClickListener(detailClickListener)

            playVideo.setOnClickListener(detailClickListener)

            videoAnime.closeVideoFab.setOnClickListener(detailClickListener)

        }
        return binding.root
    }

    override fun onCharacterClicked(characterView: View, anime: AnimeCharactersResponse.Character) {

        if (binding.characterAnime.cardRoot.visibility == View.VISIBLE) {
            returnCardToOriginPosition(
                binding.detailRoot,
                binding.characterAnime.cardRoot,
                characterView,
                450
            )
        } else {
            animateCard(binding.detailRoot, binding.characterAnime.cardRoot, characterView)
        }
        this.characterView = characterView
    }

    override fun onAnimeLongClicked(animeImageView: ImageView, anime: AnimeListResponse.Anime) {

    }

    override fun onAnimeClicked(animeView: View, anime: AnimeListResponse.Anime) {

        if (binding.recommendedAnime.cardRoot.visibility == View.VISIBLE) {
            returnCardToOriginPosition(
                binding.detailRoot,
                binding.recommendedAnime.cardRoot,
                recommendedView,
                450
            )
        } else {
            animateCard(binding.detailRoot, binding.recommendedAnime.cardRoot, animeView)
        }
        this.recommendedView = animeView
    }

}
