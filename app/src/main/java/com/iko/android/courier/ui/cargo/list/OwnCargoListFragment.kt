package com.iko.android.courier.ui.cargo.list

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import com.iko.android.core.extension.showMessage
import com.iko.android.core.extension.showWarningDialog
import com.iko.android.courier.CourierApp
import com.iko.android.courier.R
import com.iko.android.courier.data.CargoEvent
import com.iko.android.courier.data.Event
import com.iko.android.courier.data.database.entity.Cargo
import com.iko.android.courier.ui.cargo.list.adapter.CargoAdapter
import com.iko.android.courier.ui.cargo.state.CargoManagementActivity
import com.iko.android.modularapp.base.CoreFragment
import kotlinx.android.synthetic.main.activity_cargo_list.*

class OwnCargoListFragment : CoreFragment<CargoListVM>(CargoListVM::class.java, R.layout.activity_cargo_list),
    CargoAdapter.Listener {

    private lateinit var adapter: CargoAdapter

    override fun performDependencyInjection() {
        (context!!.applicationContext as CourierApp).appComponent.inject(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        toolbar.initForActivity(activity, getString(R.string.label_own_cargo))
        activity?.supportActionBar?.setDisplayHomeAsUpEnabled(false)
        setupRecycleView()
        subscribeLiveData()
    }

    private fun setupRecycleView() {
        adapter = CargoAdapter(this)
        cargoes.run {
            layoutManager = LinearLayoutManager(context)
            itemAnimator = DefaultItemAnimator()
        }
        cargoes.adapter = adapter
    }

    private fun subscribeLiveData() {
        viewModel.event.observe(this, Observer {
            when (it) {
                is CargoEvent.CargoListFetched -> adapter.addManageableCargoes(
                    it.cargoes.own,
                    getString(R.string.label_no_own_cargoes)
                )
                is CargoEvent.CargoDeleted -> context?.showWarningDialog(
                    getString(
                        R.string.warning_cargo_deleted,
                        it.cargoId
                    )
                )
                is Event.Notification -> context?.showMessage(it.message)
            }
        })
    }

    override fun onCargoDeleteClick(cargo: Cargo) {
        viewModel.deleteCargo(cargo)
    }

    override fun onCargoRequestClick(cargo: Cargo) {
        CargoManagementActivity.start(activity, cargo.id!!)
    }

    override fun onShowStatusClick(cargo: Cargo) {
        CargoManagementActivity.start(activity, cargo.id!!)
    }

}