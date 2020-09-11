package com.yyusufsefa.weatherapp_test.data.response

import androidx.room.Entity
import com.google.gson.annotations.SerializedName

/**
 * Bu tip api'lar gereksiz data'larda dönebiliyor, gereksiz dataları cekmemeni öneririm.
 * DTO classların fazla şişiyor ve bunlar memoryde yer kaplıyor
 */
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
