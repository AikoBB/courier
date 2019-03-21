package com.iko.android.courier.di.components.cargo

import com.iko.android.courier.ui.cargo.create.CreateCargoActivity
import com.iko.android.courier.ui.cargo.list.CargoListActivity
import com.iko.android.courier.ui.cargo.list.DeliversFragment
import com.iko.android.courier.ui.cargo.list.OwnCargoListFragment

interface CargoComponent {
    fun inject(activity: CargoListActivity)
    fun inject(activity: CreateCargoActivity)
    fun inject(fragment: DeliversFragment)
    fun inject(fragment: OwnCargoListFragment)
}