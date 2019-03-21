package com.iko.android.courier.ui.main

import com.iko.android.courier.data.Event
import com.iko.android.courier.data.ProfileEvent
import com.iko.android.courier.data.database.entity.Person
import com.iko.android.courier.data.prefs.AppPreferences
import com.iko.android.courier.domain.Callback
import com.iko.android.courier.domain.usecase.GetCurrentUser
import com.iko.android.courier.domain.usecase.UseCase
import com.iko.android.modularapp.base.CoreViewModel
import javax.inject.Inject

class MainVM @Inject constructor(
    private val getCurrentUser: GetCurrentUser,
    private val prefs: AppPreferences
) : CoreViewModel<Event>() {

    var currentUser: Person? = prefs.currentUser

    init {
        if (currentUser == null) {
            showLoading()
            getCurrentUser.run(
                UseCase.None(),
                Callback(::onCurrentUserFetched) {
                    hideLoading()
                    event.value = Event.Notification(it ?: "")
                }
            )
        }
    }

    private fun onCurrentUserFetched(user: Person) {
        hideLoading()
        this.currentUser = user
        prefs.currentUser = user
        event.value = ProfileEvent.ProfileFetched(user)
    }
}