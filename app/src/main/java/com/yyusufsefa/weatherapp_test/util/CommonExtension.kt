package com.yyusufsefa.weatherapp_test.util

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity

infix fun Activity.changeActionbarVisibility(isShow: Boolean) {
    try {
        (this as AppCompatActivity).supportActionBar?.let {
            if (isShow) {
                if (!it.isShowing) {
                    it.show()
                }
            } else {
                if (it.isShowing) {
                    it.hide()
                }
            }
        }

    } catch (exception: Exception) {
        throw Exception("Activity is not an AppCompatActivity")
    }
}