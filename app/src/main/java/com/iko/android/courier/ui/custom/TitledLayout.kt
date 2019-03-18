package com.iko.android.courier.ui.custom

import android.content.Context
import android.util.AttributeSet
import androidx.cardview.widget.CardView
import com.iko.android.courier.R
import kotlinx.android.synthetic.main.titled_layout.view.*

open class TitledLayout(context: Context, private val attrs: AttributeSet) : CardView(context, attrs) {

    init {
        initView()
    }

    private fun initView() {
        inflate(context, R.layout.titled_layout, this)
        val attributes = context.obtainStyledAttributes(attrs, R.styleable.CourierCustomAttributes)
        attributes.getResourceId(R.styleable.CourierCustomAttributes_android_drawable, -1)
            ?.takeIf { it != -1 }
            ?.let { drawableId ->
                title_text.setLeftDrawable(drawableId)
            }
        attributes.getString(R.styleable.CourierCustomAttributes_title)?.let { text ->
            title_text.setTitle(text)
        }
    }

}