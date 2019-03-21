package com.iko.android.courier.domain.usecase

import com.iko.android.courier.data.database.entity.Review
import com.iko.android.courier.domain.Callback
import com.iko.android.courier.domain.repository.ProfileRepository
import javax.inject.Inject

class CreateReview @Inject constructor(private val profileRepository: ProfileRepository) :
    UseCase<CreateReview.Params, Callback<Review, String?>>() {

    override fun run(params: Params, callback: Callback<Review, String?>) {
        profileRepository.createReview(params.review, callback)
    }

    data class Params(val review: Review)
}