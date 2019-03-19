package com.iko.android.courier.utils

import android.util.Patterns
import java.util.regex.Pattern

val String.isValidEmail
    get() = Patterns.EMAIL_ADDRESS.matcher(this).matches()

val String.isValidPhoneNumber
    get() = Pattern.matches("(0|996|\\+996)?(70[\\d]|50[\\d]|77[\\d]|55[\\d]|755)(\\d{6})", this)