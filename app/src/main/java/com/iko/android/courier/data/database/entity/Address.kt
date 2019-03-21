package com.iko.android.courier.data.database.entity

import org.parceler.Parcel

@Parcel
data class Address(
    val name: String? = null,
    val longitude: Double? = null,
    val latitude: Double? = null
)