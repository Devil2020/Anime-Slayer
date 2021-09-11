package com.morse.animeslayer.ui.fragments.Tops

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.morse.animeslayer.R
import com.morse.animeslayer.databinding.FragmentTopsBinding

enum class TopType() {
    TopTv,
    TopAiring,
    TopMovie,
    TopUpComing,
    TopManga,
}

fun TopType.getTitle () : String {
    return when (this){
        TopType.TopTv -> "Let`s view Top TV "
        TopType.TopManga -> "Let`s view Top Manga "
        TopType.TopUpComing -> "Let`s view Top UpComing "
        TopType.TopMovie -> "Let`s view Top Movie "
        TopType.TopAiring -> "Let`s view Top Airing "
    }
}

class TopsFragment : Fragment() {
    val args by lazy {
        TopsFragmentArgs ()
    }
    var binding: FragmentTopsBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentTopsBinding.inflate(layoutInflater)
        binding?.titleName = args.topType.getTitle()
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding?.topToolbar?.setNavigationOnClickListener {
            findNavController().popBackStack()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

}