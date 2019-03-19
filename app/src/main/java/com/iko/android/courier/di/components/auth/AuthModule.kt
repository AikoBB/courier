package com.iko.android.courier.di.components.auth

import androidx.lifecycle.ViewModel
import com.iko.android.courier.ui.auth.login.LoginVM
import com.iko.android.modularapp.di.viewmodel.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class AuthModule {

    @Binds
    @IntoMap
    @ViewModelKey(LoginVM::class)
    abstract fun bindLoginVM(viewModel: LoginVM): ViewModel

}