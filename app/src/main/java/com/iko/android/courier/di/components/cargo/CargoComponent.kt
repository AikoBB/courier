package com.iko.android.courier.di.components.cargo

import com.iko.android.courier.ui.cargo.create.CreateCargoActivity
import com.iko.android.courier.ui.cargo.list.CargoListActivity

interface CargoComponent {
    fun inject(activity: CargoListActivity)
    fun inject(activity: CreateCargoActivity)
}