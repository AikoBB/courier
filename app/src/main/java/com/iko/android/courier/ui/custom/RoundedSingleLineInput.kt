package com.iko.android.courier.ui.custom

import android.content.Context
import android.util.AttributeSet
import android.widget.RelativeLayout
import com.iko.android.courier.R
import kotlinx.android.synthetic.main.rounded_singleline_input_view.view.*

class RoundedSingleLineInput(context: Context, private val attrs: AttributeSet) : RelativeLayout(context, attrs) {

    init {
        initView()
    }

    private fun initView() {
        inflate(context, R.layout.rounded_singleline_input_view, this)
        val attributes = context.obtainStyledAttributes(attrs, R.styleable.CourierCustomAttributes)
        attributes.getResourceId(R.styleable.CourierCustomAttributes_android_drawable, -1)
            ?.takeIf { it != -1 }
            ?.let { drawableId ->
                input.setCompoundDrawablesWithIntrinsicBounds(drawableId,0,0,0)
            }
        attributes.getString(R.styleable.CourierCustomAttributes_android_hint)?.let { text ->
            input.hint = text
        }
    }

    fun getText() = input.text.toString()
    fun setInputText(type: Int){
        input.inputType = type
    }

}