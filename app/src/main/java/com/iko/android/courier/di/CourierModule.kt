package com.iko.android.courier.di

import android.content.Context
import android.content.SharedPreferences
import com.google.firebase.auth.FirebaseAuth
import com.iko.android.courier.data.database.CourierDatabase
import com.iko.android.courier.data.prefs.AppPreferences
import com.iko.android.courier.di.scope.ApplicationScope
import com.iko.android.courier.domain.repository.AuthRepository
import dagger.Module
import dagger.Provides

@Module
class CourierModule {

    @ApplicationScope
    @Provides
    internal fun provideDatabase(context: Context) = CourierDatabase.getInstance(context)

    @ApplicationScope
    @Provides
    internal fun provideAppPrefernces(prefs: SharedPreferences) = AppPreferences(prefs)

    @ApplicationScope
    @Provides
    internal fun provideFirebaseAuth() = FirebaseAuth.getInstance()

    @ApplicationScope
    @Provides
    internal fun provideAuthRepository(firebaseAuth: FirebaseAuth, prefs: AppPreferences) = AuthRepository.Network(firebaseAuth, prefs)

}