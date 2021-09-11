package com.morse.animeslayer.ui.fragments.Tops

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.morse.animeslayer.databinding.AnimeItemLayoutBinding
import com.morse.animeslayer.domain.Anime
import com.morse.animeslayer.ui.fragments.home.AnimeListListener

class TopsPaginationAdapter(private val animeListListener: AnimeListListener) :
    PagingDataAdapter<Anime, TopsPaginationAdapter.TopViewHolder>(PAGEDATA_COMPARATOR) {

    override fun onBindViewHolder(holder: TopViewHolder, position: Int) {
        getItem(position)?.let { holder.renderTop(it) }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TopViewHolder {
        return TopViewHolder(
            AnimeItemLayoutBinding.inflate(LayoutInflater.from(parent.context))
        )
    }

    inner class TopViewHolder(private val animeItemLayoutBinding: AnimeItemLayoutBinding) :
        RecyclerView.ViewHolder(animeItemLayoutBinding.root) {

        fun renderTop(anime: Anime) {
            animeItemLayoutBinding.animeItem = anime
            with(animeItemLayoutBinding.animeListRoot) {
                setOnClickListener {
                    animeListListener.onAnimeClicked(this, anime)
                }

                setOnLongClickListener {
                    animeListListener.onAnimeLongClicked(
                        animeItemLayoutBinding.animeImage,
                        animeItemLayoutBinding.animeName,
                        anime
                    )
                    true
                }
            }
        }

    }

    companion object {
        private val PAGEDATA_COMPARATOR =
            object : DiffUtil.ItemCallback<Anime>() {
                override fun areItemsTheSame(
                    oldItem: Anime,
                    newItem: Anime
                ): Boolean =
                    (oldItem.title == newItem.title)

                override fun areContentsTheSame(
                    oldItem: Anime,
                    newItem: Anime
                ): Boolean {
                    return (oldItem.synopsis == newItem.synopsis)
                }

            }
    }


}