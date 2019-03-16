package com.iko.android.courier.di.components.main

import androidx.lifecycle.ViewModel
import com.iko.android.courier.ui.main.MainVM
import com.iko.android.courier.ui.main.home.HomeVM
import com.iko.android.modularapp.di.viewmodel.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class MainModule {

    @Binds
    @IntoMap
    @ViewModelKey(MainVM::class)
    abstract fun bindMainVM(viewModel: MainVM): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(HomeVM::class)
    abstract fun bindHomeVM(viewModel: HomeVM): ViewModel

}