package com.iko.android.modularapp.base

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable

/**
 * Created by Aigerim on 7/31/2018.
 */
open class CoreViewModel<T> : ViewModel() {

    var isLoading: MutableLiveData<Boolean> = MutableLiveData()
    val event: MutableLiveData<T> = MutableLiveData()
    private var compositeDisposable: CompositeDisposable = CompositeDisposable()

    protected fun setIsloading(result: Boolean) {
        isLoading.value = result
    }

    fun showLoading() {
        isLoading.value = true
    }

    fun hideLoading() {
        isLoading.value = false
    }

    override fun onCleared() {
        compositeDisposable.clear()
        super.onCleared()
    }

    fun getDisposable(): CompositeDisposable {
        if (compositeDisposable == null || compositeDisposable.isDisposed)
            compositeDisposable = CompositeDisposable()
        return compositeDisposable
    }

}
