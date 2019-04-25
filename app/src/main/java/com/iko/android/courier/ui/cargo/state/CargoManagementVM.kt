package com.iko.android.courier.ui.cargo.state

import com.iko.android.courier.data.CargoEvent
import com.iko.android.courier.data.Event
import com.iko.android.courier.data.database.entity.Cargo
import com.iko.android.courier.data.database.entity.Person
import com.iko.android.courier.domain.Callback
import com.iko.android.courier.domain.usecase.FetchCargoById
import com.iko.android.courier.domain.usecase.UpdateCargo
import com.iko.android.modularapp.base.CoreViewModel
import javax.inject.Inject

class CargoManagementVM @Inject constructor(
    private val fetchCargoById: FetchCargoById,
    private val updateCargo: UpdateCargo
) : CoreViewModel<Event>() {

    var cargo: Cargo? = null

    fun fetchCargoById(id: String) {
        showLoading()
        fetchCargoById.run(FetchCargoById.Params(id), Callback({
            hideLoading()
            cargo = it
            event.value = CargoEvent.CargoInfoFetched(cargo!!)
        }, ::handleError))
    }

    fun acceptRequest(person: Person) {
        cargo?.assignee = person
        updateCargo.run(UpdateCargo.Params(cargo!!), Callback({
            cargo = it
            event.value = CargoEvent.CargoRequestAccepted(it)
        }, ::handleError))
    }

    fun requestProfile(person: Person) {
        event.value = CargoEvent.ProfileRequested(person)
    }

    private fun handleError(message: String?) {
        hideLoading()
        event.value = Event.Notification(message ?: "Unexpected error")
    }

}