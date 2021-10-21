package com.expertapps.base.libraries.filter.base

import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView

open class BaseViewHolder(val dataBinding: ViewDataBinding) :
    RecyclerView.ViewHolder(dataBinding.root) {

        fun <T : ViewDataBinding> getDataBindingView () = dataBinding as T

    }