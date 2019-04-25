package com.iko.android.courier.ui.cargo.state

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.iko.android.core.extension.showMessage
import com.iko.android.courier.CourierApp
import com.iko.android.courier.R
import com.iko.android.courier.data.CargoEvent
import com.iko.android.courier.data.Event
import com.iko.android.courier.data.database.entity.Cargo
import com.iko.android.courier.data.database.entity.Person
import com.iko.android.courier.ui.profile.ProfileFragment
import com.iko.android.modularapp.base.CoreActivity
import com.iko.android.modularapp.extension.gone
import com.iko.android.modularapp.extension.visible
import kotlinx.android.synthetic.main.activity_cargo_management.*

class CargoManagementActivity :
    CoreActivity<CargoManagementVM>(CargoManagementVM::class.java, R.layout.activity_cargo_management) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.fetchCargoById(intent.getStringExtra(String::class.java.canonicalName))
        toolbar.initForActivity(this)
        subscribeLiveData()
    }

    override fun performDependencyInjection() {
        (applicationContext as CourierApp).appComponent.inject(this)
    }

    private fun subscribeLiveData() {
        viewModel.event.observe(this, Observer {
            when (it) {
                is CargoEvent.CargoInfoFetched -> setupView(it.cargo)
                is CargoEvent.CargoRequestAccepted -> showCargoState()
                is CargoEvent.ProfileRequested -> showProfile(it.person)
                is Event.Notification -> showMessage(it.message)
            }
        })
    }

    private fun setupView(cargo: Cargo) {
        when {
            cargo.assignee != null -> showCargoState()
            else -> showDeliveryRequests()
        }
    }

    private fun showCargoState() {
        showFragment(
            CargoStateFragment(),
            CargoStateFragment::class.java.canonicalName,
            getString(R.string.label_delivery_requests)
        )
    }

    private fun showDeliveryRequests() {
        showFragment(
            DeliveryRequestsFragment(),
            DeliveryRequestsFragment::class.java.canonicalName,
            getString(R.string.label_cargo_delivery_state)
        )
    }

    private fun showProfile(person: Person) {
        toolbar.gone()
        showFragment(
            ProfileFragment(),
            ProfileFragment::class.java.canonicalName,
            getString(R.string.label_cargo_delivery_state)
        )
    }

    private fun showFragment(fragment: Fragment?, tag: String, toolbarTitle: String?) {
        toolbar.visible()
        supportActionBar?.title = toolbarTitle
        val replaced = supportFragmentManager
            .beginTransaction()
            .disallowAddToBackStack()
            .replace(R.id.container, fragment!!, tag)
        try {
            replaced.commit()
        } catch (e: IllegalStateException) {
            replaced.commitAllowingStateLoss()
        }
    }

    override fun onBackPressed() {
        val fragment = supportFragmentManager.findFragmentByTag(ProfileFragment::class.java.canonicalName)
        if (fragment != null && fragment.isVisible) {
            setupView(viewModel.cargo!!)
        } else super.onBackPressed()
    }

    companion object {

        fun start(activity: Activity?, cargoId: String) {
            activity?.run {
                val intent = Intent(activity, CargoManagementActivity::class.java)
                intent.putExtra(String::class.java.canonicalName, cargoId)
                startActivity(intent)
            }

        }
    }
}