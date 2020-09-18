package com.yyusufsefa.weatherapp_test.ui.viewmodel

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yyusufsefa.weatherapp_test.data.repository.Repository
import com.yyusufsefa.weatherapp_test.data.response.ListObject
import com.yyusufsefa.weatherapp_test.db.WeatherRoomDatabase
import com.yyusufsefa.weatherapp_test.db.repository.WeatherRepository
import com.yyusufsefa.weatherapp_test.util.Result
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.launch

@InternalCoroutinesApi
class HomeViewModel(
    private val context: Context,
    private val mainRepository: Repository,
) : ViewModel() {

    private val _weatherData = mainRepository.weatherResponse
    val weatherData get() = _weatherData

    private var weatherRepository: WeatherRepository

    var allWeather: LiveData<List<ListObject>>


    fun getWeatherData(city: String) {
        viewModelScope.launch {
            weatherData.postValue(Result.loading())
            mainRepository.fetchAllWeatherData(city).let { response ->
                response.data?.list?.map {
                    it.cityName = city
                }
                weatherData.postValue(response)

                viewModelScope.launch {
                    weatherRepository.insert(response.data?.list!!)
                }
            }
        }
    }

    fun getAllWeatherFromDatabase() {
        val database = WeatherRoomDatabase.getDatabase(context)
        val wetherDao = database.weatherDao()
        weatherRepository = WeatherRepository(wetherDao)
        allWeather = weatherRepository.allWeather
    }


}

