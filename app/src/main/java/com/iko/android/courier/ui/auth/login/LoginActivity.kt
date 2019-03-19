package com.iko.android.courier.ui.auth.login

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.text.InputType
import androidx.lifecycle.Observer
import com.iko.android.core.extension.showWarningDialog
import com.iko.android.courier.CourierApp
import com.iko.android.courier.data.AuthEvent
import com.iko.android.courier.data.Event
import com.iko.android.courier.ui.main.MainActivity
import com.iko.android.modularapp.base.CoreActivity
import kotlinx.android.synthetic.main.activity_login.*


class LoginActivity : CoreActivity<LoginVM>(LoginVM::class.java, com.iko.android.courier.R.layout.activity_login) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        subscribeToLiveData()
        setupViews()
    }

    private fun subscribeToLiveData() {
        viewModel.event.observe(this, Observer {
            when (it) {
                is AuthEvent.LoginSucceeded -> MainActivity.start(this)
                is AuthEvent.LoginFailed -> showWarningDialog(it.description ?: "")
                is Event.Notification -> showWarningDialog(it.message)
            }
        })
    }

    private fun setupViews() {
        input_password.setInputText(InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD)
        btn_login.setOnClickListener { viewModel.login(input_email.getText(), input_password.getText()) }
    }

    override fun performDependencyInjection() {
        (applicationContext as CourierApp).appComponent.inject(this)
    }

    companion object {
        fun start(activity: Activity?) {
            activity?.startActivity(Intent(activity, LoginActivity::class.java))
        }
    }

}