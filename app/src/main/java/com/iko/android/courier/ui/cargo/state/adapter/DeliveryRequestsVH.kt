package com.iko.android.courier.ui.cargo.state.adapter

import android.graphics.Paint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.iko.android.courier.data.database.entity.Person
import com.iko.android.modularapp.base.CoreViewHolder
import kotlinx.android.synthetic.main.item_delivery_request.view.*


class DeliveryRequestsVH(itemView: View) : CoreViewHolder<Person>(itemView) {

    private lateinit var person: Person

    override fun onBind(item: Person) {
        person = item
        itemView.name.text = person.fullName
        itemView.name.paintFlags = itemView.name.paintFlags or Paint.UNDERLINE_TEXT_FLAG
    }

    companion object {
        fun create(parent: ViewGroup, listener: DeliveryRequestsAdapter.Listener): DeliveryRequestsVH {
            val itemView = LayoutInflater.from(parent.context)
                .inflate(com.iko.android.courier.R.layout.item_delivery_request, parent, false)
            val holder = DeliveryRequestsVH(itemView)
            holder.itemView.accept.setOnClickListener { listener.onRequestAcceptClick(holder.person) }
            holder.itemView.name.setOnClickListener { listener.onPersonInfoClick(holder.person) }
            return holder
        }
    }
}