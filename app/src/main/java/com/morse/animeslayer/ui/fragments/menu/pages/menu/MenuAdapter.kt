package com.morse.animeslayer.ui.fragments.menu.pages.menu


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.morse.animeslayer.R
import com.morse.animeslayer.databinding.MenuItemLayoutBinding

interface MenuItemListener {
    fun onMenuClicked (menuItem : MenuItem)
}

class MenuAdapter (val listener : MenuItemListener) : RecyclerView.Adapter<MenuAdapter.MenuViewHolder> () {

    private val menuItems : ArrayList<MenuItem> by lazy {
        arrayListOf(
            MenuItem ("Current Season" , MenuType.Current_Season , R.drawable.ic_electric ) ,
            MenuItem ("Schedule", MenuType.Schedule ,  R.drawable.ic_schedule) ,
            MenuItem ("Favourite", MenuType.Favourite ,  R.drawable.favorite) ,
            MenuItem ("Top Tv", MenuType.Top_Tv ,  R.drawable.ic_top_games_star) ,
            MenuItem ("Top Airing", MenuType.Top_Airing ,  R.drawable.ic_top_games_star) ,
            MenuItem ("Top Movie", MenuType.Top_Movie ,  R.drawable.ic_top_games_star) ,
            MenuItem ("Top Upcoming", MenuType.Top_Incoming ,  R.drawable.ic_top_games_star) ,
            MenuItem ("Top Manga", MenuType.Top_Manga ,  R.drawable.ic_top_games_star) ,
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

            var ourMenuItem : MenuItem?= null

            fun bindMenuToItem (menuItem: MenuItem){
                ourMenuItem = menuItem
                binding.menuItem = menuItem
                binding.root.setOnClickListener {
                    listener.onMenuClicked(menuItem)
                }
            }

    }



}