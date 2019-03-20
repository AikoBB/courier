package com.iko.android.courier.ui.profile.review

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import com.iko.android.courier.CourierApp
import com.iko.android.courier.R
import com.iko.android.courier.data.database.entity.Review
import com.iko.android.courier.ui.profile.review.adapter.ReviewAdapter
import com.iko.android.modularapp.base.CoreFragment
import kotlinx.android.synthetic.main.fragment_review.*
import java.util.*

class ReviewFragment : CoreFragment<ReviewVM>(ReviewVM::class.java, R.layout.fragment_review) {

    private lateinit var adapter: ReviewAdapter
    override fun performDependencyInjection() {
        (activity.applicationContext as CourierApp).appComponent.inject(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecycleView()
    }

    private fun setupRecycleView() {
        adapter = ReviewAdapter(context!!)
        reviews.run {
            layoutManager = LinearLayoutManager(context)
            itemAnimator = DefaultItemAnimator()
        }
        reviews.adapter = adapter
        adapter.addItems(listOf(Review(reviewerName = "Amy Dan", date = Date(), review = "Best deliverman!"), Review(reviewerName = "Will Smith", date = Date(), review = "Worst deliverman!")))
    }

}