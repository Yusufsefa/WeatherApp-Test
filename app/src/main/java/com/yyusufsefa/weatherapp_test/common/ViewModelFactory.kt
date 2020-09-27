package com.yyusufsefa.weatherapp_test.common

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.yyusufsefa.weatherapp_test.data.repository.Repository
import com.yyusufsefa.weatherapp_test.db.WeatherRoomDatabase
import com.yyusufsefa.weatherapp_test.db.repository.WeatherRepository
import com.yyusufsefa.weatherapp_test.ui.viewmodel.DetailViewModel
import com.yyusufsefa.weatherapp_test.ui.viewmodel.HomeViewModel
import kotlinx.coroutines.InternalCoroutinesApi

@Suppress("UNCHECKED_CAST")
class ViewModelFactory(
    private val weatherRoomDatabase: WeatherRoomDatabase,
    private val weatherRepository: WeatherRepository,
    private val repository: BaseRepository? = null
) : ViewModelProvider.Factory {
    @InternalCoroutinesApi
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(
                HomeViewModel::class.java
            ) -> {
                HomeViewModel(
                    repository as Repository,
                    weatherRoomDatabase,
                    weatherRepository
                ) as T
            }
            modelClass.isAssignableFrom(
                DetailViewModel::class.java
            ) -> {
                DetailViewModel(
                    weatherRepository,
                    weatherRoomDatabase
                ) as T
            }

            else -> throw IllegalArgumentException("ViewModel Not Found")
        }
    }
}