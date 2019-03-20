package com.iko.android.courier.ui.profile.review.adapter

import android.content.Context
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.iko.android.courier.R
import com.iko.android.courier.data.database.entity.Review
import com.iko.android.courier.ui.cargo.list.adapter.EmptyListVH
import com.iko.android.modularapp.base.CoreViewHolder

class ReviewAdapter(private val context: Context) : RecyclerView.Adapter<CoreViewHolder<*>>() {

    private var items = mutableListOf<Review>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CoreViewHolder<*> {
        return when (viewType) {
            ViewType.REVIEW -> ReviewVH.create(parent)
            else -> EmptyListVH.create(parent)
        }
    }

    override fun getItemViewType(position: Int): Int {
        return when {
            items.isEmpty() -> ViewType.EMPTY_LIST
            else -> ViewType.REVIEW
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
            is EmptyListVH -> holder.onBind(context.getString(R.string.label_no_reviews))
            is ReviewVH -> holder.onBind(items[position])
        }
    }

    fun addItems(reviews: List<Review>) {
        items.clear()
        items.addAll(reviews)
        notifyDataSetChanged()
    }

    object ViewType {
        const val REVIEW = 1
        const val EMPTY_LIST = 2
    }
}