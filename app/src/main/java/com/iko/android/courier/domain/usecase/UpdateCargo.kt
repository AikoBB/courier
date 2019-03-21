package com.iko.android.courier.domain.usecase

import com.iko.android.courier.data.database.entity.Cargo
import com.iko.android.courier.domain.Callback
import com.iko.android.courier.domain.repository.CargoRepository
import javax.inject.Inject

class UpdateCargo @Inject constructor(private val cargoRepository: CargoRepository.Network) :
    UseCase<UpdateCargo.Params, Callback<Cargo, String?>>() {

    override fun run(params: Params, callback: Callback<Cargo, String?>) {
        cargoRepository.updateCargo(params.cargo, callback)
    }

    data class Params(val cargo: Cargo)
}