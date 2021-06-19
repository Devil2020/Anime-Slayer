package com.morse.animeslayer.ui.fragments.anime_detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.morse.animeslayer.R
import com.morse.animeslayer.databinding.FragmentAnimeDetailBinding
import com.morse.animeslayer.domain.AnimeCharactersResponse
import com.morse.animeslayer.domain.AnimeListResponse
import com.morse.animeslayer.ui.fragments.home.AnimeListAdapter
import com.morse.animeslayer.ui.fragments.home.AnimeListListener
import com.morse.common.utils.ItemOffsetDecoration

class AnimeDetailFragment : Fragment(), CharacterListener, AnimeListListener {

    private val binding: FragmentAnimeDetailBinding by lazy {
        FragmentAnimeDetailBinding.inflate(layoutInflater)
    }

    private val characterAdapter = CharactersAdapter(this)

    private val recommendationAdapter = AnimeListAdapter(this)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        with(binding) {
            imageTest =
                "https://images.squarespace-cdn.com/content/v1/596001c2579fb355caec7aac/1593544464864-G6P4BZA1W880KYYO16DA/ke17ZwdGBToddI8pDm48kJtX192gXcg_LgA3lCIbBpNZw-zPPgdn4jUwVcJE1ZvWQUxwkmyExglNqGp0IvTJZUJFbgE-7XRK3dMEBRBhUpyqMo3jzJhLqXTCrPyqviB5hVyaUuIqL1zNo8v7faVDIjGq_WbF1sCo3nyx4oc98OM/kakeguri_best_anime_characters_cosplay.png?format=500w"
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
                    //manageFavouriteFab?.shrink()
                } else {
                    // manageFavouriteFab?.extend()
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

        }
        return binding.root
    }

    override fun onCharacterClicked(characterView: View, anime: AnimeCharactersResponse.Character) {

    }

    override fun onAnimeClicked(animeView: View, anime: AnimeListResponse.Anime) {

    }

}
