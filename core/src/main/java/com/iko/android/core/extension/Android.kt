package com.iko.android.core.extension

import android.content.Context
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.iko.android.core.R

fun Context.showMessage(message: String){
    Toast.makeText(this, message, Toast.LENGTH_LONG).show()
}

fun Context.showConfirmDialog(content: String, onOkListener: () -> Unit = {}, onCancelListener: () -> Unit = {}) {
    val builder = AlertDialog.Builder(this)
    builder.setMessage(content)
            .setCancelable(false)
            .setPositiveButton(resources.getString(R.string.ok)) { _, _ ->  onOkListener() }
            .setNegativeButton(resources.getString(R.string.cancel)) { dialog, _ ->
                dialog.dismiss()
                onCancelListener()
            }
    val dialog = builder.create()
    dialog.show()
}