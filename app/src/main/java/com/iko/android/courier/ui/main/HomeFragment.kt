package com.iko.android.courier.ui.main

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import com.iko.android.core.extension.showMessage
import com.iko.android.courier.CourierApp
import com.iko.android.courier.data.Event
import com.iko.android.courier.data.ProfileEvent
import com.iko.android.courier.data.database.entity.Person
import com.iko.android.courier.ui.cargo.create.CreateCargoActivity
import com.iko.android.courier.ui.cargo.list.CargoListActivity
import com.iko.android.modularapp.base.CoreFragment
import com.iko.android.modularapp.extension.visible
import kotlinx.android.synthetic.main.fragment_home.*


class HomeFragment : CoreFragment<MainVM>(MainVM::class.java, com.iko.android.courier.R.layout.fragment_home) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        subscribeToLiveData()
        send_package.setOnClickListener { CreateCargoActivity.start(activity, viewModel.currentUser!!) }
        become_courier.setOnClickListener { CargoListActivity.start(activity) }
        viewModel.currentUser?.let { greetCurrentUser(it) }
    }

    private fun subscribeToLiveData() {
        viewModel.event.observe(this, Observer {
            when (it) {
                is ProfileEvent.ProfileFetched -> {
                    greetCurrentUser(it.person)
                }
                is Event.Notification -> context?.showMessage(it.message)
            }
        })
    }

    private fun greetCurrentUser(person: Person) {
        tv_greeting.text = getString(com.iko.android.courier.R.string.label_greetings, person.fullName)
        tv_greeting.visible()
    }

    override fun performDependencyInjection() {
        (context!!.applicationContext as CourierApp).appComponent.inject(this)
    }

}