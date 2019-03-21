package com.iko.android.courier.ui.cargo.list

import com.iko.android.courier.data.CargoEvent
import com.iko.android.courier.data.Event
import com.iko.android.courier.data.database.entity.CargoList
import com.iko.android.courier.domain.Callback
import com.iko.android.courier.domain.usecase.FetchCargoList
import com.iko.android.courier.domain.usecase.UseCase
import com.iko.android.modularapp.base.CoreViewModel
import javax.inject.Inject

class CargoListVM @Inject constructor(private val fetchCargoList: FetchCargoList) : CoreViewModel<Event>() {

    init {
        showLoading()
        fetchCargoList.run(UseCase.None(), Callback(::onListReady) { event.value = Event.Notification(it ?: "") })
    }

    private fun onListReady(cargoList: CargoList) {
        hideLoading()
        event.value = CargoEvent.CargoListFetched(cargoList.readyForDeliver)
    }
}