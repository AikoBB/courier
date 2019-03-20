package com.iko.android.courier.ui.cargo.list

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import com.iko.android.courier.CourierApp
import com.iko.android.courier.R
import com.iko.android.courier.data.database.entity.*
import com.iko.android.courier.ui.cargo.list.adapter.CargoAdapter
import com.iko.android.modularapp.base.CoreActivity
import kotlinx.android.synthetic.main.activity_cargo_list.*
import java.util.*

class CargoListActivity : CoreActivity<CargoListVM>(CargoListVM::class.java, R.layout.activity_cargo_list),
    CargoAdapter.Listener {

    private lateinit var adapter: CargoAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        toolbar.initForActivity(this, getString(R.string.label_cargoes))
        setupRecycleView()
        adapter.addItems(createList())
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

    override fun onCargoClick(cargo: Cargo) {

    }

    fun createList(): MutableList<Cargo> {
        val list = mutableListOf<Cargo>()
        val p1 = Person(
            id = 1,
            fullName = "John Smith",
            email = "john@mail.com",
            isCourier = false,
            phoneNumber = "700 700 700",
            address = Address(" sender Adress1")
        )

        val p2 = Person(
            id = 2,
            fullName = "Amy Smith",
            email = "amy@mail.com",
            isCourier = false,
            phoneNumber = "700 700 701",
            address = Address(" Adress2")
        )

        val p3 = Person(
            id = 3,
            fullName = "Jane Smith",
            email = "jane@mail.com",
            isCourier = false,
            phoneNumber = "700 700 703",
            address = Address(" Adress3")
        )

        list.add(
            Cargo(
                1,
                p1.address!!,
                p2.address!!,
                p1,
                p2,
                weight = 12.0f,
                price = 13.0,
                deliveryTime = Date(),
                deliveryState = DeliveryState.CREATED,
                deliveryType = DeliveryType.EXPRESS
            )
        )
        list.add(
            Cargo(
                1,
                p3.address!!,
                p3.address!!,
                p2,
                p3,
                weight = 121.0f,
                price = 10.0,
                deliveryTime = Date(),
                deliveryState = DeliveryState.CREATED,
                deliveryType = DeliveryType.ASAP
            )
        )
        return list
    }

    companion object {

        fun start(activity: Activity?) {
            val intent = Intent(activity, CargoListActivity::class.java)
            activity?.startActivity(intent)
        }

    }
}