package com.yyusufsefa.weatherapp_test.ui.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.yyusufsefa.weatherapp_test.R
import com.yyusufsefa.weatherapp_test.common.ViewModelFactory
import com.yyusufsefa.weatherapp_test.data.response.ListObject
import com.yyusufsefa.weatherapp_test.databinding.FragmentDetailWeatherBinding
import com.yyusufsefa.weatherapp_test.db.WeatherRoomDatabase
import com.yyusufsefa.weatherapp_test.db.repository.WeatherRepository
import com.yyusufsefa.weatherapp_test.navigation.NavigationType
import com.yyusufsefa.weatherapp_test.navigation.openDetailToHomeFragment
import com.yyusufsefa.weatherapp_test.ui.adapter.WeatherDayForHourAdapter
import com.yyusufsefa.weatherapp_test.ui.viewmodel.DetailViewModel
import kotlinx.android.synthetic.main.fragment_detail_weather.*
import kotlinx.coroutines.InternalCoroutinesApi

class DetailWeatherFragment : BottomSheetDialogFragment() {

    private lateinit var binding: FragmentDetailWeatherBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = DataBindingUtil.inflate<FragmentDetailWeatherBinding>(
        inflater, R.layout.fragment_detail_weather, container, false
    ).also { binding = it }.root

    @InternalCoroutinesApi
    private val viewModel by lazy {
        ViewModelProvider(
            this,
            ViewModelFactory(
                WeatherRoomDatabase.getDatabase(requireContext()),
                WeatherRepository(WeatherRoomDatabase.getDatabase(requireContext()).weatherDao())
            )
        ).get(DetailViewModel::class.java)
    }

    private lateinit var adapter: WeatherDayForHourAdapter
    private lateinit var listObject: ListObject

    @InternalCoroutinesApi
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        arguments.let {
            listObject = DetailWeatherFragmentArgs.fromBundle(it!!).listObject
        }

        closeButton.setOnClickListener {
            this@DetailWeatherFragment openDetailToHomeFragment NavigationType.DetailToHomeFragment
        }

        initUI(listObject)
        initObserver()
    }

    private fun initUI(listObject: ListObject) {

        adapter = WeatherDayForHourAdapter(listOf())
        binding.recycWeatherforHour.adapter = adapter

        binding.detailweather = listObject
    }

    @InternalCoroutinesApi
    private fun initObserver() {
        viewModel.getAllWeatherFromDatabase()
        viewModel.allWeather.observe(viewLifecycleOwner, {
            adapter.setNewList(it.filter { it.getDay() == listObject.getDay() })
        })
    }

}