package com.iko.android.courier.domain.usecase

import com.iko.android.courier.data.database.entity.Person
import com.iko.android.courier.domain.Callback
import com.iko.android.courier.domain.repository.AuthRepository
import javax.inject.Inject

class LoginUseCase @Inject constructor(private val authRepository: AuthRepository): UseCase<LoginUseCase.Params, Callback<Person, String?>>(){

    override fun run(params: Params, callback: Callback<Person, String?>) {
        authRepository.login(params.email, params.email, callback)
    }

    data class Params(val email: String, val password: String)
}

class IsLoggedInUseCase @Inject constructor(private val authRepository: AuthRepository): UseCase<UseCase.None, Callback<UseCase.None?, UseCase.None?>>(){

    override fun run(params: None, callback: Callback<UseCase.None?, UseCase.None?>) {
        when(authRepository.isUserLoggedIn()){
            true -> callback.onSuccess(None())
            false -> callback.onFailed(None())
        }
    }

}