package com.yyusufsefa.weatherapp_test.data.response

import androidx.room.ColumnInfo
import androidx.room.Entity

@Entity(tableName = "city_table")
data class City(

    @ColumnInfo(name = "coord")
    val coord: Coord?,

    @ColumnInfo(name = "country")
    val country: String?,

    @ColumnInfo(name = "id")
    val id: Int?,

    @ColumnInfo(name = "name")
    val name: String?,

    @ColumnInfo(name = "population")
    val population: Int?,

    @ColumnInfo(name = "sunrise")
    val sunrise: Int?,

    @ColumnInfo(name = "sunset")
    val sunset: Int?,

    @ColumnInfo(name = "timezone")
    val timezone: Int?
)