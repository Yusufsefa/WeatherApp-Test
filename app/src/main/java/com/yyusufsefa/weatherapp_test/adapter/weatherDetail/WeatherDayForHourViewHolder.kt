package com.yyusufsefa.weatherapp_test.adapter.weatherDetail

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.yyusufsefa.weatherapp_test.R
import com.yyusufsefa.weatherapp_test.data.response.ListObject
import kotlinx.android.synthetic.main.item_hour_for_weather.view.*
import kotlinx.android.synthetic.main.item_weather_list.view.*

class WeatherDayForHourViewHolder(parent: ViewGroup) : RecyclerView.ViewHolder(
    LayoutInflater.from(parent.context).inflate(
        R.layout.item_hour_for_weather,
        parent,
        false
    )
) {

    fun bind(item: ListObject) {
        itemView.txt_item_hour.text = item.getHour()
        Glide
            .with(itemView.context)
            .load(WeatherDayForHourViewHolder.imageBaseUrl + item.weather.first().icon + ".png")
            .centerCrop()
            .placeholder(ColorDrawable(Color.BLUE))
            .into(itemView.img_day_for_the_weather)
        itemView.txt_day_for_the_temp.text = item.main.getTemp()

    }

    companion object {
        const val imageBaseUrl = "http://openweathermap.org/img/wn/"
    }

}