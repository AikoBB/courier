package com.iko.android.courier.ui.custom

import android.content.Context
import android.content.res.TypedArray
import android.util.AttributeSet
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import com.iko.android.courier.R
import com.iko.android.modularapp.extension.invisible
import kotlinx.android.synthetic.main.clickable_info_row.view.*

class ClickableInfoRow(context: Context, private val attrs: AttributeSet) : ConstraintLayout(context, attrs) {

    private lateinit var attributes: TypedArray

    init {
        initView()
    }

    private fun initView() {
        inflate(context, R.layout.clickable_info_row, this)
        attributes = context.obtainStyledAttributes(attrs, R.styleable.CourierCustomAttributes)
        setupIcon()
        setupTitle()
        setupSubtitle()
    }

    private fun setupIcon() {
        attributes.getResourceId(R.styleable.CourierCustomAttributes_android_drawable, -1).let { drawableId ->
            when (drawableId == -1) {
                true -> icon.invisible()
                else -> icon.background = ContextCompat.getDrawable(context, drawableId)
            }
        }
    }

    private fun setupTitle() {
        attributes.getString(R.styleable.CourierCustomAttributes_title)?.let { text ->
            title.text = text
        }
    }

    private fun setupSubtitle() {
        attributes.getString(R.styleable.CourierCustomAttributes_subtitle).let { text ->
            subtitle.text = text
        }
    }

    private fun setOnClick(perform: () -> Unit){
        this.setOnClickListener { perform() }
    }

}