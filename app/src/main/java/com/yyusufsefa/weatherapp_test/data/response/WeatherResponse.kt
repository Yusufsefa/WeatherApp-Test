package com.yyusufsefa.weatherapp_test.data.response

data class WeatherResponse(
    val city: City,
    val cnt: Int,
    val cod: String,
    val list: List<ListObject>,
    val message: Int
)