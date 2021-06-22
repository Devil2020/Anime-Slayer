package com.morse.animeslayer.ui.fragments.menu.pages

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.morse.animeslayer.R
import com.morse.animeslayer.databinding.FragmentMenuBinding
import com.morse.common.extensions.navigateSafeWithNavDirections


class MenuFragment : Fragment() , MenuItemListener{

    private val binding : FragmentMenuBinding by lazy {
        FragmentMenuBinding.inflate(layoutInflater)
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
        with(binding.menuRecyclerview){
            adapter = MenuAdapter(this@MenuFragment)
            addRecyclerListener {
                (it as MenuAdapter.MenuViewHolder).ourMenuItem
            }
        }
        binding.settingClick.setOnClickListener {
            findNavController().navigateSafeWithNavDirections(MenuFragmentDirections.actionMenuFragmentToSettingFragment())
        }
    }

    override fun onMenuClicked(menuItem: MenuItem) {
        Toast.makeText(requireContext() ,  menuItem.menuItemName , Toast.LENGTH_SHORT).show()
    }
}