package com.yyusufsefa.weatherapp_test.data.response

import androidx.room.ColumnInfo
import androidx.room.Entity

@Entity(tableName = "coord_table")
data class Coord(

    @ColumnInfo(name = "lat")
    val lat: Double?,

    @ColumnInfo(name = "lon")
    val lon: Double?
)