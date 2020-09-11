package com.yyusufsefa.weatherapp_test.common

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.yyusufsefa.weatherapp_test.data.repository.Repository
import com.yyusufsefa.weatherapp_test.ui.viewmodel.HomeViewModel

@Suppress("UNCHECKED_CAST")
class ViewModelFactory(private val repository: BaseRepository) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(HomeViewModel::class.java) -> {
                HomeViewModel(this.repository as Repository) as T
            }
            else -> throw IllegalArgumentException("ViewModel Not Found")
        }
    }
}