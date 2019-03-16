package com.iko.android.modularapp.di.module

import android.content.Context
import android.content.SharedPreferences
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Created by Aigerim on 7/30/2018.
 */

@Module
class StorageModule{

    @Provides
    @Singleton
    fun providesSharedPreferences(context: Context): SharedPreferences {
        return context.getSharedPreferences("shared_preference", Context.MODE_PRIVATE)
    }

}