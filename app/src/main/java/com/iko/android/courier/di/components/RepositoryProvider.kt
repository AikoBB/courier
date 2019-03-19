package com.iko.android.courier.di.components

import com.iko.android.courier.di.scope.ApplicationScope
import com.iko.android.courier.domain.repository.AuthRepository
import dagger.Module
import dagger.Provides

@Module
class RepositoryProvider {

    @Provides
    @ApplicationScope
    fun provideAuthRepository(dataSource: AuthRepository.Network): AuthRepository = dataSource

}