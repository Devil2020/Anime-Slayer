package com.morse.common.adapter

import android.widget.LinearLayout
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.expertapps.base.libraries.filter.base.BaseAdapter
import com.morse.common.utils.ItemOffsetDecoration


fun <T: Any> BaseAdapter<T>.onItemClick (executeAction : (T) ->Unit ) : BaseAdapter<T> {
    action = executeAction
    return this
}

fun <T : Any> BaseAdapter<T>.withRecyclerView (inputRecyclerView: RecyclerView?) : BaseAdapter<T> {
    if (inputRecyclerView != null) {
        recyclerView = inputRecyclerView
    }
    recyclerView?.adapter = this
    return this
}

fun  <T : Any> BaseAdapter<T>.withSelectedId(vararg incomingSelectedObject: T): BaseAdapter<T> {
    selectedObject.clear()
    selectedObject.addAll(incomingSelectedObject .toList())
    return this
}

fun  <T : Any> BaseAdapter<T>.isLinearLayout(orientation : Int = RecyclerView.VERTICAL): BaseAdapter<T> {
    recyclerView.layoutManager = LinearLayoutManager (recyclerView.context , orientation , false)
    return this
}

fun  <T : Any> BaseAdapter<T>.isGridLayout(orientation : Int = RecyclerView.VERTICAL): BaseAdapter<T> {
    recyclerView.layoutManager = GridLayoutManager (recyclerView.context , 2)
    return this
}

fun  <T : Any> BaseAdapter<T>.setItemDecoration(itemDecoration : RecyclerView.ItemDecoration = ItemOffsetDecoration(9)): BaseAdapter<T> {
    recyclerView.addItemDecoration(itemDecoration)
    return this
}

fun  <T : Any> BaseAdapter<T>.submitData(listOfData : ArrayList<T>): BaseAdapter<T> {
    submit(listOfData)
    return this
}