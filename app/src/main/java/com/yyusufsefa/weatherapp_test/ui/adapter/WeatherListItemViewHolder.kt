package com.yyusufsefa.weatherapp_test.ui.adapter

import android.annotation.SuppressLint
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.yyusufsefa.weatherapp_test.R
import com.yyusufsefa.weatherapp_test.data.response.ListObject
import kotlinx.android.synthetic.main.item_weather_list.view.*

class WeatherListItemViewHolder(
    private val parent: ViewGroup
) : RecyclerView.ViewHolder(
    LayoutInflater.from(parent.context).inflate(
        R.layout.item_weather_list,
        parent,
        false
    )
) {

    @SuppressLint("ResourceAsColor")
    fun bind(
        item: ListObject,
        onItemClickListiner: (ListObject, Int) -> Unit,
        position: Int
    ) {

        itemView.txtDay.text = item.getDay()
        Glide
            .with(itemView.context)
            .load(WeatherListItemViewHolder.imageBaseUrl + item.weather.first().icon + ".png")
            .centerCrop()
            .placeholder(ColorDrawable(Color.BLUE))
            .into(itemView.img_day_for_the_weather)

        itemView.rltv_item.setBackgroundColor(item.getColorForDay())

        itemView.txt_day_for_the_temp.text = item.main.getTemp()
        itemView.txt_maxTemp.text = item.main.getMaxTemp()
        itemView.txt_minTemp.text = item.main.getMinTemp()

        itemView.setOnClickListener {
            onItemClickListiner(item, position)
        }

    }

    companion object {
        const val imageBaseUrl = "http://openweathermap.org/img/wn/"
    }
}