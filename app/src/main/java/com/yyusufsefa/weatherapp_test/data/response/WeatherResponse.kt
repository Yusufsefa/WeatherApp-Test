package com.yyusufsefa.weatherapp_test.data.response

import androidx.room.ColumnInfo
import androidx.room.Entity

@Entity(tableName = "weatherResponse_table")
data class WeatherResponse(
    @ColumnInfo(name = "city")
    val city: City,

    @ColumnInfo(name = "cnt")
    val cnt: Int,

    @ColumnInfo(name = "cod")
    val cod: String,

    @ColumnInfo(name = "list")
    val list: List<ListObject>,

    @ColumnInfo(name = "message")
    val message: Int
)
