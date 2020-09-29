package com.yyusufsefa.weatherapp_test.ui.view

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.yyusufsefa.weatherapp_test.R
import com.yyusufsefa.weatherapp_test.common.ViewModelFactory
import com.yyusufsefa.weatherapp_test.data.WeatherClient
import com.yyusufsefa.weatherapp_test.data.repository.Repository
import com.yyusufsefa.weatherapp_test.databinding.ItemWeatherListHeaderBinding
import com.yyusufsefa.weatherapp_test.db.WeatherRoomDatabase
import com.yyusufsefa.weatherapp_test.db.repository.WeatherRepository
import com.yyusufsefa.weatherapp_test.ui.adapter.WeatherListAdapter
import com.yyusufsefa.weatherapp_test.ui.viewmodel.HomeViewModel
import com.yyusufsefa.weatherapp_test.util.Result
import com.yyusufsefa.weatherapp_test.util.hide
import com.yyusufsefa.weatherapp_test.util.show
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.item_weather_list_header.*
import kotlinx.coroutines.InternalCoroutinesApi


class HomeFragment : Fragment(R.layout.fragment_home) {

    @InternalCoroutinesApi
    private val viewModel by lazy {
        ViewModelProvider(
            this,
            ViewModelFactory(
                WeatherRoomDatabase.getDatabase(requireContext()),
                WeatherRepository(WeatherRoomDatabase.getDatabase(requireContext()).weatherDao()),
                Repository(WeatherClient.getProjectService())
            )
        ).get(HomeViewModel::class.java)
    }

    private lateinit var adapter: WeatherListAdapter
    private lateinit var binding: ItemWeatherListHeaderBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding =
            DataBindingUtil.inflate(inflater, R.layout.item_weather_list_header, container, false)
        return binding.root
    }

    @InternalCoroutinesApi
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.getWeatherData("trabzon")
        initUI()
        viewModel.deleteAll()
        initObservers()

    }

    private fun initUI() {
//        prgLoadingBar.hide()
        adapter = WeatherListAdapter(listOf()) { model, position ->
            val action =
                HomeFragmentDirections.actionHomeFragmentToDetailWeatherFragment(model)
            findNavController().navigate(action)
        }
        recycWeather.adapter = adapter
    }

    @InternalCoroutinesApi
    @RequiresApi(Build.VERSION_CODES.O)
    private fun initObservers() {
        container.visibility = View.GONE
        viewModel.deleteAll()
        viewModel.weatherData.observe(viewLifecycleOwner, Observer { result ->
            when (result.status) {
                Result.Status.SUCCESS -> {
                    txt_view_header.text = "Next 5 Days/Hourly"
                    prgLoadingBar.hide()
                    adapter.setNewList(result.data!!.distinctBy { it.getDate() })
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
        viewModel.getCurrentWeatherData("trabzon")
        viewModel.currentWeatherData.observe(viewLifecycleOwner, { result ->
            when (result.status) {
                Result.Status.SUCCESS -> {
                    prgLoadingBar.hide()
                    container.visibility = View.VISIBLE
                    binding.header = result.data
//                    txtDegree.text = result.data!!.main.getTemp()
//                    txtWeather.text = result.data.weather.first().main
//                    Glide
//                        .with(requireContext())
//                        .load(WeatherListItemViewHolder.imageBaseUrl + result.data.weather.first().icon + ".png")
//                        .centerCrop()
//                        .placeholder(ColorDrawable(Color.BLUE))
//                        .into(imgCurrentWeather)
//                    txtHumidity.text = result.data.main.getHumidity()
//                    txtWind.text = result.data.wind.getWind()


                    cardHeader.setCardBackgroundColor(result.data!!.getColorForDay())
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

