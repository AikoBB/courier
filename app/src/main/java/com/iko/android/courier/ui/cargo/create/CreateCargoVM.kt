package com.iko.android.courier.ui.cargo.create

import com.iko.android.courier.data.CargoEvent
import com.iko.android.courier.data.Event
import com.iko.android.courier.data.database.entity.Cargo
import com.iko.android.courier.data.database.entity.Person
import com.iko.android.courier.domain.Callback
import com.iko.android.courier.domain.usecase.CreateCargo
import com.iko.android.modularapp.base.CoreViewModel
import javax.inject.Inject

class CreateCargoVM @Inject constructor(private val createCargo: CreateCargo) : CoreViewModel<Event>() {
    var currentUser: Person? = null

    fun createCargo(cargo: Cargo) {
        showLoading()
        createCargo.run(
            CreateCargo.Params(cargo),
            Callback(
                { event.value = CargoEvent.CargoCreated(cargo) },
                { event.value = Event.Notification(it ?: "") })
        )
    }
}