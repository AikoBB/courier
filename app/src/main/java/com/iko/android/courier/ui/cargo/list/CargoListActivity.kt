package com.iko.android.courier.ui.cargo.list

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import com.iko.android.core.extension.showMessage
import com.iko.android.courier.CourierApp
import com.iko.android.courier.R
import com.iko.android.courier.data.CargoEvent
import com.iko.android.courier.data.Event
import com.iko.android.courier.data.database.entity.Cargo
import com.iko.android.courier.ui.cargo.list.adapter.CargoAdapter
import com.iko.android.modularapp.base.CoreActivity
import kotlinx.android.synthetic.main.activity_cargo_list.*

class CargoListActivity : CoreActivity<CargoListVM>(CargoListVM::class.java, R.layout.activity_cargo_list),
    CargoAdapter.Listener {

    private lateinit var adapter: CargoAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        toolbar.initForActivity(this, getString(R.string.label_cargoes))
        setupRecycleView()
        subscribeLiveData()
    }

    override fun performDependencyInjection() {
        (applicationContext as CourierApp).appComponent.inject(this)
    }

    private fun setupRecycleView() {
        adapter = CargoAdapter(this)
        cargoes.run {
            layoutManager = LinearLayoutManager(this@CargoListActivity)
            itemAnimator = DefaultItemAnimator()
        }
        cargoes.adapter = adapter
    }

    private fun subscribeLiveData() {
        viewModel.event.observe(this, Observer {
            when (it) {
                is CargoEvent.CargoListFetched -> adapter.addItems(it.cargoes)
                is Event.Notification -> showMessage(it.message)
            }
        })
    }

    override fun onCargoClick(cargo: Cargo) {

    }

    companion object {

        fun start(activity: Activity?) {
            val intent = Intent(activity, CargoListActivity::class.java)
            activity?.startActivity(intent)
        }

    }
}