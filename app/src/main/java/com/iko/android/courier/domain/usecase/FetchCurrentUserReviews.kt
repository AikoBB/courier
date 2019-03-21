package com.iko.android.courier.domain.usecase

import com.iko.android.courier.data.database.entity.Review
import com.iko.android.courier.data.prefs.AppPreferences
import com.iko.android.courier.domain.Callback
import com.iko.android.courier.domain.repository.ProfileRepository
import javax.inject.Inject

class FetchCurrentUserReviews @Inject constructor(
    private val profileRepository: ProfileRepository,
    private val prefs: AppPreferences
) : UseCase<UseCase.None, Callback<List<Review>, String?>>() {

    override fun run(params: None, callback: Callback<List<Review>, String?>) {
        profileRepository.getReviewsByEmail(prefs.email, callback)
    }

}