package com.iko.android.courier.ui.cargo.create

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import com.iko.android.courier.CourierApp
import com.iko.android.courier.R
import com.iko.android.modularapp.base.CoreActivity
import kotlinx.android.synthetic.main.activity_create_cargo.*

class CreateCargoActivity : CoreActivity<CreateCargoVM>(CreateCargoVM::class.java, R.layout.activity_create_cargo) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        toolbar.initForActivity(this, getString(R.string.label_create_cargo))
    }

    override fun performDependencyInjection() {
        (applicationContext as CourierApp).appComponent.inject(this)
    }

    companion object {

        fun start(activity: Activity?) {
            val intent = Intent(activity, CreateCargoActivity::class.java)
            activity?.startActivity(intent)
        }

    }

}