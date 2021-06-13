package com.morse.animeslayer.ui.fragments.home

import android.graphics.Rect
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.morse.animeslayer.R
import com.morse.animeslayer.databinding.FragmentHomeBinding
import com.morse.animeslayer.ui.fragments.menu.MenuBottomSheet
import com.morse.common.extensions.navigateSafe
import java.util.*

class HomeFragment : Fragment() {

    private val animeAdapter = AnimeListAdapter ()
    private val spacing : Int by lazy {
        resources.getDimension(R.dimen._25sdp).toInt()
    }
    val binding: FragmentHomeBinding by lazy {
        FragmentHomeBinding.inflate(layoutInflater)
    }

    private val homeClickListener = View.OnClickListener {
        when (it.id){
            R.id.me_icon_iv -> {
                it.startAnimation(
                    AnimationUtils.loadAnimation(requireContext() , R.anim.scale_click)
                )
                MenuBottomSheet().show(parentFragmentManager , MenuBottomSheet.TAG)
            }
            R.id.search_icon_iv -> {
                findNavController().navigateSafe(R.id.action_homeFragment_to_searchFragment)
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding) {
            this.meIconIv.setOnClickListener (
                homeClickListener
            )
            this.searchIconIv.setOnClickListener  (
                homeClickListener
            )
            this.animeListRv.adapter = animeAdapter
//            this.animeListRv.addItemDecoration(object : RecyclerView.ItemDecoration() {
//                override fun getItemOffsets(
//                    outRect: Rect,
//                    view: View,
//                    parent: RecyclerView,
//                    state: RecyclerView.State
//                ) {
//                    val position =
//                        parent.getChildAdapterPosition(view!!) // item position
//                    val spanCount = 2
//
//                    if (position >= 0) {
//                        val column = position % spanCount // item column
//                        if (Locale.getDefault().language.equals("en")){
//                            outRect.right =
//                                spacing - column * spacing / spanCount // spacing - column * ((1f / spanCount) * spacing)
//                            outRect.left =
//                                (column + 1) * spacing / spanCount // (column + 1) * ((1f / spanCount) * spacing)
//                            //if (position < spanCount) { // top edge
//                            outRect.top = spacing
//                            // }
//                            outRect.bottom = spacing // item bottom
//                        }
//                        else {
//                            outRect.left =
//                                spacing - column * spacing / spanCount // spacing - column * ((1f / spanCount) * spacing)
//                            outRect.right =
//                                (column + 1) * spacing / spanCount // (column + 1) * ((1f / spanCount) * spacing)
//                            //if (position < spanCount) { // top edge
//                            outRect.top = spacing
//                            // }
//                            outRect.bottom = spacing // item bottom
//                        }
//
//                    } else {
//                        outRect.left = 0
//                        outRect.right = 0
//                        outRect.top = 0
//                        outRect.bottom = 0
//                    }
//                }
//            })
        }
    }


}