package com.iko.android.modularapp.networking

/**
 * Created by Aigerim on 7/30/2018.
 */

interface Scheduler{
    fun ui(): io.reactivex.Scheduler
    fun io(): io.reactivex.Scheduler
    fun computation(): io.reactivex.Scheduler
    fun newThread(): io.reactivex.Scheduler
}