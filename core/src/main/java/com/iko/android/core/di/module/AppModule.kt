package com.iko.android.modularapp.di.module

import android.content.Context
import com.iko.android.modularapp.application.CoreApp
import com.iko.android.modularapp.networking.AppScheduler
import com.iko.android.modularapp.networking.Scheduler
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Created by Aigerim on 7/30/2018.
 */

@Module
class AppModule{

    @Provides
    @Singleton
    fun provideContext(application: CoreApp): Context = application

    @Provides
    @Singleton
    fun provideResources(context: Context) = context.resources

    @Provides
    @Singleton
    fun scheduler(): Scheduler = AppScheduler()

}