package com.iko.android.courier.data.database.entity

import java.text.SimpleDateFormat
import java.util.*

data class Cargo(
    val id: Long,
    var addressFrom: Address,
    var addressTo: Address,
    var sender: Person,
    var receiver: Person,
    var assignee: Person? = null,
    var weight: Float,
    var price: Double,
    var deliveryTime: Date? = null,
    var deliveryType: DeliveryType? = null,
    var deliveryState: DeliveryState
) {
    fun getDateOnly(): String {
        return when (deliveryTime != null) {
            true -> SimpleDateFormat("dd-MM-yyyy").format(deliveryTime)
            else -> ""
        }
    }

    fun getTimeOnly(): String {
        return when (deliveryTime != null) {
            true -> SimpleDateFormat("HH:mm").format(deliveryTime)
            else -> ""
        }
    }
}

enum class DeliveryState {
    CREATED,
    ASSIGNED,
    ON_THE_WAY,
    DELIVERED,
}

enum class DeliveryType {
    EXPRESS,
    ANY_TIME,
    ASAP
}