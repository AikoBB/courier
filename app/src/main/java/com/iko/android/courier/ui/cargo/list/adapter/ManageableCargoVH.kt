package com.iko.android.courier.ui.cargo.list.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.iko.android.courier.R
import com.iko.android.courier.data.database.entity.Cargo
import com.iko.android.courier.utils.getFormattedForm
import com.iko.android.modularapp.base.CoreViewHolder
import com.iko.android.modularapp.extension.invisible
import kotlinx.android.synthetic.main.item_own_cargo.view.*

class ManageableCargoVH(itemView: View, private val listener: CargoAdapter.Listener) : CoreViewHolder<Cargo>(itemView) {

    private lateinit var cargo: Cargo
    override fun onBind(item: Cargo) {
        cargo = item

        item.deliveryTime?.let {
            val date = it.getFormattedForm()
            val time = it.getFormattedForm("HH:mm")
            itemView.delivery_date.text = "$date \n $time"
        }

        item.addressFrom?.name?.let {
            itemView.pick_up_address.text = it
        }

        item.addressTo?.name?.let {
            itemView.deliver_address.text = it
        }

        item.deliveryType?.toString().run {
            itemView.delivery_type.text = this
        }

        item.weight?.toString().run {
            itemView.weight.text = this
        }

        item.price?.let {
            val price = "$it"
            itemView.title_price.text = price
        }

        item.requests?.size?.toString().run {
            itemView.request.text = this
        }

        when (item.assignee == null) {
            true -> {
                itemView.btn_delete.setOnClickListener { listener.onCargoDeleteClick(item) }
                itemView.btn_action.text = itemView.context.getString(R.string.label_requests)
            }
            else -> {
                itemView.btn_delete.invisible()
                itemView.btn_action.text = itemView.context.getString(R.string.label_status)
            }
        }

        itemView.btn_action.setOnClickListener { listener.onShowStatusClick(item) }

    }

    companion object {

        fun create(parent: ViewGroup, listener: CargoAdapter.Listener): ManageableCargoVH {
            val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_own_cargo, parent, false)
            return ManageableCargoVH(itemView, listener)
        }

    }
}