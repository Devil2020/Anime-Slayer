package com.morse.animeslayer.ui.fragments.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.morse.animeslayer.databinding.AnimeItemLayoutBinding
import com.morse.animeslayer.domain.AnimeListResponse

class AnimeListAdapter : RecyclerView.Adapter<AnimeListAdapter.AnimeViewHolder>() {

    private val listOfAnime = arrayListOf<AnimeListResponse.Anime>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AnimeViewHolder {
        val layoutInflation = LayoutInflater.from(parent.context)
        return AnimeViewHolder(
            AnimeItemLayoutBinding.inflate(layoutInflation)
        )
    }

    override fun onBindViewHolder(holder: AnimeViewHolder, position: Int) {
        TODO("Not yet implemented")
    }

    override fun getItemCount(): Int {
        return listOfAnime.size
    }

    inner class AnimeViewHolder(val binding: AnimeItemLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bindAnimeDataToBinding(animeItem: AnimeListResponse.Anime) {

            binding.

        }

    }
}