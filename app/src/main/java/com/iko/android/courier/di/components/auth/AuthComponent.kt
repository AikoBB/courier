package com.iko.android.courier.di.components.auth

import com.iko.android.courier.ui.auth.login.LoginActivity

interface AuthComponent {
    fun inject(activity: LoginActivity)
}