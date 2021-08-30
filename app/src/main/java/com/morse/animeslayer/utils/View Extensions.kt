package com.morse.animeslayer.utils

import android.R
import android.graphics.Color
import android.media.AudioAttributes
import android.media.AudioManager
import android.media.MediaPlayer
import android.net.Uri
import android.os.Build
import android.view.View
import android.widget.RelativeLayout
import android.widget.VideoView
import androidx.annotation.RequiresApi
import androidx.databinding.BindingAdapter
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.RecyclerView
import com.expertapps.base.extensions.loadImageWithCornerRadius
import com.morse.animeslayer.databinding.InfoPopupCardLayoutBinding
import com.morse.animeslayer.ui.fragments.menu.pages.schedule.YearAdapter
import com.yarolegovich.discretescrollview.DiscreteScrollView
import com.yarolegovich.discretescrollview.transform.Pivot
import com.yarolegovich.discretescrollview.transform.ScaleTransformer
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch



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

fun Fragment.change(discreteScrollView: DiscreteScrollView) {

    lifecycleScope.launch {
        repeat(5) {
            discreteScrollView.scrollToPosition(it)
            delay(2000)
        }
    }
}

fun Fragment.releaseVideo (videoView: VideoView){
    videoView.pause()
    videoView.stopPlayback()
}

fun Fragment.closeVideo (videoView: VideoView){
    videoView.pause()
    videoView.stopPlayback()
    videoView.clearAnimation()
    videoView.suspend() // clears media player
}

fun Fragment.manageVideo(videoView: VideoView, onCompleteAction : () -> Unit) {
    val stringBuilder: StringBuilder =
        StringBuilder().append("android.resource://com.morse.animeslayer").append("/")
            .append(com.morse.animeslayer.R.raw.splash_v2)
    videoView.setVideoURI(Uri.parse(stringBuilder.toString()))
    //videoView.setZOrderOnTop(true)
    videoView.setBackgroundColor(Color.TRANSPARENT)
    videoView.setOnErrorListener { mediaPlayer: MediaPlayer?, i: Int, i1: Int ->
        //wait for 3 seconds
        Thread {
            try {
                Thread.sleep(3000)
            } catch (e: InterruptedException) {
                e.printStackTrace()
            }
        }.start()

        return@setOnErrorListener false
    }
    videoView.setOnCompletionListener {
        onCompleteAction.invoke() }
    videoView.start()
}
