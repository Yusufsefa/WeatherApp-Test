package com.yyusufsefa.weatherapp_test.data.response

data class ListObject(
    val clouds: Clouds,
    val dt: Int,
    val dt_txt: String,
    val main: Main,
    val sys: Sys,
    val weather: List<Weather>,
    val wind: Wind,
    var cityName: String = "Empty"
) : BaseItem()