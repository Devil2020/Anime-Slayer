package com.expertapps.connect.utils

import android.content.Context
import android.graphics.Color
import android.text.TextUtils
import android.view.MenuItem
import android.view.View
import android.widget.EditText
import androidx.appcompat.widget.SearchView
import androidx.core.view.MenuItemCompat
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent

class SearchUtil  {

    private var searchView: SearchView
    private var context: Context

    private constructor(context: Context) {
        this.context = context
        searchView = SearchView(context)
    }

    fun setupSearchView(menuItem : MenuItem) {
        val searchEditId = androidx.appcompat.R.id.search_src_text
        val et = searchView.findViewById<View>(searchEditId) as EditText
        et.setTextColor(Color.WHITE)
        val v: View = searchView.findViewById(androidx.appcompat.R.id.search_plate)
        v.setBackgroundColor(Color.TRANSPARENT)
        menuItem.actionView = searchView
    }

     fun setupListener(
        listener: SearchViewListener
    ) {
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                listener.onSearchButtonClicked(query)
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                if(TextUtils.isEmpty(newText)){
                    listener.onClearAllSearchText()
                }
                else {
                    listener.onSearchingStillRunning(newText)
                }
                return true
            }
        })

    }

    fun removeListeners() {
        searchView.setOnQueryTextListener(null)
    }

    companion object {

        private val LOCK = Any()
        @Volatile
        private var searchUtils: SearchUtil? = null

        fun Builder(context: Context): SearchUtil {
            return synchronized(LOCK) {
                return@synchronized searchUtils ?: SearchUtil(context).apply {
                    searchUtils = this
                }
            }
        }

    }

    interface SearchViewListener {

        fun onClearAllSearchText ()

        fun onSearchingStillRunning(searchText: String?)

        fun onSearchButtonClicked(searchText: String?)

    }

}