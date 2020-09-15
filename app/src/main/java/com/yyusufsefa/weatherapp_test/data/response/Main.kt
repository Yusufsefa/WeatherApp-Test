package com.yyusufsefa.weatherapp_test.data.response

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "main_table")
data class Main(
    @ColumnInfo(name = "feels_like")
    val feels_like: Double,

    @ColumnInfo(name = "grnd_level")
    val grnd_level: Int,

    @ColumnInfo(name = "humidity")
    val humidity: Int,

    @ColumnInfo(name = "pressure")
    val pressure: Int,

    @ColumnInfo(name = "sea_level")
    val sea_level: Int,

    @ColumnInfo(name = "temp")
    val temp: Double,

    @ColumnInfo(name = "temp_kf")
    val temp_kf: Double,

    @ColumnInfo(name = "temp_max")
    val temp_max: Double,

    @ColumnInfo(name = "temp_min")
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