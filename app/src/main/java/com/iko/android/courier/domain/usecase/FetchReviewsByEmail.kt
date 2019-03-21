package com.iko.android.courier.domain.usecase

import com.iko.android.courier.data.database.entity.Review
import com.iko.android.courier.domain.Callback
import com.iko.android.courier.domain.repository.ProfileRepository
import javax.inject.Inject

class FetchReviewsByEmail @Inject constructor(private val profileRepository: ProfileRepository) :
    UseCase<FetchReviewsByEmail.Params, Callback<List<Review>, String?>>() {

    override fun run(params: Params, callback: Callback<List<Review>, String?>) {
        profileRepository.getReviewsByEmail(params.email, callback)
    }

    data class Params(val email: String)

}