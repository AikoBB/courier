package com.iko.android.courier.data.database.entity

import java.util.*

data class Person(
    val id: Long = 1,
    var fullName: String? = null,
    var phoneNumber: String? = null,
    var email: String? = null,
    var avatarUrl: String? = null,
    var password: String? = null,
    var isCourier: Boolean? = null,
    var address: Address? = null
)

data class Review(
    val reviewedId: Long = 1,
    var reviewerName: String? = null,
    var reviewerAvatar: String? = null,
    var review: String? = null,
    var date: Date? = null
)