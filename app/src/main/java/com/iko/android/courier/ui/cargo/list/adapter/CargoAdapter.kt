package com.iko.android.courier.ui.cargo.list.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.iko.android.courier.data.database.entity.Cargo
import com.iko.android.modularapp.base.CoreViewHolder

class CargoAdapter constructor(
    private val listener: Listener
) : RecyclerView.Adapter<CoreViewHolder<*>>() {

    private var items = mutableListOf<Cargo>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CoreViewHolder<*> {
        return when (viewType) {
            ViewType.CARGO -> CargoVH.create(parent, listener)
            else -> EmptyCargoVH.create(parent)
        }
    }

    override fun getItemViewType(position: Int): Int {
        return when {
            items.isEmpty() -> ViewType.EMPTY_LIST
            else -> ViewType.CARGO
        }
    }

    override fun getItemCount(): Int {
        return when {
            items.isEmpty() -> 1
            else -> items.count()
        }
    }

    override fun onBindViewHolder(holder: CoreViewHolder<*>, position: Int) {
        when (holder) {
            is EmptyCargoVH -> holder.onBind("")
            is CargoVH -> holder.onBind(items[position])
        }
    }

    fun addItems(cargoes: List<Cargo>) {
        items.clear()
        items.addAll(cargoes)
        notifyDataSetChanged()
    }

    interface Listener {
        fun onCargoClick(cargo: Cargo)
    }

    object ViewType {
        const val CARGO = 1
        const val EMPTY_LIST = 2
    }
}