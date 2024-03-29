package com.iko.android.modularapp.base

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import javax.inject.Inject


/**
 * Created by Aigerim on 7/31/2018.
 */

abstract class CoreFragment<V : CoreViewModel<*>>(
    private var viewModelClass: Class<V>,
    @LayoutRes private var layoutId: Int
) : Fragment() {
    protected lateinit var viewModel: V
    protected lateinit var activity: CoreActivity<V>
    @Inject
    lateinit var factory: ViewModelProvider.Factory

    override fun onCreate(savedInstanceState: Bundle?) {
        performDependencyInjection()
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProviders.of(this, factory).get(viewModelClass)
        setHasOptionsMenu(false)
    }

    abstract fun performDependencyInjection()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(layoutId, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        subscribeLoadingData()
    }

    private fun subscribeLoadingData() {
        viewModel.isLoading.observe(this, Observer {
            when (it) {
                true -> activity.showLoading()
                else -> activity.hideLoading()
            }
        })
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        if (context is CoreActivity<*>)
            activity = context as CoreActivity<V>
    }

    override fun onDetach() {
        super.onDetach()
    }
}