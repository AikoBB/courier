package com.iko.android.courier.ui.cargo.state

import com.iko.android.courier.CourierApp
import com.iko.android.courier.R
import com.iko.android.modularapp.base.CoreFragment

class CargoStateFragment: CoreFragment<CargoManagementVM>(CargoManagementVM::class.java, R.layout.fragment_cargo_state){

    override fun performDependencyInjection() {
        (context?.applicationContext as CourierApp).appComponent.inject(this)
    }
}