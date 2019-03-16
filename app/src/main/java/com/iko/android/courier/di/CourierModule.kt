package com.iko.android.courier.di

import android.content.Context
import com.iko.android.courier.data.database.CourierDatabase
import com.iko.android.courier.di.scope.ApplicationScope
import dagger.Module
import dagger.Provides

@Module
class CourierModule {

    @ApplicationScope
    @Provides
    internal fun provideDatabase(context: Context) = CourierDatabase.getInstance(context)

}