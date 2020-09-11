package com.yyusufsefa.weatherapp_test.data.response

import androidx.room.Entity
import com.google.gson.annotations.SerializedName

@Entity(tableName = "headerObject_table")
data class HeaderObject(
    @SerializedName("clouds")
    val clouds: Clouds,

    @SerializedName("dt")
    val dt: Long,

    @SerializedName("dt_txt")
    val dt_txt: String,

    @SerializedName("main")
    val main: Main,

    @SerializedName("sys")
    val sys: Sys,

    @SerializedName("weather")
    val weather: List<Weather>,

    @SerializedName("wind")
    val wind: Wind
) : BaseItem()