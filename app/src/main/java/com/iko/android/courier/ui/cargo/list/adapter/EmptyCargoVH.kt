package com.iko.android.courier.ui.cargo.list.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.iko.android.courier.R
import com.iko.android.modularapp.base.CoreViewHolder

class EmptyCargoVH(itemView: View) : CoreViewHolder<String>(itemView) {

    override fun onBind(item: String) {}

    companion object {
        fun create(parent: ViewGroup): EmptyCargoVH {
            val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_empty_cargo, parent, false)
            return EmptyCargoVH(itemView)
        }
    }
}