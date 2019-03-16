package com.iko.android.courier.di.components.cargo

import androidx.lifecycle.ViewModel
import com.iko.android.courier.ui.cargo.list.CargoListVM
import com.iko.android.modularapp.di.viewmodel.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class CargoModule {

    @Binds
    @IntoMap
    @ViewModelKey(CargoListVM::class)
    abstract fun bindMainVM(viewModel: CargoListVM): ViewModel

}