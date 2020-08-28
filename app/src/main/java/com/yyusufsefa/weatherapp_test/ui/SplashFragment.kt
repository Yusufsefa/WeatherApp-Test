package com.yyusufsefa.weatherapp_test.ui

import android.os.Bundle
import android.os.CountDownTimer
import android.view.View
import androidx.fragment.app.Fragment
import com.yyusufsefa.weatherapp_test.R
import com.yyusufsefa.weatherapp_test.navigation.NavigationType
import com.yyusufsefa.weatherapp_test.navigation.openHomeFragment
import com.yyusufsefa.weatherapp_test.util.changeActionbarVisibility

class SplashFragment : Fragment(R.layout.fragment_splash) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        requireActivity() changeActionbarVisibility false

        object : CountDownTimer(3000, 1000) {
            override fun onTick(p0: Long) {
            }

            override fun onFinish() {
                this@SplashFragment openHomeFragment NavigationType.SplashToHomeFragment
            }

        }.start()

    }


}