package com.iko.android.courier.domain.usecase

abstract class UseCase<in Params, in Callback> {

    abstract fun run(params: Params, callback: Callback)
    class None
}