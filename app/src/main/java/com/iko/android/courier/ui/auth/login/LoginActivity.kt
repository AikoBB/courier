package com.iko.android.courier.ui.auth.login

import android.app.Activity
import android.content.Intent
import com.iko.android.courier.CourierApp
import com.iko.android.courier.R
import com.iko.android.modularapp.base.CoreActivity

class LoginActivity : CoreActivity<LoginVM>(LoginVM::class.java, R.layout.activity_login) {

    override fun performDependencyInjection() {
        (applicationContext as CourierApp).appComponent.inject(this)
    }

    companion object {
        fun start(activity: Activity?) {
            activity?.startActivity(Intent(activity, LoginActivity::class.java))
        }
    }

}