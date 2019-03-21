package com.iko.android.courier.di.components.main

import androidx.lifecycle.ViewModel
import com.iko.android.courier.di.scope.ApplicationScope
import com.iko.android.courier.ui.main.MainVM
import com.iko.android.courier.ui.splash.SplashVM
import com.iko.android.modularapp.di.viewmodel.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class MainModule {

    @Binds
    @IntoMap
    @ViewModelKey(SplashVM::class)
    abstract fun bindSplashVM(viewModel: SplashVM): ViewModel

    @Binds
    @IntoMap
    @ApplicationScope
    @ViewModelKey(MainVM::class)
    abstract fun bindMainVM(viewModel: MainVM): ViewModel

}