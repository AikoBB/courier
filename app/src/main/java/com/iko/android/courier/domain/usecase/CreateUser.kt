package com.iko.android.courier.domain.usecase

import com.iko.android.courier.data.database.entity.Person
import com.iko.android.courier.domain.Callback
import com.iko.android.courier.domain.repository.ProfileRepository
import javax.inject.Inject

class CreateUser @Inject constructor(private val profileRepository: ProfileRepository): UseCase<CreateUser.Params, Callback<Person, String?>>(){

    override fun run(params: Params, callback: Callback<Person, String?>) {
        profileRepository.createUser(params.person, callback)
    }

    data class Params(val person: Person)
}