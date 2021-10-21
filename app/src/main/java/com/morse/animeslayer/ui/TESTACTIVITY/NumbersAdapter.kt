package com.morse.animeslayer.ui.TESTACTIVITY

import android.view.LayoutInflater
import android.view.ViewGroup
import com.expertapps.base.libraries.filter.base.BaseAdapter
import com.expertapps.base.libraries.filter.base.BaseViewHolder
import com.morse.animeslayer.databinding.NumberLayoutTestBinding

class NumbersAdapter : BaseAdapter<Int>() {

    override fun bind(viewHolder: BaseViewHolder, position: Int) {
        viewHolder.getDataBindingView<NumberLayoutTestBinding>()
            .numberTv.text = " \uD83D\uDE80 : The Value is : ${data[position] }"
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        return BaseViewHolder(
            NumberLayoutTestBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }
}