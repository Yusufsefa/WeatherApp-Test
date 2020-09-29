package com.yyusufsefa.weatherapp_test.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yyusufsefa.weatherapp_test.data.repository.Repository
import com.yyusufsefa.weatherapp_test.data.response.HeaderObject
import com.yyusufsefa.weatherapp_test.data.response.ListObject
import com.yyusufsefa.weatherapp_test.db.WeatherRoomDatabase
import com.yyusufsefa.weatherapp_test.db.repository.WeatherRepository
import com.yyusufsefa.weatherapp_test.util.Result
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.launch

@InternalCoroutinesApi
class HomeViewModel(
    private val mainRepository: Repository,
    private val weatherRoomDatabase: WeatherRoomDatabase,
    private val weatherRepository: WeatherRepository
) : ViewModel() {

    private val _weatherData = MutableLiveData<Result<List<ListObject>>>()
    val weatherData get() = _weatherData

    private val _currentData = MutableLiveData<Result<HeaderObject>>()
    val currentWeatherData get() = _currentData

    fun getWeatherData(city: String) {
        viewModelScope.launch {
            weatherData.postValue(Result.loading())
            mainRepository.fetchAllWeatherData(city).let { response ->
                response.data?.list?.map {
                    it.cityName = city
                }
                weatherData.postValue(Result.success(response.data?.list.orEmpty()))
                weatherRepository.insert(response.data?.list.orEmpty())
            }
        }
    }

    fun deleteAll() {
        viewModelScope.launch {
            weatherRepository.deleteAll()
        }
    }

    fun getCurrentWeatherData(city: String) {
        viewModelScope.launch {
            currentWeatherData.postValue(Result.loading())
            mainRepository.getCurrentWeatherData(city).let { response ->
                currentWeatherData.postValue(Result.success(response.data!!))
            }
        }
    }

}




