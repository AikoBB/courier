package com.iko.android.courier.ui.auth.login

import android.content.res.Resources
import com.iko.android.courier.R
import com.iko.android.courier.data.AuthEvent
import com.iko.android.courier.data.Event
import com.iko.android.courier.data.database.entity.Person
import com.iko.android.courier.domain.Callback
import com.iko.android.courier.domain.usecase.LoginUseCase
import com.iko.android.courier.utils.isValidEmail
import com.iko.android.modularapp.base.CoreViewModel
import javax.inject.Inject

class LoginVM @Inject constructor(
    private val loginUseCase: LoginUseCase,
    private val resources: Resources
) : CoreViewModel<Event>() {

    fun login(email: String, password: String) {
        if (isEmailValid(email) && isPasswordValid(password)){
            showLoading()
            loginUseCase.run(
                LoginUseCase.Params(email, password),
                Callback(::onLoginSucceed, ::onLoginFailed)
            )
        }

    }

    private fun isEmailValid(email: String): Boolean {
        val label = resources.getString(R.string.label_email)
        return when {
            !email.isValidEmail -> {
                event.value = Event.Notification(resources.getString(R.string.warning_empty_incorrect, label))
                false
            }
            else -> true
        }
    }

    private fun isPasswordValid(password: String): Boolean {
        val label = resources.getString(R.string.label_password)
        return when {
            password.isEmpty() -> {
                event.value = Event.Notification(resources.getString(R.string.warning_can_not_be_empty, label))
                false
            }
            else -> true
        }
    }

    private fun onLoginSucceed(person: Person) {
        hideLoading()
        event.value = AuthEvent.LoginSucceeded(person)
    }

    private fun onLoginFailed(message: String?) {
        hideLoading()
        event.value = AuthEvent.LoginFailed(message ?: resources.getString(R.string.warning_wrong_creds))
    }

}