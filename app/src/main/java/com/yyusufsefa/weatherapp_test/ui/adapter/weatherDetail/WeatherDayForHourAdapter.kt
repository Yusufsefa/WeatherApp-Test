package com.yyusufsefa.weatherapp_test.adapter.weatherDetail

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.yyusufsefa.weatherapp_test.data.response.ListObject
import com.yyusufsefa.weatherapp_test.ui.adapter.weatherDetail.WeatherDayForHourViewHolder

class WeatherDayForHourAdapter(
    private var itemList: List<ListObject> = listOf()
) : RecyclerView.Adapter<WeatherDayForHourViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WeatherDayForHourViewHolder =
        WeatherDayForHourViewHolder(parent)


    override fun onBindViewHolder(holder: WeatherDayForHourViewHolder, position: Int) {
        holder.bind(
            itemList[position],
            position
        )
    }

    override fun getItemCount(): Int = itemList.size

    fun setNewList(itemList: List<ListObject>) {
        this.itemList = itemList
        notifyDataSetChanged()
    }

}

