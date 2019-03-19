package com.iko.android.courier.domain.repository

import com.google.firebase.auth.FirebaseAuth
import com.iko.android.courier.data.database.entity.Person
import com.iko.android.courier.data.prefs.AppPreferences
import com.iko.android.courier.domain.Callback
import javax.inject.Inject

interface AuthRepository {

    fun login(email: String, password: String, callback: Callback<Person, String?>)
    fun isUserLoggedIn(): Boolean

    class Network @Inject constructor(
        private val auth: FirebaseAuth,
        private val prefs: AppPreferences) : AuthRepository {

        override fun login(email: String, password: String, callback: Callback<Person, String?>) {
            auth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener { task ->
                    when (task.isSuccessful) {
                        true -> {
                            prefs.email = email
                            prefs.password = password
                            callback.onSuccess(Person(email = email))
                        }
                        false -> callback.onFailed(task.exception?.message)
                    }
                }
        }

        override fun isUserLoggedIn() = prefs.email.isNotEmpty() && prefs.password.isNotEmpty()

    }

}