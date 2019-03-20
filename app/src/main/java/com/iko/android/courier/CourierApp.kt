package com.iko.android.courier

import com.facebook.drawee.backends.pipeline.Fresco
import com.google.firebase.FirebaseApp
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

    override fun onCreate() {
        super.onCreate()
        FirebaseApp.initializeApp(this)
        Fresco.initialize(this)
    }
}