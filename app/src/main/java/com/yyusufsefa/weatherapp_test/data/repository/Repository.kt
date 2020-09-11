package com.yyusufsefa.weatherapp_test.data.repository

import androidx.lifecycle.MutableLiveData
import com.yyusufsefa.weatherapp_test.common.BaseRepository
import com.yyusufsefa.weatherapp_test.data.ApiService
import com.yyusufsefa.weatherapp_test.data.response.WeatherResponse
import com.yyusufsefa.weatherapp_test.util.Result

class Repository(
    private val apiservice: ApiService
) : BaseRepository() {
    val weatherResponse by lazy {
        MutableLiveData<Result<WeatherResponse>>()
    }

    suspend fun fetchAllWeatherData(city: String): com.yyusufsefa.weatherapp_test.util.Result<WeatherResponse> {
        return getResult {
            apiservice
                .getWeatherResponse(
                    API_VERSION,
                    city,
                    API_KEY
                )
        }
    }

    companion object {
        private const val API_VERSION = 2.5f
        private const val API_KEY = "24e2d314f8aef1c2d752bb1c0a83a06d"
    }

}