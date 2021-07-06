package com.morse.animeslayer.ui.fragments.favourite

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.expertapps.base.extensions.animateCard
import com.expertapps.base.extensions.returnCardToOriginPosition
import com.morse.animeslayer.R
import com.morse.animeslayer.databinding.FragmentSearchBinding

class FavouriteFragment : Fragment() {

    var binding: FragmentSearchBinding? = null

    val favouriteClickListener = View.OnClickListener {
        when (it.id) {
            R.id.close_icon_iv -> {
                findNavController().popBackStack()
            }
            R.id.clear_list -> {
                binding?.favouriteRoot?.let { it1 ->
                    binding?.clearAllDialog?.root?.let { it2 ->
                        binding?.clearList?.let { it3 ->
                            animateCard(
                                it1,
                                it2, it3
                            )
                        }
                    }
                }
            }
            R.id.cancelRemoveDeletButton -> {
                binding?.favouriteRoot?.let { it1 ->
                    binding?.clearList?.let { it2 ->
                        binding?.clearAllDialog?.root?.let { it3 ->
                            returnCardToOriginPosition(
                                it1, it3,
                                it2, 450
                            )
                        }
                    }
                }
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
        binding = FragmentSearchBinding.inflate(layoutInflater)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding) {

            this?.closeIconIv?.setOnClickListener(
                favouriteClickListener
            )

            this?.clearList?.setOnClickListener(
                favouriteClickListener
            )

            this?.clearAllDialog?.cancelRemoveDeletButton?.setOnClickListener(
                favouriteClickListener
            )

            this?.clearAllDialog?.confirmRemoveDeletButton?.setOnClickListener(
                favouriteClickListener
            )

        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

}