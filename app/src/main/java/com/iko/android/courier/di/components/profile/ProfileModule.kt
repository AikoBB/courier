package com.iko.android.courier.di.components.profile

import androidx.lifecycle.ViewModel
import com.iko.android.courier.di.scope.ApplicationScope
import com.iko.android.courier.ui.profile.ProfileVM
import com.iko.android.modularapp.di.viewmodel.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ProfileModule {

    @Binds
    @IntoMap
    @ApplicationScope
    @ViewModelKey(ProfileVM::class)
    abstract fun bindProfileVM(viewModel: ProfileVM): ViewModel

}