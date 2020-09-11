package com.yyusufsefa.weatherapp_test.data.response

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Clouds(
    val all: Int
) : Parcelable