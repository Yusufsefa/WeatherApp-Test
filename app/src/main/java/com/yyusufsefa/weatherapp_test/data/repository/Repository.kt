package com.yyusufsefa.weatherapp_test.data.repository

import androidx.lifecycle.MutableLiveData
import com.yyusufsefa.weatherapp_test.common.BaseRepository
import com.yyusufsefa.weatherapp_test.data.ApiService
import com.yyusufsefa.weatherapp_test.data.response.HeaderObject
import com.yyusufsefa.weatherapp_test.data.response.WeatherResponse
import com.yyusufsefa.weatherapp_test.util.Result

class Repository(
    private val apiservice: ApiService
) : BaseRepository() {

    val weatherResponse by lazy {
        MutableLiveData<Result<WeatherResponse>>()
    }

    val currentResponse by lazy {
        MutableLiveData<Result<HeaderObject>>()
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

    suspend fun getCurrentWeatherData(city: String): com.yyusufsefa.weatherapp_test.util.Result<HeaderObject> {
        return getResult {
            apiservice
                .getCurrentWeatherResponse(
                    API_VERSION,
                    city,
                    API_KEY
                )
        }
    }

    companion object {
        private const val API_VERSION = 2.5f
        private const val API_KEY = "YOUR_API_KEY"
    }

}