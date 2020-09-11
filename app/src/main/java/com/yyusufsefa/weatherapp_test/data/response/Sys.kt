package com.yyusufsefa.weatherapp_test.data.response

import android.os.Parcelable
import androidx.room.Entity
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "sys_table")
data class Sys(
    @SerializedName("pod")
    val pod: String
) : Parcelable