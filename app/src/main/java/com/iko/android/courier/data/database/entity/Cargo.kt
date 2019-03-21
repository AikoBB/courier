package com.iko.android.courier.data.database.entity

import com.iko.android.courier.utils.getFormattedForm
import org.parceler.Parcel
import java.util.*

@Parcel
data class Cargo(
    var id: String? = null,
    var addressFrom: Address? = null,
    var addressTo: Address? = null,
    var sender: Person? = null,
    var receiver: Person? = null,
    var assignee: Person? = null,
    var note: String? = null,
    var weight: Float? = null,
    var price: Float? = null,
    var deliveryTime: Date? = null,
    var deliveryType: DeliveryType? = null,
    var states: MutableList<CargoStateInfo> = mutableListOf()
) {
    fun getDateOnly(): String {
        return when (deliveryTime != null) {
            true -> deliveryTime!!.getFormattedForm()
            else -> ""
        }
    }

    fun getTimeOnly(): String {
        return when (deliveryTime != null) {
            true -> deliveryTime!!.getFormattedForm("HH:mm")
            else -> ""
        }
    }
}

@Parcel
data class CargoStateInfo(
    val state: CargoState? = null,
    val date: Date? = null
)

enum class CargoState {
    CREATED,
    ASSIGNED,
    COURIER_ON_THE_WAY,
    PICK_UP,
    DELIVERED,
}

enum class DeliveryType {
    EXPRESS,
    ANY_TIME,
    ASAP
}

data class CargoList(
    var own: List<Cargo> = listOf(),
    var delivers: List<Cargo> = listOf(),
    var readyForDeliver: List<Cargo> = listOf()
)
