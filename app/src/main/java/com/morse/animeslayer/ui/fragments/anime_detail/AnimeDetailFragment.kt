package com.morse.animeslayer.ui.fragments.anime_detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.morse.animeslayer.databinding.FragmentAnimeDetailBinding

class AnimeDetailFragment : Fragment() {

    private val binding: FragmentAnimeDetailBinding by lazy {
        FragmentAnimeDetailBinding.inflate(layoutInflater)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        with(binding) {
            imageTest =
                "https://images.squarespace-cdn.com/content/v1/596001c2579fb355caec7aac/1593544464864-G6P4BZA1W880KYYO16DA/ke17ZwdGBToddI8pDm48kJtX192gXcg_LgA3lCIbBpNZw-zPPgdn4jUwVcJE1ZvWQUxwkmyExglNqGp0IvTJZUJFbgE-7XRK3dMEBRBhUpyqMo3jzJhLqXTCrPyqviB5hVyaUuIqL1zNo8v7faVDIjGq_WbF1sCo3nyx4oc98OM/kakeguri_best_anime_characters_cosplay.png?format=500w"
            scoreTestKey = "Score"
            scoreTestValue= "#1960"

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


        }
        return binding.root
    }
}
