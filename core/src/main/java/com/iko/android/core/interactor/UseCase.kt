package com.iko.android.modularapp.interactor

import com.iko.android.modularapp.exception.Failure
import com.iko.android.modularapp.functional.Either
import kotlinx.coroutines.*

abstract class UseCase<out Type, in Params> where Type : Any {

    abstract suspend fun run(params: Params): Either<Failure, Type>

    operator fun CoroutineScope.invoke(params: Params, onResult: (Either<Failure, Type>) -> Unit = {}) {
        val deferred = async(Dispatchers.Default){ run(params) }
        launch(Dispatchers.Main) { onResult(deferred.await()) }
    }

    class None
}