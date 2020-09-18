package com.yyusufsefa.weatherapp_test.ui.adapter

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.annotation.GlideModule
import com.yyusufsefa.weatherapp_test.R
import com.yyusufsefa.weatherapp_test.data.response.HeaderObject
import kotlinx.android.synthetic.main.item_weather_list_header.view.*

@GlideModule
class WeatherListHeaderViewHolder(parent: ViewGroup) : RecyclerView.ViewHolder(
    LayoutInflater.from(parent.context).inflate(
        R.layout.item_weather_list_header,
        parent,
        false
    )
) {

    fun bind(
        item: HeaderObject
    ) {
        itemView.txtDegree.text = item.main.getTemp()
        Glide
            .with(itemView.context)
            .load(imageBaseUrl + item.weather.first().icon + ".png")
            .centerCrop()
            .placeholder(ColorDrawable(Color.BLUE))
            .into(itemView.imgCurrentWeather)

        itemView.txtWeather.text = item.dt.toString()
    }

    companion object {
        const val imageBaseUrl = "http://openweathermap.org/img/wn/"
    }

}