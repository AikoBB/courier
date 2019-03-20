package com.iko.android.courier.utils

import java.text.SimpleDateFormat
import java.util.*

fun Date.getFormattedForm(format: String = "dd/MM/yyyy"): String{
    return SimpleDateFormat("dd-MM-yyyy").format(this)
}
