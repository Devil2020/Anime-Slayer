package com.morse.animeslayer.ui.fragments.splash

import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.annotation.DrawableRes
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.morse.animeslayer.R

class SplashImageAdapter constructor( var listOfImageResources: ArrayList<Drawable>) :
    RecyclerView.Adapter<SplashImageAdapter.SplashViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SplashViewHolder {
        return SplashViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.splash_item_layout, parent, false)
        )
    }

    override fun onBindViewHolder(holder: SplashViewHolder, position: Int) {
        holder.bind(listOfImageResources[position])
    }

    override fun getItemCount(): Int {
        return listOfImageResources.size
    }

    inner class SplashViewHolder(val view: View) : RecyclerView.ViewHolder(view) {

        val splashImageView = view.findViewById<ImageView>(R.id.splashItemImageView)

        fun bind (  drawable : Drawable){
            Glide.with(view).load(drawable).into(splashImageView)
        }

    }

}