package com.iko.android.courier.data.prefs

import android.content.SharedPreferences
import androidx.core.content.edit
import com.google.gson.Gson
import com.iko.android.courier.data.database.entity.Person

class AppPreferences(private val prefs: SharedPreferences) {

    private val EMAIL = "email"
    var email: String
        get() {
            return prefs.getString(EMAIL, "")
        }
        set(value) {
            prefs.edit {
                putString(EMAIL, value)
            }
        }


    private val PASSWORD = "password"
    var password: String
        get() {
            return prefs.getString(PASSWORD, "")
        }
        set(value) {
            prefs.edit {
                putString(PASSWORD, value)
            }
        }

    private val CURRENT_USER = "CURRENT_USER"
    var currentUser: Person?
        get() {
            val json = prefs.getString(CURRENT_USER, "")
            return when (json.isEmpty()) {
                true -> null
                else -> Gson().fromJson(json, Person::class.java)
            }
        }
        set(value) {
            prefs.edit {
                putString(CURRENT_USER, Gson().toJson(value))
            }
        }

    fun reset() {
        email = ""
        password = ""
        currentUser = null
    }

}