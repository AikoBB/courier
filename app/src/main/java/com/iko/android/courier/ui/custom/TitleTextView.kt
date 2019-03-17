package com.iko.android.courier.ui.custom

import android.content.Context
import android.util.AttributeSet
import androidx.constraintlayout.widget.ConstraintLayout
import com.iko.android.courier.R
import kotlinx.android.synthetic.main.title_text_view.view.*

class TitleTextView(context: Context, private val attrs: AttributeSet) : ConstraintLayout(context, attrs) {

    init {
        initView()
    }

    private fun initView() {
        inflate(context, R.layout.title_text_view, this)
        val attributes = context.obtainStyledAttributes(attrs, R.styleable.CourierCustomAttributes)
        attributes.getResourceId(R.styleable.CourierCustomAttributes_android_drawable, -1)
            ?.takeIf { it != -1 }
            ?.let { drawableId ->
                title.setCompoundDrawablesWithIntrinsicBounds(drawableId, 0, 0, 0)
            }
        attributes.getString(R.styleable.CourierCustomAttributes_title)?.let { text ->
            title.text = text
        }
    }

}