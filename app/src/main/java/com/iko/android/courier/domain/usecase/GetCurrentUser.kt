package com.iko.android.courier.domain.usecase

import com.iko.android.courier.data.database.entity.Person
import com.iko.android.courier.data.prefs.AppPreferences
import com.iko.android.courier.domain.Callback
import com.iko.android.courier.domain.repository.ProfileRepository
import javax.inject.Inject

class GetCurrentUser @Inject constructor(
    private val profileRepository: ProfileRepository,
    private val prefs: AppPreferences) : UseCase<UseCase.None, Callback<Person, String?>>() {

    override fun run(params: None, callback: Callback<Person, String?>) {
        profileRepository.getUserByEmail(prefs.email, callback)
    }
}