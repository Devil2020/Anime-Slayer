package com.morse.animeslayer.ui.fragments.detail.anime

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.expertapps.base.extensions.animateCard
import com.expertapps.base.extensions.returnCardToOriginPosition
import com.morse.animeslayer.R
import com.morse.animeslayer.databinding.FragmentAnimeDetailBinding
import com.morse.animeslayer.domain.AnimeCharactersResponse
import com.morse.animeslayer.domain.Anime
import com.morse.animeslayer.ui.fragments.home.AnimeListAdapter
import com.morse.animeslayer.ui.fragments.home.AnimeListListener
import com.morse.common.utils.ItemOffsetDecoration

class AnimeDetailFragment : Fragment(), CharacterListener, AnimeListListener {

    private var binding: FragmentAnimeDetailBinding? = null

    private lateinit var characterView: View

    private lateinit var recommendedView: View

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
                if (binding?.recommendedAnime?.cardRoot?.visibility == View.VISIBLE) {
                    binding?.detailRoot?.let { it1 ->
                        returnCardToOriginPosition(
                            it1,
                            binding?.recommendedAnime!!.cardRoot,
                            recommendedView,
                            450
                        )
                    }
                } else {
                    binding?.detailRoot?.let { it1 ->
                        binding?.recommendedAnime?.cardRoot?.let { it2 ->
                            animateCard(
                                it1,
                                it2,
                                characterView
                            )
                        }
                    }
                }
            }

            R.id.characterAnime -> {
                if (binding?.characterAnime?.cardRoot?.visibility == View.VISIBLE) {
                    binding?.detailRoot?.let { it1 ->
                        returnCardToOriginPosition(
                            it1,
                            binding?.characterAnime?.cardRoot!!,
                            characterView,
                            450
                        )
                    }
                } else {
                    binding?.detailRoot?.let { it1 -> binding?.characterAnime?.cardRoot?.let { it2 ->
                        animateCard(it1,
                            it2, characterView)
                    } }
                }
            }

            R.id.playVideo -> {
                binding?.detailRoot?.let { it1 -> binding?.videoAnime?.root?.let { it2 ->
                    binding?.playVideo?.let { it3 ->
                        animateCard(it1,
                            it2, it3
                        )
                    }
                } }
            }

            R.id.closeVideoFab -> {
                binding?.detailRoot?.let { it1 ->
                    binding?.videoAnime?.root?.let { it2 ->
                        binding?.playVideo?.let { it3 ->
                            returnCardToOriginPosition(
                                it1,
                                it2,
                                it3,
                                450
                            )
                        }
                    }
                }
            }

        }
    }

    private var characterAdapter : CharactersAdapter? = CharactersAdapter(this)

    private var recommendationAdapter : AnimeListAdapter ? = AnimeListAdapter(this)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAnimeDetailBinding.inflate(layoutInflater)

        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding!!) {
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
                    manageFavouriteFab.shrink()
                    manageWebsiteFab.shrink()
                } else {
                    manageFavouriteFab.extend()
                    manageWebsiteFab.extend()
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

    }

    override fun onCharacterClicked(characterView: View, anime: AnimeCharactersResponse.Character) {

        if (binding?.characterAnime?.cardRoot?.visibility == View.VISIBLE) {
            binding?.detailRoot?.let {
                returnCardToOriginPosition(
                    it,
                    binding?.characterAnime?.cardRoot!!,
                    characterView,
                    450
                )
            }
        } else {
            binding?.detailRoot?.let { binding?.characterAnime?.cardRoot?.let { it1 ->
                animateCard(it,
                    it1, characterView)
            } }
        }
        this.characterView = characterView
    }

    override fun onAnimeLongClicked(
        animeImageView: ImageView,
        animeName: TextView, anime: Anime
    ) {

    }

    override fun onAnimeClicked(animeView: View, anime: Anime) {

        if (binding?.recommendedAnime?.cardRoot?.visibility == View.VISIBLE) {
            binding?.detailRoot?.let {
                returnCardToOriginPosition(
                    it,
                    binding?.recommendedAnime?.cardRoot!!,
                    recommendedView,
                    450
                )
            }
        } else {
            binding?.detailRoot?.let { binding?.recommendedAnime?.cardRoot?.let { it1 ->
                animateCard(it,
                    it1, animeView)
            } }
        }
        this.recommendedView = animeView
    }

    override fun onDestroyView() {
        super.onDestroyView()
        characterAdapter = null
        recommendationAdapter = null
        binding?.videoAnime?.youtubePlayerView?.removeAllViewsInLayout()
        binding?.videoAnime?.youtubePlayerView?.release()
        binding = null
    }

}