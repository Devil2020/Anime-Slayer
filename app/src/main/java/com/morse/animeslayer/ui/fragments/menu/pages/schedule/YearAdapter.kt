package com.morse.animeslayer.ui.fragments.menu.pages.schedule

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.morse.animeslayer.R

class YearAdapter : RecyclerView.Adapter<YearAdapter.YearViewHolder>() {

    val years = arrayListOf("1997","1998","1999","2000","2001","2002","2003","2004","2005","2006","2007","2008","2009","2010","2011","2012","2013","2014","2015","2016","2017","2018","2019","2020","2021","2022","2023")

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): YearViewHolder {
        return YearViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.year_item_layout , parent , false)
        )
    }

    override fun onBindViewHolder(holder: YearViewHolder, position: Int) {
        holder.yearTextView.setText("${years[position]}")
    }

    override fun getItemCount(): Int {
        return years.size
    }

    inner class YearViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {
        val yearTextView = view.findViewById<TextView>(R.id.yearValue)
    }

}