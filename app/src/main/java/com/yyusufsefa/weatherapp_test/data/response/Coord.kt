package com.yyusufsefa.weatherapp_test.data.response

import androidx.room.Entity
import com.google.gson.annotations.SerializedName

@Entity(tableName = "coord_table")
data class Coord(

    @SerializedName("lat")
    val lat: Double?,

    @SerializedName("lon")
    val lon: Double?,
)