package com.morse.animeslayer.ui.fragments.favourite

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.expertapps.base.extensions.animateCard
import com.expertapps.base.extensions.returnCardToOriginPosition
import com.morse.animeslayer.R
import com.morse.animeslayer.databinding.FragmentSearchBinding

class FavouriteFragment : Fragment() {

    val binding : FragmentSearchBinding by lazy {
        FragmentSearchBinding.inflate(layoutInflater)
    }

    val favouriteClickListener = View.OnClickListener {
        when (it.id){
            R.id.close_icon_iv -> {
                findNavController().popBackStack()
            }
            R.id.clear_list -> {
                animateCard(binding.favouriteRoot , binding.clearAllDialog.root , binding.clearList )
            }
            R.id.cancelRemoveDeletButton -> {
                returnCardToOriginPosition(binding.favouriteRoot , binding.clearAllDialog.root , binding.clearList  , 450)
            }
            R.id.confirmRemoveDeletButton -> {
                // clear your db
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
        with(binding){

            closeIconIv.setOnClickListener (
                favouriteClickListener
            )

            clearList.setOnClickListener (
                favouriteClickListener
            )

            clearAllDialog.cancelRemoveDeletButton.setOnClickListener (
                favouriteClickListener
            )

            clearAllDialog.confirmRemoveDeletButton.setOnClickListener (
                favouriteClickListener
            )

        }
    }

}