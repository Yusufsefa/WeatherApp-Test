package com.yyusufsefa.weatherapp_test.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.yyusufsefa.weatherapp_test.data.response.ListObject
import com.yyusufsefa.weatherapp_test.db.WeatherRoomDatabase
import com.yyusufsefa.weatherapp_test.db.repository.WeatherRepository

class DetailViewModel(
    private val weatherRepository: WeatherRepository,
    private val weatherRoomDatabase: WeatherRoomDatabase
) : ViewModel() {

    var allWeather = MutableLiveData<List<ListObject>>()

    fun getAllWeatherFromDatabase() {
        allWeather = liveData {
            emitSource(weatherRepository.allWeather)
        } as MutableLiveData<List<ListObject>>
    }


}