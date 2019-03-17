package com.iko.android.courier.ui.main.home

import android.os.Bundle
import android.view.View
import com.iko.android.courier.CourierApp
import com.iko.android.courier.R
import com.iko.android.courier.ui.cargo.create.CreateCargoActivity
import com.iko.android.courier.ui.cargo.list.CargoListActivity
import com.iko.android.modularapp.base.CoreFragment
import kotlinx.android.synthetic.main.fragment_home.*

class HomeFragment: CoreFragment<HomeVM>(HomeVM::class.java, R.layout.fragment_home){

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        send_package.setOnClickListener { CreateCargoActivity.start(activity) }
        become_courier.setOnClickListener { CargoListActivity.start(activity) }
    }
    override fun performDependencyInjection() {
        (context!!.applicationContext as CourierApp).appComponent.inject(this)
    }
}