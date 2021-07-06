package com.morse.animeslayer.ui.fragments.menu.pages.menu

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.morse.animeslayer.databinding.FragmentMenuBinding
import com.morse.animeslayer.ui.fragments.menu.host.MenuBottomSheet
import com.morse.common.extensions.navigateSafeWithNavDirections


class MenuFragment : Fragment(), MenuItemListener {

    private var binding: FragmentMenuBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMenuBinding.inflate(layoutInflater)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding?.menuRecyclerview) {
            this?.adapter = MenuAdapter(this@MenuFragment)
            this?.addRecyclerListener {
                (it as MenuAdapter.MenuViewHolder).ourMenuItem
            }
        }
        binding?.settingClick?.setOnClickListener {
            findNavController().navigateSafeWithNavDirections(MenuFragmentDirections.actionMenuFragmentToSettingFragment())
        }
    }

    override fun onMenuClicked(menuItem: MenuItem) {
        val menuBundle = Bundle().apply {
            putString(MenuType::class.java.name, menuItem.menuType.name)
        }
        requireActivity().supportFragmentManager.setFragmentResult(
            MenuFragment::class.java.name,
            menuBundle
        )
        requireActivity().supportFragmentManager.setFragmentResult(
            MenuBottomSheet::class.java.name,
            Bundle.EMPTY
        )

    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }


}