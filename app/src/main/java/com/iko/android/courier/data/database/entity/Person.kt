package com.iko.android.courier.data.database.entity

data class Person(
    val id: Long,
    var fullName: String? = null,
    var phoneNumber: String? = null,
    var email: String? = null,
    var avatarUrl: String? = null,
    var password: String? = null,
    var isCourier: Boolean? = null,
    var address: Address? = null
)