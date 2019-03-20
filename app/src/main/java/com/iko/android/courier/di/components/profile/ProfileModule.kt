package com.iko.android.courier.di.components.profile

import androidx.lifecycle.ViewModel
import com.iko.android.courier.ui.profile.ProfileVM
import com.iko.android.courier.ui.profile.balance.BalanceVM
import com.iko.android.courier.ui.profile.review.ReviewVM
import com.iko.android.modularapp.di.viewmodel.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ProfileModule {

    @Binds
    @IntoMap
    @ViewModelKey(ProfileVM::class)
    abstract fun bindProfileVM(viewModel: ProfileVM): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(ReviewVM::class)
    abstract fun bindReviewVM(viewModel: ReviewVM): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(BalanceVM::class)
    abstract fun bindBalanceVM(viewModel: BalanceVM): ViewModel
}