package com.iko.android.courier.ui.main.home

import com.iko.android.courier.CourierApp
import com.iko.android.courier.R
import com.iko.android.modularapp.base.CoreFragment

class HomeFragment: CoreFragment<HomeVM>(HomeVM::class.java, R.layout.fragment_home){

    override fun performDependencyInjection() {
        (context!!.applicationContext as CourierApp).appComponent.inject(this)
    }
}