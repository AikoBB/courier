package com.iko.android.courier.di

import com.iko.android.courier.di.components.main.MainComponent
import com.iko.android.courier.di.components.main.MainModule
import com.iko.android.courier.di.scope.ApplicationScope
import com.iko.android.modularapp.di.component.CoreComponent
import com.iko.android.modularapp.di.viewmodel.ViewModelModule
import dagger.Component

@ApplicationScope
@Component(
    modules = [
        CourierModule::class,
        ViewModelModule::class,
        MainModule::class
    ],
    dependencies = [CoreComponent::class]
)
interface CourierComponent : MainComponent {
}