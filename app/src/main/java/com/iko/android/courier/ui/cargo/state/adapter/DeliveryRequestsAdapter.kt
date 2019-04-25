package com.iko.android.courier.ui.cargo.state.adapter

import android.content.Context
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.iko.android.courier.R
import com.iko.android.courier.data.database.entity.Person
import com.iko.android.courier.ui.cargo.list.adapter.EmptyListVH
import com.iko.android.modularapp.base.CoreViewHolder

class DeliveryRequestsAdapter constructor(
    private val context: Context,
    private val listener: Listener
) : RecyclerView.Adapter<CoreViewHolder<*>>() {

    private var items = mutableListOf<Person>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CoreViewHolder<*> {
        return when (viewType) {
            ViewType.PERSON -> DeliveryRequestsVH.create(parent, listener)
            else -> EmptyListVH.create(parent)
        }
    }

    override fun getItemViewType(position: Int): Int {
        return when {
            items.isEmpty() -> ViewType.EMPTY_LIST
            else -> ViewType.PERSON
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
            is EmptyListVH -> holder.onBind(context.getString(R.string.label_no_request))
            is DeliveryRequestsVH -> holder.onBind(items[position])
        }
    }

    fun addItems(requests: List<Person>) {
        items.clear()
        items.addAll(requests)
        notifyDataSetChanged()
    }

    interface Listener {
        fun onPersonInfoClick(person: Person)
        fun onRequestAcceptClick(person: Person)
    }

    object ViewType {
        const val PERSON = 1
        const val EMPTY_LIST = 2
    }
}