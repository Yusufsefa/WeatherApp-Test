package com.yyusufsefa.weatherapp_test.data.response

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "weather_table")
data class Weather(
    @ColumnInfo(name = "description")
    val description: String,

    @ColumnInfo(name = "icon")
    val icon: String,

    @ColumnInfo(name = "id")
    val id: Int,

    @ColumnInfo(name = "main")
    val main: String
) : Parcelable