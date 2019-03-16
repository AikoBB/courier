package com.iko.android.core.custom_view

import android.content.Context
import android.util.AttributeSet
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.appbar.AppBarLayout
import com.iko.android.core.R
import kotlinx.android.synthetic.main.toolbar.*

class CustomToolbar(context: Context, private val attrs: AttributeSet) : AppBarLayout(context, attrs) {

    init {
        inflate(context, R.layout.toolbar, this)
    }

    fun initForActivity(
        activity: AppCompatActivity?,
        title: String? = null,
        onBackClick: () -> Unit = { activity?.finish() }
    ) {
        activity?.run {
            setSupportActionBar(tb)
            supportActionBar?.setHomeButtonEnabled(true)
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
            tb?.setNavigationOnClickListener { onBackClick() }
            title?.let { supportActionBar?.title = it }
        }

    }

}