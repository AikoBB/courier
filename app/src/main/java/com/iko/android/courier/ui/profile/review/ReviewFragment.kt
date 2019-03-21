package com.iko.android.courier.ui.profile.review

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import com.iko.android.core.extension.showMessage
import com.iko.android.courier.CourierApp
import com.iko.android.courier.R
import com.iko.android.courier.data.Event
import com.iko.android.courier.data.ProfileEvent
import com.iko.android.courier.ui.profile.ProfileVM
import com.iko.android.courier.ui.profile.review.adapter.ReviewAdapter
import com.iko.android.modularapp.base.CoreFragment
import kotlinx.android.synthetic.main.fragment_review.*

class ReviewFragment : CoreFragment<ProfileVM>(ProfileVM::class.java, R.layout.fragment_review) {

    private lateinit var adapter: ReviewAdapter
    override fun performDependencyInjection() {
        (activity.applicationContext as CourierApp).appComponent.inject(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecycleView()
        subscribeLiveData()
    }

    private fun setupRecycleView() {
        adapter = ReviewAdapter(context!!)
        reviews.run {
            layoutManager = LinearLayoutManager(context)
            itemAnimator = DefaultItemAnimator()
        }
        reviews.adapter = adapter
        adapter.addItems(viewModel.reviews)
    }

    private fun subscribeLiveData() {
        viewModel.event.observe(this, androidx.lifecycle.Observer {
            when (it) {
                is ProfileEvent.ReviewListFetched -> {
                    adapter.addItems(it.reviews)

                }
                is Event.Notification -> context?.showMessage(it.message)
            }
        })
    }

}