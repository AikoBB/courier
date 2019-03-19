package com.iko.android.courier.data.prefs

import android.content.SharedPreferences
import androidx.core.content.edit

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

    fun reset(){
        email = ""
        password = ""
    }

}