package com.iko.android.courier.domain.usecase

import com.iko.android.courier.data.database.entity.CargoList
import com.iko.android.courier.domain.Callback
import com.iko.android.courier.domain.repository.CargoRepository
import javax.inject.Inject

class FetchCargoList @Inject constructor(private val cargoRepository: CargoRepository.Network) :
    UseCase<UseCase.None, Callback<CargoList, String?>>() {

    override fun run(params: None, callback: Callback<CargoList, String?>) {
        cargoRepository.getCargoList(callback)
    }
}