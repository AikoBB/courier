package com.iko.android.courier.ui.profile

import com.iko.android.courier.CourierApp
import com.iko.android.courier.R
import com.iko.android.modularapp.base.CoreFragment

class ProfileFragment: CoreFragment<ProfileVM>(ProfileVM::class.java, R.layout.fragment_profile){

    override fun performDependencyInjection() {
        (context!!.applicationContext as CourierApp).appComponent.inject(this)
    }

}