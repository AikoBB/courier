package com.iko.android.courier.di

import com.iko.android.courier.di.components.RepositoryProvider
import com.iko.android.courier.di.components.auth.AuthComponent
import com.iko.android.courier.di.components.auth.AuthModule
import com.iko.android.courier.di.components.cargo.CargoComponent
import com.iko.android.courier.di.components.cargo.CargoModule
import com.iko.android.courier.di.components.main.MainComponent
import com.iko.android.courier.di.components.main.MainModule
import com.iko.android.courier.di.components.profile.ProfileComponent
import com.iko.android.courier.di.components.profile.ProfileModule
import com.iko.android.courier.di.scope.ApplicationScope
import com.iko.android.modularapp.di.component.CoreComponent
import com.iko.android.modularapp.di.viewmodel.ViewModelModule
import dagger.Component

@ApplicationScope
@Component(
    modules = [
        CourierModule::class,
        ViewModelModule::class,
        AuthModule::class,
        MainModule::class,
        CargoModule::class,
        ProfileModule::class,
        RepositoryProvider::class
    ],
    dependencies = [CoreComponent::class]
)
interface CourierComponent : AuthComponent,MainComponent, CargoComponent, ProfileComponent {
}