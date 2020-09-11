package com.yyusufsefa.weatherapp_test.data.response

import android.os.Parcelable
import androidx.room.Entity
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "weather_table")
data class Weather(
    @SerializedName("description")
    val description: String,

    @SerializedName("icon")
    val icon: String,

    @SerializedName("id")
    val id: Int,

    @SerializedName("main")
    val main: String
) : Parcelable