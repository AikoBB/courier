package com.iko.android.courier.domain.usecase

import com.iko.android.courier.domain.Callback
import com.iko.android.courier.domain.repository.CargoRepository
import javax.inject.Inject

class DeleteCargo @Inject constructor(private val cargoRepository: CargoRepository.Network) :
    UseCase<DeleteCargo.Params, Callback<Any?, String?>>() {

    override fun run(params: Params, callback: Callback<Any?, String?>) {
        cargoRepository.deleteCargo(params.cargoId, callback)
    }

    data class Params(val cargoId: String)
}