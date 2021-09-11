package com.morse.animeslayer.ui.fragments.search

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.navigation.fragment.findNavController
import com.morse.animeslayer.R
import com.morse.animeslayer.databinding.FragmentSearchBinding
import com.morse.animeslayer.domain.Anime
import com.morse.animeslayer.ui.fragments.home.ANIME_LIST
import com.morse.animeslayer.ui.fragments.home.AnimeListAdapter
import com.morse.animeslayer.ui.fragments.home.AnimeListListener
import com.morse.common.utils.ItemOffsetDecoration


class SearchFragment : Fragment() , AnimeListListener {

    var binding : FragmentSearchBinding ?= null
    private var animeAdapter: AnimeListAdapter? = null
    lateinit var itemDecorator: ItemOffsetDecoration

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSearchBinding.inflate(layoutInflater)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        itemDecorator = ItemOffsetDecoration(
            requireContext(),
            R.dimen._9sdp
        )
        with(binding){
            this?.searchToolbar?.setNavigationOnClickListener {
                findNavController().popBackStack()
            }
            animeAdapter = AnimeListAdapter(this@SearchFragment)
            this?.searchRecyclerview?.scrollToPosition(0)
            this?.searchRecyclerview?.adapter = animeAdapter
            this?.searchRecyclerview?.addItemDecoration(
                itemDecorator
            )
            animeAdapter?.submit(ANIME_LIST)
        }
    }

    override fun onAnimeClicked(animeView: View, anime: Anime) {

    }

    override fun onAnimeLongClicked(animeImageView: ImageView, animeName: TextView, anime: Anime) {

    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

}