package com.iko.android.courier.ui.custom

import android.content.Context
import android.util.AttributeSet
import android.util.Patterns
import android.view.LayoutInflater
import com.iko.android.core.extension.showWarningDialog
import com.iko.android.courier.R
import com.iko.android.courier.data.database.entity.Person
import kotlinx.android.synthetic.main.person_info_input_view.view.*
import kotlinx.android.synthetic.main.titled_layout.view.*
import java.util.regex.Pattern

class PersonInfoInputView(context: Context, private val attrs: AttributeSet) : TitledLayout(context, attrs), Validator {

    init {
        initView()
    }

    private fun initView() {
        val inputView = LayoutInflater.from(context).inflate(R.layout.person_info_input_view, null, false)
        container.addView(inputView)
        val attributes = context.obtainStyledAttributes(attrs, R.styleable.CourierCustomAttributes)
        attributes.getBoolean(R.styleable.CourierCustomAttributes_editable, true)?.let { editable ->
            receiver_name.isEnabled = editable
            receiver_email.isEnabled = editable
            receiver_phone.isEnabled = editable
        }
    }

    override fun isValidInput(): Boolean {
        return when {
            receiver_name.text.isEmpty() -> {
                context.showWarningDialog(context.getString(R.string.warning_can_not_be_empty, receiver_name.hint))
                false
            }
            receiver_phone.text.isEmpty()
                    || !Pattern.matches(
                "(0|996|\\+996)?(70[\\d]|50[\\d]|77[\\d]|55[\\d]|755)(\\d{6})",
                receiver_phone.text.toString()
            ) -> {
                context.showWarningDialog(context.getString(R.string.warning_empty_incorrect, receiver_phone.hint))
                false
            }
            receiver_email.text.isNotEmpty() && !Patterns.EMAIL_ADDRESS.matcher(receiver_email.text.toString()).matches() -> {
                context.showWarningDialog(context.getString(R.string.warning_incorrect, receiver_email.hint))
                false
            }
            else -> true
        }
    }

    fun setInputs(person: Person) {
        receiver_name.setText(person.fullName)
        receiver_email.setText(person.email)
        receiver_phone.setText(person.phoneNumber)
    }

    fun getInputs(person: Person) {
        person.fullName = receiver_name.text.toString()
        person.email = receiver_email.text.toString()
        person.phoneNumber = receiver_phone.text.toString()
    }

}