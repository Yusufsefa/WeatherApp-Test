package com.yyusufsefa.weatherapp_test.ui.view

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.yyusufsefa.weatherapp_test.R
import com.yyusufsefa.weatherapp_test.data.response.ListObject

class DetailWeatherFragment : Fragment(R.layout.fragment_detail_weather) {

    private lateinit var listObject: ListObject

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        arguments?.let {
            listObject = DetailWeatherFragmentArgs.fromBundle(it).listObject
        }

    }
}