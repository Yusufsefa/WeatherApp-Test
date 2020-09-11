package com.yyusufsefa.weatherapp_test.data

import com.yyusufsefa.weatherapp_test.data.response.WeatherResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    @GET("/data/{version}/forecast")
    suspend fun getWeatherResponse(
        @Path("version") version: Float,
        @Query("q") location: String,
        @Query("appid") apiKey: String
    ): Response<WeatherResponse>
}