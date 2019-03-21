package com.iko.android.courier.data.database.entity

import org.parceler.Parcel
import java.util.*

@Parcel
data class Person(
    var fullName: String? = null,
    var phoneNumber: String? = null,
    var email: String? = null,
    var avatarUrl: String? = null,
    var password: String? = null,
    var isCourier: Boolean? = null,
    var address: Address? = null
)

data class Review(
    val reviewedEmail: String? = null,
    var reviewerName: String? = null,
    var reviewerAvatar: String? = null,
    var review: String? = null,
    var rate: Int = 0,
    var date: Date? = null
)