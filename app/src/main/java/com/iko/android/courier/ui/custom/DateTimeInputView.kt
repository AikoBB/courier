package com.iko.android.courier.ui.custom

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import com.iko.android.core.extension.showWarningDialog
import com.iko.android.courier.R
import com.iko.android.courier.data.database.entity.Cargo
import com.iko.android.courier.data.database.entity.DeliveryType
import com.iko.android.courier.utils.getFormattedForm
import com.iko.android.modularapp.extension.gone
import kotlinx.android.synthetic.main.date_time_input_view.view.*
import kotlinx.android.synthetic.main.titled_layout.view.*
import java.text.SimpleDateFormat
import java.util.*


class DateTimeInputView(context: Context, private val attrs: AttributeSet) : TitledLayout(context, attrs), Validator {

    private lateinit var inputView: View

    init {
        initView()
    }

    private fun initView() {
        inputView =
            LayoutInflater.from(context).inflate(com.iko.android.courier.R.layout.date_time_input_view, null, false)
        container.addView(inputView)
        val attributes =
            context.obtainStyledAttributes(attrs, com.iko.android.courier.R.styleable.CourierCustomAttributes)
        attributes.getBoolean(com.iko.android.courier.R.styleable.CourierCustomAttributes_editable, true)
            ?.let { editable ->
                if (editable) {
                    inputView.delivery_date.setOnClickListener { showDatePicker() }
                    inputView.delivery_time.setOnClickListener { showTimePicker() }
                } else {
                    spinner.gone()
                }
            }
        setupDeliveryTypeSpinner()
    }

    private fun showDatePicker() {
        val calendar = Calendar.getInstance()
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val day = calendar.get(Calendar.DAY_OF_MONTH)
        val pickerDialog =
            DatePickerDialog(context, DatePickerDialog.OnDateSetListener { _, mYear, monthOfYear, dayOfMonth ->
                calendar.set(Calendar.YEAR, mYear)
                calendar.set(Calendar.MONTH, monthOfYear)
                calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth)
                inputView.delivery_date.text = Date(calendar.timeInMillis).getFormattedForm()
            }, year, month, day)
        pickerDialog.datePicker.minDate = calendar.timeInMillis
        pickerDialog.show()
    }

    private fun showTimePicker() {
        val currentTime = Calendar.getInstance()
        val hour = currentTime.get(Calendar.HOUR_OF_DAY)
        val minute = currentTime.get(Calendar.MINUTE)
        val timePicker = TimePickerDialog(
            context,
            TimePickerDialog.OnTimeSetListener { _, selectedHour, selectedMinute ->
                inputView.delivery_time.text = "$selectedHour:$selectedMinute"
            }, hour, minute, true
        )
        timePicker.setTitle(context.getString(R.string.label_select_time))
        timePicker.show()
    }

    private fun setupDeliveryTypeSpinner() {
        val types = mutableListOf<String>()
        context.resources.getStringArray(R.array.delivery_type).forEach { types.add(it.toString()) }
        inputView.spinner.adapter = ArrayAdapter<String>(context, android.R.layout.simple_spinner_item, types)
        inputView.spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {}
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                if (isLastItemSelected()) {
                    inputView.delivery_date.text = context.getString(R.string.label_date)
                    inputView.delivery_time.text = context.getString(R.string.label_time)
                }
            }
        }
    }

    private fun isLastItemSelected() = inputView.spinner.selectedItemPosition > 1

    override fun isValidInput(): Boolean {
        return when {
            isLastItemSelected() -> true
            inputView.delivery_date.text == context.getString(com.iko.android.courier.R.string.label_date) -> {
                context.showWarningDialog(
                    context.getString(
                        com.iko.android.courier.R.string.warning_choose_delivery_info,
                        inputView.delivery_date.text.toString().toLowerCase()
                    )
                )
                false
            }
            inputView.delivery_time.text == context.getString(com.iko.android.courier.R.string.label_time) -> {
                context.showWarningDialog(
                    context.getString(
                        com.iko.android.courier.R.string.warning_choose_delivery_info,
                        inputView.delivery_time.text.toString().toLowerCase()
                    )
                )
                false
            }
            else -> true
        }
    }

    fun setInputs(cargo: Cargo) {
        inputView.delivery_date.text = cargo.getDateOnly()
        inputView.delivery_time.text = cargo.getTimeOnly()
        cargo.deliveryType?.let {
            inputView.delivery_time.text = when (it) {
                DeliveryType.EXPRESS -> {
                    inputView.spinner.setSelection(0)
                    inputView.spinner.getItemAtPosition(0) as String
                }
                DeliveryType.ASAP -> {
                    inputView.spinner.setSelection(1)
                    inputView.spinner.getItemAtPosition(1) as String
                }
                DeliveryType.ANY_TIME -> {
                    inputView.spinner.setSelection(2)
                    inputView.spinner.getItemAtPosition(2) as String
                }
            }
        }
    }

    fun setToCargo(cargo: Cargo) {
        if (!isLastItemSelected())
            cargo.deliveryTime =
                SimpleDateFormat("dd/MM/yyyy HH:mm").parse("${inputView.delivery_date.text} ${inputView.delivery_time.text}")
        cargo.deliveryType = when (inputView.spinner.selectedItemPosition) {
            0 -> DeliveryType.EXPRESS
            1 -> DeliveryType.ASAP
            else -> DeliveryType.ANY_TIME
        }
    }

}