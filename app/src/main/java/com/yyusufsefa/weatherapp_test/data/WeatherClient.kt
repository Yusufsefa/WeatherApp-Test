package com.yyusufsefa.weatherapp_test.data

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object WeatherClient {
    private val BASE_URL = "https://api.openweathermap.org"

    private val retrofit = Retrofit
        .Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl(BASE_URL)
        .build()

    fun getProjectService(): ApiService = retrofit.create(ApiService::class.java)
}