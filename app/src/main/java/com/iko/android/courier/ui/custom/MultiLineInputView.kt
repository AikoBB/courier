package com.iko.android.courier.ui.custom

import android.content.Context
import android.text.InputFilter
import android.text.InputType
import android.util.AttributeSet
import android.util.TypedValue
import android.view.Gravity
import android.widget.EditText
import com.iko.android.core.extension.showWarningDialog
import com.iko.android.courier.R
import kotlinx.android.synthetic.main.titled_layout.view.*

class MultiLineInputView(context: Context, private val attrs: AttributeSet) : TitledLayout(context, attrs), Validator {

    private lateinit var input: EditText

    init {
        initView()
    }

    private fun initView() {
        setupEditText()
        val attributes = context.obtainStyledAttributes(attrs, R.styleable.CourierCustomAttributes)
        attributes.getBoolean(R.styleable.CourierCustomAttributes_editable, true)?.let { editable ->
            input.isEnabled = editable
            input.isFocusable = editable
        }
        attributes.getString(R.styleable.CourierCustomAttributes_android_hint)?.let { hint ->
            input.hint = hint
        }
        container.addView(input)
    }

    private fun setupEditText() {
        input = EditText(context)
        input.run {
            gravity = Gravity.TOP
            inputType = InputType.TYPE_TEXT_FLAG_MULTI_LINE
            setLines(3)
            maxLines = 3
            setSingleLine(false)
            filters = arrayOf(InputFilter.LengthFilter(300))
            setTextSize(TypedValue.COMPLEX_UNIT_SP, 14f)
        }
    }

    override fun isValidInput(): Boolean {
        val isNotEmpty = getText().isNotEmpty()
        if (!isNotEmpty) context.showWarningDialog(context.getString(R.string.warning_can_not_be_empty, input.hint))
        return isNotEmpty
    }

    fun getText() = input.text.toString()

    fun setText(text: String) {
        input.setText(text)
    }


}