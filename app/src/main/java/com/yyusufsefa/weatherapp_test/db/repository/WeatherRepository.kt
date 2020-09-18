package com.yyusufsefa.weatherapp_test.db.repository

import com.yyusufsefa.weatherapp_test.data.response.ListObject
import com.yyusufsefa.weatherapp_test.db.dao.WeatherDao

class WeatherRepository(private val weatherDao: WeatherDao) {

    val allWeather = weatherDao.getAllWeather()

    suspend fun insert(listObject: List<ListObject>) {
        weatherDao.insert(listObject)
    }

}