package com.iko.android.courier.di.components.cargo

import com.iko.android.courier.ui.cargo.create.CreateCargoActivity
import com.iko.android.courier.ui.cargo.list.CargoListActivity
import com.iko.android.courier.ui.cargo.list.DeliversFragment
import com.iko.android.courier.ui.cargo.list.OwnCargoListFragment
import com.iko.android.courier.ui.cargo.state.CargoManagementActivity
import com.iko.android.courier.ui.cargo.state.CargoStateFragment
import com.iko.android.courier.ui.cargo.state.DeliveryRequestsFragment

interface CargoComponent {
    fun inject(activity: CargoListActivity)
    fun inject(activity: CreateCargoActivity)
    fun inject(fragment: DeliversFragment)
    fun inject(fragment: OwnCargoListFragment)
    fun inject(activity: CargoManagementActivity)
    fun inject(fragment: DeliveryRequestsFragment)
    fun inject(fragment: CargoStateFragment)
}