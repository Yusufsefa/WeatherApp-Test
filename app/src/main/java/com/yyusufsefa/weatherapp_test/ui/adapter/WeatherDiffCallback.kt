package com.yyusufsefa.weatherapp_test.ui.adapter

import androidx.recyclerview.widget.DiffUtil
import com.yyusufsefa.weatherapp_test.data.response.ListObject

class WeatherDiffCallback : DiffUtil.ItemCallback<ListObject>() {
    override fun areItemsTheSame(oldItem: ListObject, newItem: ListObject): Boolean {
        return oldItem.dt_txt == newItem.dt_txt
    }

    override fun areContentsTheSame(oldItem: ListObject, newItem: ListObject): Boolean {
        return oldItem == newItem
    }

}