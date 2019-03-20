package com.iko.android.courier.ui.cargo.list.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.iko.android.courier.R
import com.iko.android.modularapp.base.CoreViewHolder
import kotlinx.android.synthetic.main.item_empty_list.view.*

class EmptyListVH(itemView: View) : CoreViewHolder<String>(itemView) {

    override fun onBind(item: String) {
        itemView.subtitle.text = item
    }

    companion object {
        fun create(parent: ViewGroup): EmptyListVH {
            val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_empty_list, parent, false)
            return EmptyListVH(itemView)
        }
    }
}