package com.morse.animeslayer.utils

import com.expertapps.base.extensions.loadImageWithCornerRadius
import com.morse.animeslayer.databinding.PopupCardLayoutBinding

fun PopupCardLayoutBinding.render (imageUrl : String, name : String, description : String){
    animeImageview.loadImageWithCornerRadius(imageUrl , name , root.resources.getDimension(com.morse.common.R.dimen._20sdp).toInt())
    animeName.text = name
    animeDescribtion.text = description
}
