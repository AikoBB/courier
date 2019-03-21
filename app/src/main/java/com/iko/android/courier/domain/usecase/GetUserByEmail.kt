package com.iko.android.courier.domain.usecase

import com.iko.android.courier.data.database.entity.Person
import com.iko.android.courier.domain.Callback
import com.iko.android.courier.domain.repository.ProfileRepository
import javax.inject.Inject

class GetUserByEmail @Inject constructor(private val profileRepository: ProfileRepository) :
    UseCase<GetUserByEmail.Params, Callback<Person, String?>>() {

    override fun run(params: Params, callback: Callback<Person, String?>) {
        profileRepository.getUserByEmail(params.email, callback)
    }

    data class Params(val email: String)
}