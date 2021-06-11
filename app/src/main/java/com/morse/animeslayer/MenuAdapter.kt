package com.morse.animeslayer

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.morse.animeslayer.databinding.MenuItemLayoutBinding

class MenuAdapter : RecyclerView.Adapter<MenuAdapter.MenuViewHolder> () {

    private val menuItems : ArrayList<MenuItem> by lazy {
        arrayListOf(
            MenuItem ("Current Season" , R.drawable.ic_electric) ,
            MenuItem ("Schedule", R.drawable.ic_schedule) ,
            MenuItem ("Favourite", R.drawable.favorite) ,
            MenuItem ("Top Tv", R.drawable.ic_top_games_star) ,
            MenuItem ("Top Airing", R.drawable.ic_top_games_star) ,
            MenuItem ("Top Movie", R.drawable.ic_top_games_star) ,
            MenuItem ("Top Upcoming", R.drawable.ic_top_games_star) ,
            MenuItem ("Top Manga", R.drawable.ic_top_games_star) ,
        )
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MenuViewHolder {
        val layoutInflation = LayoutInflater.from(parent.context)
        return MenuViewHolder(
            MenuItemLayoutBinding.inflate(layoutInflation)
        )
    }

    override fun onBindViewHolder(holder: MenuViewHolder, position: Int) {
        holder.bindMenuToItem(menuItems.get(position))
    }

    override fun getItemCount(): Int {
       return menuItems.size
    }

    inner class MenuViewHolder(val binding: MenuItemLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {

            fun bindMenuToItem (menuItem: MenuItem){
                binding.menuItem = menuItem
            }

    }

}