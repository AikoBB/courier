package com.iko.android.modularapp.networking

import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * Created by Aigerim on 7/30/2018.
 */
class AppScheduler: Scheduler{

    override fun ui(): io.reactivex.Scheduler = AndroidSchedulers.mainThread()

    override fun io(): io.reactivex.Scheduler=  Schedulers.io()

    override fun computation(): io.reactivex.Scheduler = Schedulers.computation()

    override fun newThread(): io.reactivex.Scheduler = Schedulers.newThread()
}