package com.yyusufsefa.weatherapp_test.data.response

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "headerObject_table")
data class HeaderObject(
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "id") val _id: Int,

    @ColumnInfo(name = "clouds")
    val clouds: Clouds,

    @ColumnInfo(name = "dt")
    val dt: Long,

    @ColumnInfo(name = "dt_txt")
    val dt_txt: String,

    @ColumnInfo(name = "main")
    val main: Main,

    @ColumnInfo(name = "sys")
    val sys: Sys,

    @ColumnInfo(name = "weather")
    val weather: List<Weather>,

    @ColumnInfo(name = "wind")
    val wind: Wind
) : BaseItem()
