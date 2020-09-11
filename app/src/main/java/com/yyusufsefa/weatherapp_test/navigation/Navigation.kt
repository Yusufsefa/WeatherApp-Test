package com.yyusufsefa.weatherapp_test.navigation

import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.yyusufsefa.weatherapp_test.R

infix fun Fragment.openHomeFragment(navigationType: NavigationType) {
    when (navigationType) {
        NavigationType.SplashToHomeFragment -> {
            findNavController().navigate(R.id.action_splashFragment_to_homeFragment)
        }
        else -> {
            throw Exception("Wrong NavigationType: $navigationType")
        }
    }
}


infix fun Fragment.openDetailFragment(navigationType: NavigationType) {
    when (navigationType) {
        NavigationType.HomeToDetailFragment -> {
            findNavController().navigate(R.id.action_homeFragment_to_detailWeatherFragment)
        }
        else -> {
            throw Exception("Wrong NavigationType: $navigationType")
        }
    }
}