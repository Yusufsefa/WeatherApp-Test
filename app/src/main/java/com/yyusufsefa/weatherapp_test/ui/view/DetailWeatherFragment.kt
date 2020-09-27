package com.yyusufsefa.weatherapp_test.ui.view

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.yyusufsefa.weatherapp_test.R
import com.yyusufsefa.weatherapp_test.adapter.weatherDetail.WeatherDayForHourAdapter
import com.yyusufsefa.weatherapp_test.common.ViewModelFactory
import com.yyusufsefa.weatherapp_test.data.response.ListObject
import com.yyusufsefa.weatherapp_test.db.WeatherRoomDatabase
import com.yyusufsefa.weatherapp_test.db.repository.WeatherRepository
import com.yyusufsefa.weatherapp_test.navigation.NavigationType
import com.yyusufsefa.weatherapp_test.navigation.openDetailToHomeFragment
import com.yyusufsefa.weatherapp_test.ui.adapter.weatherDetail.WeatherDayForHourViewHolder
import com.yyusufsefa.weatherapp_test.ui.viewmodel.DetailViewModel
import kotlinx.android.synthetic.main.fragment_detail_weather.*
import kotlinx.coroutines.InternalCoroutinesApi

class DetailWeatherFragment : BottomSheetDialogFragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View =
        inflater.inflate(R.layout.fragment_detail_weather, container, false)


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
        recycWeatherforHour.adapter = adapter

        rltvDetail.setBackgroundColor(listObject.getColorForDay())

        txt_detail_day.text = listObject.getDay()
        Glide
            .with(requireContext())
            .load(WeatherDayForHourViewHolder.imageBaseUrl + listObject.weather.first().icon + ".png")
            .centerCrop()
            .placeholder(ColorDrawable(Color.BLUE))
            .into(img_detail_weather)

        txt_detail_temp.text = listObject.main.getTemp()
        txt_detail_minTemp.text = listObject.main.getMinTemp()
        txt_detail_maxTemp.text = listObject.main.getMaxTemp()
    }

    @InternalCoroutinesApi
    private fun initObserver() {
        viewModel.getAllWeatherFromDatabase()
        viewModel.allWeather.observe(viewLifecycleOwner, {
            adapter.setNewList(it.filter { it.getDay() == listObject.getDay() })
        })
    }


}