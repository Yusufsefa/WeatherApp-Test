package com.yyusufsefa.weatherapp_test.ui.view

import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.snackbar.Snackbar
import com.yyusufsefa.weatherapp_test.R
import com.yyusufsefa.weatherapp_test.adapter.WeatherListAdapter
import com.yyusufsefa.weatherapp_test.common.ViewModelFactory
import com.yyusufsefa.weatherapp_test.data.WeatherClient
import com.yyusufsefa.weatherapp_test.data.repository.Repository
import com.yyusufsefa.weatherapp_test.navigation.NavigationType
import com.yyusufsefa.weatherapp_test.navigation.openDetailFragment
import com.yyusufsefa.weatherapp_test.ui.viewmodel.HomeViewModel
import com.yyusufsefa.weatherapp_test.util.Result
import com.yyusufsefa.weatherapp_test.util.hide
import com.yyusufsefa.weatherapp_test.util.show
import kotlinx.android.synthetic.main.fragment_home.*


class HomeFragment : Fragment(R.layout.fragment_home) {

    private val viewModel by lazy {
        ViewModelProvider(
            this,
            ViewModelFactory(Repository(WeatherClient.getProjectService()))
        ).get(HomeViewModel::class.java)
    }
    private lateinit var adapter: WeatherListAdapter

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.getWeatherData("Trabzon")
        initUI()
        initObservers()
    }

    private fun initUI() {
        prgLoadingBar.hide()

        adapter = WeatherListAdapter(listOf()) { model, position ->

            this@HomeFragment openDetailFragment NavigationType.HomeToDetailFragment

            Snackbar.make(requireView(), model.getDay().toString(), Snackbar.LENGTH_SHORT).show()
        }

        recycWeather.adapter = adapter

    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun initObservers() {
        viewModel.weatherData.observe(viewLifecycleOwner, Observer { result ->
            when (result.status) {
                Result.Status.SUCCESS -> {
                    prgLoadingBar.hide()
                    adapter.setNewList(result.data!!.list.distinctBy { it.getDate() })
                    Log.e("Data Size", result.data.list.size.toString())
                }
                Result.Status.ERROR -> {
                    prgLoadingBar.hide()
                    Toast.makeText(requireContext(), result.message, Toast.LENGTH_SHORT).show()
                }
                Result.Status.LOADING -> {
                    prgLoadingBar.show()
                }
            }
        })
    }
}