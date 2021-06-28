package com.morse.animeslayer.utils

import android.graphics.drawable.Drawable
import android.view.View
import androidx.constraintlayout.widget.ConstraintLayout
import com.expertapps.base.extensions.loadImageWithCornerRadius
import com.morse.animeslayer.R
import com.morse.animeslayer.databinding.InfoPopupCardLayoutBinding
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking

fun InfoPopupCardLayoutBinding.render(imageUrl: String, name: String, description: String) {
    animeImageview.loadImageWithCornerRadius(
        imageUrl,
        name,
        root.resources.getDimension(com.morse.common.R.dimen._20sdp).toInt()
    )
    animeName.text = name
    animeDescribtion.text = description
}

fun View.getSplashDrawableByIndex (index : Int) : Drawable{
    return when (index){
        0 ->return  resources.getDrawable(R.drawable.image0)
        1 ->return  resources.getDrawable(R.drawable.image1)
        2 ->return  resources.getDrawable(R.drawable.image2)
        3 ->return  resources.getDrawable(R.drawable.image3)
        4 ->return  resources.getDrawable(R.drawable.image4)
        else -> resources.getDrawable(R.drawable.image0)
    }
}

fun ConstraintLayout.repeatImages() {
    runBlocking {
        repeat(4) {
            background = getSplashDrawableByIndex(it)
           // delay(200000)
        }
    }
}