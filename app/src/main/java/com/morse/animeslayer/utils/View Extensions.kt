package com.morse.animeslayer.utils

import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.expertapps.base.extensions.loadImageWithCornerRadius
import com.morse.animeslayer.databinding.InfoPopupCardLayoutBinding
import com.yarolegovich.discretescrollview.DiscreteScrollView
import com.yarolegovich.discretescrollview.transform.Pivot
import com.yarolegovich.discretescrollview.transform.ScaleTransformer
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
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

fun DiscreteScrollView.setup() {
    this.scrollToPosition(1)
    this.setItemTransitionTimeMillis(400)
    this?.setItemTransformer(

        ScaleTransformer.Builder()
            .setMaxScale(1.05f)
            .setMinScale(0.8f)
            .setPivotX(Pivot.X.CENTER) // CENTER is a default one
            .setPivotY(Pivot.Y.BOTTOM) // CENTER is a default one
            .build()

    )
    this?.setSlideOnFling(true)
}

fun Fragment.change ( discreteScrollView: DiscreteScrollView){

    lifecycleScope.launch {
        repeat(5) {
            discreteScrollView.scrollToPosition(it)
            delay(2000)
        }
    }
}
