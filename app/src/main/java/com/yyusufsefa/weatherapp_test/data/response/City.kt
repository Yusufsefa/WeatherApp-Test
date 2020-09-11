package com.yyusufsefa.weatherapp_test.data.response

import androidx.room.Entity
import com.google.gson.annotations.SerializedName

@Entity(tableName = "city_table")
data class City(

    @SerializedName("coord")
    val coord: Coord?,

    @SerializedName("country")
    val country: String?,

    @SerializedName("id")
    val id: Int?,

    @SerializedName("name")
    val name: String?,

    @SerializedName("population")
    val population: Int?,

    @SerializedName("sunrise")
    val sunrise: Int?,

    @SerializedName("sunset")
    val sunset: Int?,

    @SerializedName("timezone")
    val timezone: Int?,
)