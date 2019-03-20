package com.iko.android.courier.ui.profile.review.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.iko.android.courier.R
import com.iko.android.courier.data.database.entity.Review
import com.iko.android.courier.utils.getFormattedForm
import com.iko.android.modularapp.base.CoreViewHolder
import kotlinx.android.synthetic.main.item_review.view.*

class ReviewVH(itemView: View) : CoreViewHolder<Review>(itemView) {

    override fun onBind(item: Review) {
        item.reviewerAvatar?.let { itemView.avatar.setImageURI(it) }
        item.reviewerName?.let { itemView.name.text = it }
        item.date?.let { itemView.date.text = it.getFormattedForm() }
        item.review?.let { itemView.review.text = it }
    }

    companion object {
        fun create(parent: ViewGroup): ReviewVH {
            val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_review, parent, false)
            return ReviewVH(itemView)
        }
    }
}