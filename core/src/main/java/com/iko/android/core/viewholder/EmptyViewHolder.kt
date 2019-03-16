package com.iko.android.core.viewholder

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.iko.android.core.R
import com.iko.android.modularapp.base.CoreViewHolder
import kotlinx.android.synthetic.main.item_empty.view.*

class EmptyViewHolder(itemView: View) : CoreViewHolder<String>(itemView) {

    override fun onBind(item: String) {
        itemView.tv_message.text = item
    }

    companion object {

        fun create(parent: ViewGroup): EmptyViewHolder {
            return EmptyViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_empty, parent, false))
        }
    }
}