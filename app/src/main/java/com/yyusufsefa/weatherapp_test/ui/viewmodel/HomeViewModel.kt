package com.yyusufsefa.weatherapp_test.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yyusufsefa.weatherapp_test.data.repository.Repository
import com.yyusufsefa.weatherapp_test.util.Result
import kotlinx.coroutines.launch

class HomeViewModel(
    private val mainRepository: Repository
) : ViewModel() {

    private val _weatherData = mainRepository.weatherResponse
    val weatherData get() = _weatherData

    fun getWeatherData(city: String) {
        viewModelScope.launch {
            weatherData.postValue(Result.loading())
            mainRepository.fetchAllWeatherData(city).let { response ->
                response.data?.list?.map {
                    it.cityName = city
                }
                weatherData.postValue(response)
            }
        }
    }
}

