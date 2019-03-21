package com.iko.android.courier.di.components

import com.iko.android.courier.di.scope.ApplicationScope
import com.iko.android.courier.domain.repository.AuthRepository
import com.iko.android.courier.domain.repository.ProfileRepository
import dagger.Module
import dagger.Provides

@Module
class RepositoryProvider {

    @Provides
    @ApplicationScope
    fun provideAuthRepository(dataSource: AuthRepository.Network): AuthRepository = dataSource

    @Provides
    @ApplicationScope
    fun provideProfileRepository(dataSource: ProfileRepository.Network): ProfileRepository = dataSource

}