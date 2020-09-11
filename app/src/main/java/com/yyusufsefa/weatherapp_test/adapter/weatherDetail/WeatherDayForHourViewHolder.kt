package com.yyusufsefa.weatherapp_test.adapter.weatherDetail

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.yyusufsefa.weatherapp_test.R
import com.yyusufsefa.weatherapp_test.data.response.ListObject

/**
 * ViewHolder'da synthetic kullanımı leak'e yol açıyor. bunun aşagıdaki kullanımını tavisye ederim.
 */
class WeatherDayForHourViewHolder(parent: ViewGroup) : RecyclerView.ViewHolder(
    LayoutInflater.from(parent.context).inflate(
        R.layout.item_hour_for_weather,
        parent,
        false
    )
) {

    private val dayForTheTemp = itemView.findViewById<TextView>(R.id.txt_day_for_the_temp)
    private val itemHour = itemView.findViewById<TextView>(R.id.txt_item_hour)
    private val dayForTheWeather = itemView.findViewById<ImageView>(R.id.img_day_for_the_weather)

    fun bind(item: ListObject) {
        itemHour.text = item.getHour()
        Glide
            .with(itemView.context)
            .load(WeatherDayForHourViewHolder.imageBaseUrl + item.weather.first().icon + ".png")
            .centerCrop()
            .placeholder(ColorDrawable(Color.BLUE))
            .into(dayForTheWeather)
        dayForTheTemp.text = item.main.getTemp()

    }

    companion object {
        const val imageBaseUrl = "http://openweathermap.org/img/wn/"
    }

}
