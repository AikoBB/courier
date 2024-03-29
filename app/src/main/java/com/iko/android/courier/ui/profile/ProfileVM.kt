package com.iko.android.courier.ui.profile

import com.iko.android.courier.data.Event
import com.iko.android.courier.data.ProfileEvent
import com.iko.android.courier.data.database.entity.Person
import com.iko.android.courier.data.database.entity.Review
import com.iko.android.courier.data.prefs.AppPreferences
import com.iko.android.courier.domain.Callback
import com.iko.android.courier.domain.usecase.FetchCurrentUserReviews
import com.iko.android.courier.domain.usecase.UseCase
import com.iko.android.modularapp.base.CoreViewModel
import javax.inject.Inject

class ProfileVM @Inject constructor(
    private val fetchCurrentUserReviews: FetchCurrentUserReviews,
    private val prefs: AppPreferences
) :
    CoreViewModel<Event>() {

    var currentUser: Person? = prefs.currentUser
    var reviews = mutableListOf<Review>()
    var rating = 0.0f
    var requestSent = false

    init {
        if (!requestSent) {
            showLoading()
            requestSent = true
            fetchCurrentUserReviews.run(
                UseCase.None(),
                Callback(::onReviewsFetched) { event.value = Event.Notification(it ?: "") })
        }
    }

    private fun onReviewsFetched(reviews: List<Review>) {
        hideLoading()
        this.reviews = reviews.toMutableList()
        var ratingSum = 0f
        val ratingCount = reviews.size
        reviews.forEach { ratingSum += it.rate }
        rating = ratingSum / ratingCount
        event.value = ProfileEvent.ReviewListFetched(reviews, rating)
    }

    fun signOut(){
        prefs.reset()
    }
}