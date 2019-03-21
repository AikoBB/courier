package com.iko.android.courier.ui.cargo.list

import com.iko.android.courier.data.CargoEvent
import com.iko.android.courier.data.Event
import com.iko.android.courier.data.database.entity.Cargo
import com.iko.android.courier.data.database.entity.CargoList
import com.iko.android.courier.data.prefs.AppPreferences
import com.iko.android.courier.domain.Callback
import com.iko.android.courier.domain.usecase.DeleteCargo
import com.iko.android.courier.domain.usecase.FetchCargoList
import com.iko.android.courier.domain.usecase.UpdateCargo
import com.iko.android.courier.domain.usecase.UseCase
import com.iko.android.modularapp.base.CoreViewModel
import javax.inject.Inject

class CargoListVM @Inject constructor(
    private val fetchCargoList: FetchCargoList,
    private val updateCargo: UpdateCargo,
    private val deleteCargo: DeleteCargo,
    private val prefs: AppPreferences) : CoreViewModel<Event>() {

    private var listRequested = false

    init {
        if (!listRequested) {
            listRequested = true
            showLoading()
            fetchCargoList.run(UseCase.None(), Callback(::onListReady) { event.value = Event.Notification(it ?: "") })
        }
    }

    private fun onListReady(cargoList: CargoList) {
        hideLoading()
        event.value = CargoEvent.CargoListFetched(cargoList)
    }

    fun deleteCargo(cargo: Cargo){
        deleteCargo.run(DeleteCargo.Params(cargo.id!!), Callback({event.value = CargoEvent.CargoDeleted(cargo.id!!)}, {}))
    }

    fun sendRequest(cargo: Cargo){
        cargo.requests.add(prefs.currentUser!!)
        updateCargo.run(UpdateCargo.Params(cargo), Callback({event.value = CargoEvent.CargoRequestSent(it.id!!)}, {}))
    }

}