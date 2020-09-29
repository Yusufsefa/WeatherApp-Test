package com.yyusufsefa.weatherapp_test.ui.view

import android.Manifest
import android.annotation.SuppressLint
import android.content.pm.PackageManager
import android.location.Address
import android.location.Geocoder
import android.os.Build
import android.os.Bundle
import android.os.Looper
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.google.android.gms.location.*
import com.yyusufsefa.weatherapp_test.R
import com.yyusufsefa.weatherapp_test.common.ViewModelFactory
import com.yyusufsefa.weatherapp_test.data.WeatherClient
import com.yyusufsefa.weatherapp_test.data.repository.Repository
import com.yyusufsefa.weatherapp_test.databinding.FragmentHomeBinding
import com.yyusufsefa.weatherapp_test.db.WeatherRoomDatabase
import com.yyusufsefa.weatherapp_test.db.repository.WeatherRepository
import com.yyusufsefa.weatherapp_test.ui.adapter.WeatherListAdapter
import com.yyusufsefa.weatherapp_test.ui.viewmodel.HomeViewModel
import com.yyusufsefa.weatherapp_test.util.Result
import com.yyusufsefa.weatherapp_test.util.hide
import com.yyusufsefa.weatherapp_test.util.show
import kotlinx.coroutines.InternalCoroutinesApi
import java.util.*


class HomeFragment : Fragment() {

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
    private lateinit var binding: FragmentHomeBinding

    private lateinit var fusedLocationClient: FusedLocationProviderClient

    private val headerItemBinding
        get() = binding.container

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? = DataBindingUtil.inflate<FragmentHomeBinding>(
        inflater,
        R.layout.fragment_home,
        container,
        false
    ).also {
        binding = it
    }.root

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(requireActivity())
    }

    @InternalCoroutinesApi
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (checkPermission()) {
            getCurrentLocation()
        } else {
            requestPermission()
        }
        initUI()
        viewModel.deleteAll()
        initObservers()
    }

    private fun initUI() {
        binding.prgLoadingBar.hide()
        adapter = WeatherListAdapter(listOf()) { model, position ->
            val action =
                HomeFragmentDirections.actionHomeFragmentToDetailWeatherFragment(model)
            findNavController().navigate(action)
        }
        binding.recycWeather.adapter = adapter
    }

    @InternalCoroutinesApi
    @RequiresApi(Build.VERSION_CODES.O)
    private fun initObservers() {
        headerItemBinding.root.visibility = View.GONE
        viewModel.weatherData.observe(viewLifecycleOwner, Observer { result ->
            when (result.status) {
                Result.Status.SUCCESS -> {
                    binding.txtViewHeader.text = "Next 5 Days/Hourly"
                    binding.prgLoadingBar.hide()
                    adapter.setNewList(result.data.orEmpty().distinctBy { it.getDate() })
                }
                Result.Status.ERROR -> {
                    binding.prgLoadingBar.hide()
                    Toast.makeText(requireContext(), result.message, Toast.LENGTH_SHORT).show()
                }
                Result.Status.LOADING -> {
                    binding.prgLoadingBar.show()
                }
            }

        })
        viewModel.getCurrentWeatherData("trabzon")
        viewModel.currentWeatherData.observe(viewLifecycleOwner, { result ->
            when (result.status) {
                Result.Status.SUCCESS -> {
                    binding.prgLoadingBar.hide()
                    headerItemBinding.root.visibility = View.VISIBLE
                    headerItemBinding.header = result.data
                }
                Result.Status.ERROR -> {
                    binding.prgLoadingBar.hide()
                    Toast.makeText(requireContext(), result.message, Toast.LENGTH_SHORT).show()
                }
                Result.Status.LOADING -> {
                    binding.prgLoadingBar.show()
                }
            }

        })
    }


    private fun checkPermission(): Boolean {
        return !(ContextCompat.checkSelfPermission(
            requireContext(),
            Manifest.permission.ACCESS_FINE_LOCATION
        ) != PackageManager.PERMISSION_GRANTED &&
                ContextCompat.checkSelfPermission(
                    requireContext(),
                    Manifest.permission.ACCESS_COARSE_LOCATION
                ) != PackageManager.PERMISSION_GRANTED)
    }

    private fun requestPermission() {
        requestPermissions(
            arrayOf(
                Manifest.permission.ACCESS_COARSE_LOCATION,
                Manifest.permission.ACCESS_FINE_LOCATION
            ), LOCATION_REQUEST_CODE
        )
    }

    @InternalCoroutinesApi
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        when (requestCode) {
            LOCATION_REQUEST_CODE -> {
                if (
                    (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED)
                ) {
                    getCurrentLocation()
                } else {
                    Toast.makeText(requireContext(), "Lutfen izin verin", Toast.LENGTH_SHORT).show()
                    requestPermission()
                }
                return
            }
            else -> {
                Log.e("Perm Result : ", "Else Case")
            }
        }
    }

    @SuppressLint("MissingPermission")
    @InternalCoroutinesApi
    private fun getCurrentLocation() {
        fusedLocationClient.lastLocation.addOnCompleteListener { location ->
            try {
                if (location.result == null) {
                    requestNewLocation()
                } else {
                    Log.e("Location Data  : ", location.result.toString())
                    val city = getCity(location.result?.latitude!!, location.result?.longitude!!)
                    viewModel.getWeatherData(city)
                    viewModel.getCurrentWeatherData(city)
                }
            } catch (e: Exception) {
                Log.e("Exception : ", e.localizedMessage ?: "Empty")
            }
        }
    }

    private fun getCity(lat: Double, lng: Double): String {
        val geocoder = Geocoder(requireContext(), Locale.getDefault())
        val addresses: List<Address> = geocoder.getFromLocation(lat, lng, 1)
        return addresses.first().adminArea
    }

    @InternalCoroutinesApi
    @SuppressLint("MissingPermission")
    private fun requestNewLocation() {
        val locationRequest = LocationRequest()
        locationRequest.priority = LocationRequest.PRIORITY_HIGH_ACCURACY
        locationRequest.interval = 5000
        locationRequest.fastestInterval = 2000
        locationRequest.numUpdates = 1
        fusedLocationClient.requestLocationUpdates(
            locationRequest, locationCallback, Looper.myLooper()
        )
    }

    @InternalCoroutinesApi
    private val locationCallback = object : LocationCallback() {
        override fun onLocationResult(location: LocationResult?) {
            location?.let {
                Log.e("Location Data  : ", it.lastLocation.toString())
                val city = getCity(it.lastLocation.latitude, it.lastLocation.longitude)
                viewModel.getWeatherData(city)
                viewModel.getCurrentWeatherData(city)
            }
        }
    }

    companion object {
        private val LOCATION_REQUEST_CODE = 1
    }

}

