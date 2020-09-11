package com.yyusufsefa.weatherapp_test.data.response

import androidx.room.Entity
import com.google.gson.annotations.SerializedName

@Entity(tableName = "weatherResponse_table")
data class WeatherResponse(
    @SerializedName("city")
    val city: City,

    @SerializedName("cnt")
    val cnt: Int,

    @SerializedName("cod")
    val cod: String,

    @SerializedName("list")
    val list: List<ListObject>,

    @SerializedName("message")
    val message: Int
)