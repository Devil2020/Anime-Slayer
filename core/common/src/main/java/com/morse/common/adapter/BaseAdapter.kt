package com.expertapps.base.libraries.filter.base

import android.annotation.SuppressLint
import androidx.annotation.NonNull
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView

abstract class BaseAdapter<T : Any> :
    RecyclerView.Adapter<BaseViewHolder>() {

    open internal lateinit var action : (T) -> Unit

    open internal lateinit var selectedObject : ArrayList<T>

    open internal lateinit var recyclerView: RecyclerView

    private val differ: AsyncListDiffer<T> =
        AsyncListDiffer(this, DIFF_CALLBACK())

    val data: List<T>
        get() = differ.currentList

    fun submit(list: ArrayList<T>) {
        differ.submitList(list)
    }

    fun clear() {
        differ.submitList(emptyList())
    }

    abstract fun bind(viewHolder: BaseViewHolder, position: Int)

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        holder.dataBinding.root.setOnClickListener {
            action.invoke(data[position])
        }
        bind(holder, position)
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    private fun DIFF_CALLBACK()=
        object : DiffUtil.ItemCallback<T>() {
            override fun areItemsTheSame(
                @NonNull oldFilter: T,
                @NonNull newFilter: T
            ): Boolean {
                return oldFilter.equals(newFilter)
            }

            @SuppressLint("DiffUtilEquals")
            override fun areContentsTheSame(
                @NonNull oldFilter: T,
                @NonNull newFilter: T
            ): Boolean {

                return oldFilter.equals(newFilter)
            }
        }


}
