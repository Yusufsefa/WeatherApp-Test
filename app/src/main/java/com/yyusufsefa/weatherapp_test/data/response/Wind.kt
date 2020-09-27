package com.yyusufsefa.weatherapp_test.data.response

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "wind_table")
data class Wind(
    @ColumnInfo(name = "deg")
    val deg: Int,

    @ColumnInfo(name = "speed")
    val speed: Double

) : Parcelable {
    fun getWind(): String {
        return "Wind: " + speed + " km/hr"
    }
}
