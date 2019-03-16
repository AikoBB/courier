package com.iko.android.modularapp.di.component

import android.content.Context
import android.content.SharedPreferences
import android.content.res.Resources
import com.iko.android.modularapp.application.CoreApp
import com.iko.android.modularapp.di.module.AppModule
import com.iko.android.modularapp.di.module.StorageModule
import com.iko.android.modularapp.networking.Scheduler
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton


/**
 * Created by Aigerim on 7/30/2018.
 */
@Singleton
@Component(modules = [
    AppModule::class,
    StorageModule::class,
    AndroidSupportInjectionModule::class])
interface CoreComponent : AndroidInjector<CoreApp> {

    @Component.Builder
    abstract class Builder: AndroidInjector.Builder<CoreApp>()

    val context: Context
    val resources: Resources
    val sharedPreferences: SharedPreferences
    val scheduler: Scheduler

}