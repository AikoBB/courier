package com.iko.android.modularapp.base

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.view.Window
import android.view.inputmethod.InputMethodManager
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.iko.android.core.R
import javax.inject.Inject


/**
 * Created by Aigerim on 7/31/2018.
 */
abstract class CoreActivity<V : CoreViewModel<*>>(
    private var viewModelClass: Class<V>,
    @LayoutRes private var layoutId: Int
) : AppCompatActivity() {
    protected lateinit var viewModel: V

    @Inject
    lateinit var factory: ViewModelProvider.Factory
    private lateinit var loader: Dialog

    override fun onCreate(savedInstanceState: Bundle?) {
        performDependencyInjection()
        super.onCreate(savedInstanceState)
        setContentView(layoutId)
        viewModel = ViewModelProviders.of(this, factory).get(viewModelClass)
        setupLoadingDialog()
        subscribeLoadingData()
    }

    abstract fun performDependencyInjection()

    fun hideKeyboard() {
        val view = this.currentFocus
        if (view != null) {
            val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(view.windowToken, 0)
        }
    }

    private fun subscribeLoadingData() {
        viewModel.isLoading.observe(this, Observer {
            when (it) {
                true -> showLoading()
                else -> hideLoading()
            }
        })
    }

    private fun setupLoadingDialog() {
        loader = Dialog(this, android.R.style.Theme_Translucent_NoTitleBar_Fullscreen)
        loader.run {
            requestWindowFeature(Window.FEATURE_NO_TITLE)
            setCancelable(false)
            setContentView(R.layout.loader_layout)
        }
    }

    private fun showLoading() {
        loader.show()
    }

    private fun hideLoading() {
        loader.dismiss()
    }

}