package com.iko.android.courier.ui.auth.login

import com.iko.android.courier.data.AuthEvent
import com.iko.android.courier.data.Event
import com.iko.android.courier.data.database.entity.Person
import com.iko.android.courier.domain.Callback
import com.iko.android.courier.domain.usecase.LoginUseCase
import com.iko.android.modularapp.base.CoreViewModel
import javax.inject.Inject

class LoginVM @Inject constructor(private val loginUseCase: LoginUseCase): CoreViewModel<Event>(){

    fun login(email: String, password: String){
        loginUseCase.run(
            LoginUseCase.Params(email, password),
            Callback(::onLoginSucceed, ::onLoginFailed)
        )
    }

    private fun onLoginSucceed(person: Person){
        event.value = AuthEvent.LoginSucceeded(person)
    }

    private fun onLoginFailed(message: String?){
        event.value = AuthEvent.LoginFailed(message)
    }

}