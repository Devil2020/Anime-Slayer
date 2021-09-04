package com.morse.animeslayer.ui.fragments.detail.anime

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.morse.animeslayer.databinding.CharacterItemLayoutBinding
import com.morse.animeslayer.domain.AnimeCharactersResponse

interface CharacterListener {
    fun onCharacterClicked(characterView: View, anime: AnimeCharactersResponse.Character)
}


class CharactersAdapter(private val characterListener: CharacterListener) :
    RecyclerView.Adapter<CharactersAdapter.CharacterViewHolder>() {

    private val listOfCharacters = arrayListOf(
        AnimeCharactersResponse.Character(
            name = "Mohammed Morse",
            imageUrl = "https://s2.favim.com/orig/140929/anime-anime-boy-kawaii-ninja-Favim.com-2111811.jpg",
            role = "Me"
        ),
        AnimeCharactersResponse.Character(
            name = "Ahmed Morse",
            imageUrl = "https://www.pngkit.com/png/full/492-4921532_manga-anime-sad-anime-anime-boy-crying-hot.png",
            role = "Brother"
        ),
        AnimeCharactersResponse.Character(
            name = "Salma Morse",
            imageUrl = "https://www.thiswaifudoesnotexist.net/example-92770.jpg",
            role = "Sister"
        ),
        AnimeCharactersResponse.Character(
            name = "Esraa Morse",
            imageUrl = "https://www.thiswaifudoesnotexist.net/example-22944.jpg",
            role = "Sister"
        )

    )

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CharacterViewHolder {
        val layoutInflation = LayoutInflater.from(parent.context)
        return CharacterViewHolder(
            CharacterItemLayoutBinding.inflate(layoutInflation)
        )
    }

    override fun onBindViewHolder(holder: CharacterViewHolder, position: Int) {
        holder.bindCharacterDataToBinding(listOfCharacters[position])
    }

    override fun getItemCount(): Int {
        return listOfCharacters.size
    }

    inner class CharacterViewHolder(val binding: CharacterItemLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bindCharacterDataToBinding(characterItem: AnimeCharactersResponse.Character) {
            binding.character = characterItem
            binding.root.setOnClickListener {
                characterListener.onCharacterClicked(binding.root, characterItem)
            }
        }

    }
}