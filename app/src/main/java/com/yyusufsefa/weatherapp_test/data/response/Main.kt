package com.yyusufsefa.weatherapp_test.data.response

import android.os.Parcelable
import androidx.room.Entity
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "main_table")
data class Main(
    @SerializedName("feels_like")
    val feels_like: Double,

    @SerializedName("grnd_level")
    val grnd_level: Int,

    @SerializedName("humidity")
    val humidity: Int,

    @SerializedName("pressure")
    val pressure: Int,

    @SerializedName("sea_level")
    val sea_level: Int,

    @SerializedName("temp")
    val temp: Double,

    @SerializedName("temp_kf")
    val temp_kf: Double,

    @SerializedName("temp_max")
    val temp_max: Double,

    @SerializedName("temp_min")
    val temp_min: Double
) : Parcelable {
    fun getTemp(): String {
        return (temp - 273.15f).toString().substringBefore(".") + "°"
    }

    fun getMaxTemp(): String {
        return (temp_max - 273.15f).toString().substringBefore(".") + "°"
    }

    fun getMinTemp(): String {
        return (temp_min - 273.15f).toString().substringBefore(".") + "°"
    }
}