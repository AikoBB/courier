package com.iko.android.modularapp.application

import android.app.Application
import com.iko.android.modularapp.di.component.CoreComponent
import com.iko.android.modularapp.di.component.DaggerCoreComponent

/**
 * Created by Aigerim on 7/30/2018.
 */
open class CoreApp : Application() {

    val coreComponent: CoreComponent by lazy {
        return@lazy DaggerCoreComponent.builder().create(this) as CoreComponent
    }
}