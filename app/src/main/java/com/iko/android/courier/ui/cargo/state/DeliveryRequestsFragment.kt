package com.iko.android.courier.ui.cargo.state

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import com.iko.android.courier.CourierApp
import com.iko.android.courier.R
import com.iko.android.courier.data.CargoEvent
import com.iko.android.courier.data.database.entity.Person
import com.iko.android.courier.ui.cargo.state.adapter.DeliveryRequestsAdapter
import com.iko.android.modularapp.base.CoreFragment
import kotlinx.android.synthetic.main.view_list.*

class DeliveryRequestsFragment : CoreFragment<CargoManagementVM>(CargoManagementVM::class.java, R.layout.view_list),
    DeliveryRequestsAdapter.Listener {

    private lateinit var adapter: DeliveryRequestsAdapter

    override fun performDependencyInjection() {
        (context?.applicationContext as CourierApp).appComponent.inject(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecycleView()
        subscribeLiveData()
    }

    private fun setupRecycleView() {
        adapter = DeliveryRequestsAdapter(context!!, this)
        list.run {
            layoutManager = LinearLayoutManager(context)
            itemAnimator = DefaultItemAnimator()
        }
        list.adapter = adapter
        adapter.addItems(viewModel.cargo?.requests ?: emptyList())
    }

    private fun subscribeLiveData() {
        viewModel.event.observe(this, Observer {
            when (it) {
                is CargoEvent.CargoInfoFetched -> adapter.addItems(it.cargo.requests)
            }
        })
    }

    override fun onPersonInfoClick(person: Person) {
        viewModel.requestProfile(person)
    }

    override fun onRequestAcceptClick(person: Person) {
        viewModel.acceptRequest(person)
    }
}