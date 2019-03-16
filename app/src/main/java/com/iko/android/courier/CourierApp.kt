package com.iko.android.courier

import com.iko.android.courier.di.CourierComponent
import com.iko.android.courier.di.DaggerCourierComponent
import com.iko.android.modularapp.application.CoreApp

class CourierApp: CoreApp(){
    val appComponent: CourierComponent by lazy {
        return@lazy DaggerCourierComponent
            .builder()
            .coreComponent(coreComponent)
            .build() as CourierComponent
    }
}