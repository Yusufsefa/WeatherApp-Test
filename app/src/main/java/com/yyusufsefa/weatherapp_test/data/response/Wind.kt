package com.yyusufsefa.weatherapp_test.data.response

import android.os.Parcelable
import androidx.room.Entity
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "wind_table")
data class Wind(
    @SerializedName("deg")
    val deg: Int,

    @SerializedName("speed")
    val speed: Double
) : Parcelable
