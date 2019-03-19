package com.iko.android.courier.ui.splash

import com.iko.android.courier.data.AuthEvent
import com.iko.android.courier.data.Event
import com.iko.android.courier.domain.Callback
import com.iko.android.courier.domain.usecase.IsLoggedInUseCase
import com.iko.android.courier.domain.usecase.UseCase
import com.iko.android.modularapp.base.CoreViewModel
import javax.inject.Inject

class SplashVM @Inject constructor(private val isLoggedInUseCase: IsLoggedInUseCase) : CoreViewModel<Event>() {

    fun decideNextActivity() {
        isLoggedInUseCase.run(
            UseCase.None(),
            Callback({ event.value = AuthEvent.LoggedIn() },
                    { event.value = AuthEvent.NeedAuthorization() })
        )
    }
}