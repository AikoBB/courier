package com.iko.android.modularapp.base

import android.view.View
import androidx.recyclerview.widget.RecyclerView

/**
 * Created by Aigerim on 7/31/2018.
 */

abstract class CoreViewHolder<in T>(itemView: View) : RecyclerView.ViewHolder(itemView) {
    abstract fun onBind(item: T)
}