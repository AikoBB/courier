package com.iko.android.courier.ui.cargo.list.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.iko.android.courier.data.database.entity.Cargo
import com.iko.android.modularapp.base.CoreViewHolder

class CargoAdapter constructor(
    private val listener: Listener
) : RecyclerView.Adapter<CoreViewHolder<*>>() {

    private var items = mutableListOf<Pair<Int, Cargo>>()
    private var emptyLabel = ""

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CoreViewHolder<*> {
        return when (viewType) {
            ViewType.CARGO -> CargoVH.create(parent, listener)
            ViewType.MANAGEABLE_CARGO -> ManageableCargoVH.create(parent, listener)
            else -> EmptyListVH.create(parent)
        }
    }

    override fun getItemViewType(position: Int): Int {
        return when {
            items.isEmpty() -> ViewType.EMPTY_LIST
            else -> items[position].first
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
            is EmptyListVH -> holder.onBind(emptyLabel)
            is CargoVH -> holder.onBind(items[position].second)
            is ManageableCargoVH -> holder.onBind(items[position].second)
        }
    }

    fun addItems(cargoes: List<Cargo>, emptyLabel: String) {
        this.emptyLabel = emptyLabel
        items.clear()
        cargoes.forEach { items.add(Pair(ViewType.CARGO, it)) }
        notifyDataSetChanged()
    }

    fun addManageableCargoes(cargoes: List<Cargo>, emptyLabel: String) {
        this.emptyLabel = emptyLabel
        items.clear()
        cargoes.forEach { items.add(Pair(ViewType.MANAGEABLE_CARGO, it)) }
        notifyDataSetChanged()
    }

    interface Listener {
        fun onCargoDeleteClick(cargo: Cargo)
        fun onCargoRequestClick(cargo: Cargo)
        fun onShowStatusClick(cargo: Cargo)
    }

    object ViewType {
        const val CARGO = 1
        const val MANAGEABLE_CARGO = 3
        const val EMPTY_LIST = 2
    }
}