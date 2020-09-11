package com.yyusufsefa.weatherapp_test.data.response

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "headerObject_table")
data class HeaderObject(
    // Room'da kaydettigin her DTO'nun @PrimaryKey'i olmak zorunda
    @PrimaryKey(autoGenerate = true) val id: Int = 0,

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
