package com.iko.android.courier.di.components.main

import com.iko.android.courier.ui.main.MainActivity
import com.iko.android.courier.ui.main.home.HomeFragment
import com.iko.android.courier.ui.splash.SplashActivity

interface MainComponent{
    fun inject(activity: SplashActivity)
    fun inject(activity: MainActivity)
    fun inject(fragment: HomeFragment)
}