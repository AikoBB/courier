package com.iko.android.courier.ui.splash

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.iko.android.courier.CourierApp
import com.iko.android.courier.data.AuthEvent
import com.iko.android.courier.ui.auth.login.LoginActivity
import com.iko.android.courier.ui.main.MainActivity
import javax.inject.Inject

class SplashActivity: AppCompatActivity(){

    @Inject
    lateinit var factory: ViewModelProvider.Factory
    private lateinit var viewModel: SplashVM

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (applicationContext as CourierApp).appComponent.inject(this)
        viewModel = ViewModelProviders.of(this, factory).get(SplashVM::class.java)
        viewModel.decideNextActivity()
        subscribeLiveData()
    }

    private fun subscribeLiveData(){
        viewModel.event.observe(this, Observer {
            when(it){
                is AuthEvent.LoggedIn -> MainActivity.start(this)
                else -> LoginActivity.start(this)
            }
        })
    }

    companion object {
        fun start(context: Context?) {
            val intent = Intent(context, SplashActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or
                    Intent.FLAG_ACTIVITY_CLEAR_TASK or
                    Intent.FLAG_ACTIVITY_NEW_TASK
            context?.startActivity(intent)
        }
    }
}