package com.morse.animeslayer.ui.fragments.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.morse.animeslayer.databinding.AnimeItemLayoutBinding
import com.morse.animeslayer.domain.AnimeListResponse

interface AnimeListListener {
    fun onAnimeClicked ( animeView : View , anime : AnimeListResponse.Anime)
}


class AnimeListAdapter (private val animeListListener: AnimeListListener) : RecyclerView.Adapter<AnimeListAdapter.AnimeViewHolder>() {

    private val listOfAnime = arrayListOf(
        AnimeListResponse.Anime(
            title = "Fruits Basket: The Final",
            imageUrl = "https://cdn.myanimelist.net/images/anime/1085/114792.jpg",
            score = 9.07
        ) ,
        AnimeListResponse.Anime(
            title = "Kobayashi-san Chi no Maid Drag...",
            imageUrl = "https://cdn.myanimelist.net/images/anime/1252/115539.jpg",
            score = 8.21
        ) ,
        AnimeListResponse.Anime(
            title = "One Piece",
            imageUrl = "https://cdn.myanimelist.net/images/anime/6/73245.jpg",
            score = 8.21
        ) ,
        AnimeListResponse.Anime(
            title = "Holo no Graffiti",
            imageUrl = "https://cdn.myanimelist.net/images/anime/1259/110227.jpg",
            score = 8.21
        ) ,
        AnimeListResponse.Anime(
            title = "A Girl & Her Guard Dog",
            imageUrl = "https://cdn.myanimelist.net/s/common/store/cover/9263/987508de8cd023c74fd833ba4aec913c7bb579ff9a767d16636931ae96df7766/l@2x.jpg",
            score = 8.21
        ) ,
        AnimeListResponse.Anime(
            title = "Tokyo Revengers",
            imageUrl = "https://cdn.myanimelist.net/images/anime/1884/114790.jpg",
            score = 8.21
        ) ,
        AnimeListResponse.Anime(
            title = "Battle Angel Alita: Last Order Omnibus",
            imageUrl = "https://cdn.myanimelist.net/s/common/store/cover/3638/2620c52ffcc6246fedc66e55e960e7e8ee692763da429986459f86dde14be714/l@2x.jpg",
            score = 8.21
        ) , AnimeListResponse.Anime(
            title = "The Devil Is a Part-Timer!",
            imageUrl = "https://cdn.myanimelist.net/s/common/store/cover/5121/451b3db3f845c4bc7562d32c0dff607dd773b269746a062ea1fe89e9b25adb63/l@2x.jpg",
            score = 8.21
        ) ,
        AnimeListResponse.Anime(
            title = "Boku no Hero Academia 5th Season",
            imageUrl = "https://cdn.myanimelist.net/images/anime/1911/113611.jpg",
            score = 8.21
        ) ,
        AnimeListResponse.Anime(
            title = "Jouran: The Princess of Snow and Blood",
            imageUrl = "https://cdn.myanimelist.net/images/anime/1289/115673.jpg",
            score = 8.21
        ) ,
        AnimeListResponse.Anime(
            title = "Fullmetal Alchemist: Brotherhood",
            imageUrl = "https://cdn.myanimelist.net/images/anime/1223/96541.jpg",
            score = 8.21
        ) ,
        AnimeListResponse.Anime(
            title = "Death Note",
            imageUrl = "https://cdn.myanimelist.net/images/anime/9/9453.jpg",
            score = 8.21
        ) ,
        AnimeListResponse.Anime(
            title = "One Punch Man",
            imageUrl = "https://cdn.myanimelist.net/images/anime/12/76049.jpg",
            score = 8.21
        ) ,
        AnimeListResponse.Anime(
            title = "Kimi no Na wa.",
            imageUrl = "https://cdn.myanimelist.net/images/anime/5/87048.jpg",
            score = 8.21
        ) ,
        AnimeListResponse.Anime(
            title = "Tokyo Ghoul",
            imageUrl = "https://cdn.myanimelist.net/images/anime/5/64449.jpg",
            score = 8.21
        ) , AnimeListResponse.Anime(
            title = "Attack on Titan",
            imageUrl = "https://cdn.myanimelist.net/images/anime/10/47347.jpg",
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