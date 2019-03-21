package com.iko.android.courier.ui.cargo.list.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.iko.android.courier.R
import com.iko.android.courier.data.database.entity.Cargo
import com.iko.android.courier.utils.getFormattedForm
import com.iko.android.modularapp.base.CoreViewHolder
import kotlinx.android.synthetic.main.item_cargo.view.*
import kotlinx.android.synthetic.main.item_cargo_detail.view.*
import kotlinx.android.synthetic.main.item_cargo_title.view.*
import kotlinx.android.synthetic.main.item_person_info.view.*

class CargoVH(itemView: View) : CoreViewHolder<Cargo>(itemView) {

    private lateinit var cargo: Cargo
    override fun onBind(item: Cargo) {
        cargo = item
        item.price?.let {
            val price = "$it"
            itemView.short_info.title_price.text = price
            itemView.detail.price.text = price
        }

        item.deliveryTime?.let {
            val date = it.getFormattedForm()
            val time = it.getFormattedForm("HH:mm")
            itemView.short_info.title_time_label.text = time
            itemView.short_info.title_date_label.text = date
        }

        item.addressFrom?.name?.let {
            itemView.short_info.title_from_address.text = it
            itemView.detail.address_from.text = it
        }

        item.addressTo?.name?.let {
            itemView.short_info.title_to_address.text = it
            itemView.detail.address_to.text = it
        }

        item.deliveryType?.toString().run {
            itemView.short_info.title_delivery_type.text = this
            itemView.detail.tv_delivery_type.text = this
        }

        item.weight?.toString().run {
            itemView.short_info.weight.text = this
            itemView.detail.tv_weight.text = this
        }

        item.weight?.toString().run {
            itemView.short_info.request.text = "12"
        }

        item.sender?.run {
            itemView.detail.sender.full_name.text = fullName
            itemView.detail.sender.phone_number.text = phoneNumber
            itemView.detail.sender.email.text = email

        }

        item.receiver?.run {
            itemView.detail.receiver.full_name.text = fullName
            itemView.detail.receiver.phone_number.text = phoneNumber
            itemView.detail.receiver.email.text = email
        }
        itemView.context.run {
            itemView.detail.receiver.title.text = getString(R.string.label_receiver_info)
            itemView.detail.receiver.full_name.setCompoundDrawablesWithIntrinsicBounds(
                R.drawable.ic_receiver_black_24dp,
                0,
                0,
                0
            )
        }

        itemView.folding_cell.setOnClickListener { itemView.folding_cell.toggle(false) }

    }

    companion object {
        fun create(parent: ViewGroup, listener: CargoAdapter.Listener): CargoVH {
            val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_cargo, parent, false)
            val holder = CargoVH(itemView)
            //holder.itemView.setOnClickListener { listener.onCargoClick(holder.cargo) }
            return holder
        }
    }
}