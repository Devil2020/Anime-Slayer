package com.morse.animeslayer.ui.fragments.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.morse.animeslayer.databinding.AnimeItemLayoutBinding
import com.morse.animeslayer.domain.AnimeListResponse
import com.morse.animeslayer.ui.fragments.menu.MenuItem

interface AnimeListListener {
    fun onAnimeClicked ( animeView : View , anime : AnimeListResponse.Anime)
}


class AnimeListAdapter (private val animeListListener: AnimeListListener) : RecyclerView.Adapter<AnimeListAdapter.AnimeViewHolder>() {

    private val listOfAnime = arrayListOf(
        AnimeListResponse.Anime(
            title = "DIGIMON ADVENTURE: 2020",
            imageUrl = "https://images.squarespace-cdn.com/content/v1/596001c2579fb355caec7aac/1593544464864-G6P4BZA1W880KYYO16DA/ke17ZwdGBToddI8pDm48kJtX192gXcg_LgA3lCIbBpNZw-zPPgdn4jUwVcJE1ZvWQUxwkmyExglNqGp0IvTJZUJFbgE-7XRK3dMEBRBhUpyqMo3jzJhLqXTCrPyqviB5hVyaUuIqL1zNo8v7faVDIjGq_WbF1sCo3nyx4oc98OM/kakeguri_best_anime_characters_cosplay.png?format=500w",
            score = 8.21
        ) ,
        AnimeListResponse.Anime(
            title = "DIGIMON ADVENTURE: 2020",
            imageUrl = "https://images.squarespace-cdn.com/content/v1/596001c2579fb355caec7aac/1593544464864-G6P4BZA1W880KYYO16DA/ke17ZwdGBToddI8pDm48kJtX192gXcg_LgA3lCIbBpNZw-zPPgdn4jUwVcJE1ZvWQUxwkmyExglNqGp0IvTJZUJFbgE-7XRK3dMEBRBhUpyqMo3jzJhLqXTCrPyqviB5hVyaUuIqL1zNo8v7faVDIjGq_WbF1sCo3nyx4oc98OM/kakeguri_best_anime_characters_cosplay.png?format=500w",
            score = 8.21
        ) ,
        AnimeListResponse.Anime(
            title = "DIGIMON ADVENTURE: 2020",
            imageUrl = "https://images.squarespace-cdn.com/content/v1/596001c2579fb355caec7aac/1593544464864-G6P4BZA1W880KYYO16DA/ke17ZwdGBToddI8pDm48kJtX192gXcg_LgA3lCIbBpNZw-zPPgdn4jUwVcJE1ZvWQUxwkmyExglNqGp0IvTJZUJFbgE-7XRK3dMEBRBhUpyqMo3jzJhLqXTCrPyqviB5hVyaUuIqL1zNo8v7faVDIjGq_WbF1sCo3nyx4oc98OM/kakeguri_best_anime_characters_cosplay.png?format=500w",
            score = 8.21
        ) ,
        AnimeListResponse.Anime(
            title = "DIGIMON ADVENTURE: 2020",
            imageUrl = "https://images.squarespace-cdn.com/content/v1/596001c2579fb355caec7aac/1593544464864-G6P4BZA1W880KYYO16DA/ke17ZwdGBToddI8pDm48kJtX192gXcg_LgA3lCIbBpNZw-zPPgdn4jUwVcJE1ZvWQUxwkmyExglNqGp0IvTJZUJFbgE-7XRK3dMEBRBhUpyqMo3jzJhLqXTCrPyqviB5hVyaUuIqL1zNo8v7faVDIjGq_WbF1sCo3nyx4oc98OM/kakeguri_best_anime_characters_cosplay.png?format=500w",
            score = 8.21
        ) ,
        AnimeListResponse.Anime(
            title = "DIGIMON ADVENTURE: 2020",
            imageUrl = "https://images.squarespace-cdn.com/content/v1/596001c2579fb355caec7aac/1593544464864-G6P4BZA1W880KYYO16DA/ke17ZwdGBToddI8pDm48kJtX192gXcg_LgA3lCIbBpNZw-zPPgdn4jUwVcJE1ZvWQUxwkmyExglNqGp0IvTJZUJFbgE-7XRK3dMEBRBhUpyqMo3jzJhLqXTCrPyqviB5hVyaUuIqL1zNo8v7faVDIjGq_WbF1sCo3nyx4oc98OM/kakeguri_best_anime_characters_cosplay.png?format=500w",
            score = 8.21
        ) ,
        AnimeListResponse.Anime(
            title = "DIGIMON ADVENTURE: 2020",
            imageUrl = "https://images.squarespace-cdn.com/content/v1/596001c2579fb355caec7aac/1593544464864-G6P4BZA1W880KYYO16DA/ke17ZwdGBToddI8pDm48kJtX192gXcg_LgA3lCIbBpNZw-zPPgdn4jUwVcJE1ZvWQUxwkmyExglNqGp0IvTJZUJFbgE-7XRK3dMEBRBhUpyqMo3jzJhLqXTCrPyqviB5hVyaUuIqL1zNo8v7faVDIjGq_WbF1sCo3nyx4oc98OM/kakeguri_best_anime_characters_cosplay.png?format=500w",
            score = 8.21
        ) ,
        AnimeListResponse.Anime(
            title = "DIGIMON ADVENTURE: 2020",
            imageUrl = "https://images.squarespace-cdn.com/content/v1/596001c2579fb355caec7aac/1593544464864-G6P4BZA1W880KYYO16DA/ke17ZwdGBToddI8pDm48kJtX192gXcg_LgA3lCIbBpNZw-zPPgdn4jUwVcJE1ZvWQUxwkmyExglNqGp0IvTJZUJFbgE-7XRK3dMEBRBhUpyqMo3jzJhLqXTCrPyqviB5hVyaUuIqL1zNo8v7faVDIjGq_WbF1sCo3nyx4oc98OM/kakeguri_best_anime_characters_cosplay.png?format=500w",
            score = 8.21
        ) , AnimeListResponse.Anime(
            title = "DIGIMON ADVENTURE: 2020",
            imageUrl = "https://images.squarespace-cdn.com/content/v1/596001c2579fb355caec7aac/1593544464864-G6P4BZA1W880KYYO16DA/ke17ZwdGBToddI8pDm48kJtX192gXcg_LgA3lCIbBpNZw-zPPgdn4jUwVcJE1ZvWQUxwkmyExglNqGp0IvTJZUJFbgE-7XRK3dMEBRBhUpyqMo3jzJhLqXTCrPyqviB5hVyaUuIqL1zNo8v7faVDIjGq_WbF1sCo3nyx4oc98OM/kakeguri_best_anime_characters_cosplay.png?format=500w",
            score = 8.21
        )
    )

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AnimeViewHolder {
        val layoutInflation = LayoutInflater.from(parent.context)
        return AnimeViewHolder(
            AnimeItemLayoutBinding.inflate(layoutInflation)
        )
    }

    override fun onBindViewHolder(holder: AnimeViewHolder, position: Int) {
        holder.bindAnimeDataToBinding(listOfAnime[position])
    }

    override fun getItemCount(): Int {
        return listOfAnime.size
    }

    inner class AnimeViewHolder(val binding: AnimeItemLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bindAnimeDataToBinding(animeItem: AnimeListResponse.Anime) {
            binding.animeItem = animeItem
            binding.root.setOnClickListener {
                animeListListener.onAnimeClicked(binding.root , animeItem)
            }
        }

    }
}