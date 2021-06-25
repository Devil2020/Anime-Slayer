package com.morse.animeslayer.ui.fragments.menu.pages.menu

import androidx.annotation.DrawableRes

enum class MenuType {
    Current_Season ,
    Schedule ,
    Favourite ,
    Top_Tv ,
    Top_Airing ,
    Top_Movie ,
    Top_Incoming ,
    Top_Manga
}

data class MenuItem(
    var menuItemName: String,
    val menuType: MenuType ,
    @DrawableRes var menuItemImage: Int
)
