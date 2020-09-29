package com.yyusufsefa.weatherapp_test.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.yyusufsefa.weatherapp_test.R
import com.yyusufsefa.weatherapp_test.data.response.ListObject
import com.yyusufsefa.weatherapp_test.databinding.ItemHourForWeatherBinding

class WeatherDayForHourAdapter(
    private var itemList: List<ListObject> = listOf()
) : RecyclerView.Adapter<WeatherDayForHourAdapter.ViewHolder>() {

    class ViewHolder(var view: ItemHourForWeatherBinding) : RecyclerView.ViewHolder(view.root)

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = DataBindingUtil.inflate<ItemHourForWeatherBinding>(
            inflater,
            R.layout.item_hour_for_weather,
            parent,
            false
        )
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.view.hourofweather = itemList[position]
    }

    override fun getItemCount(): Int = itemList.size

    fun setNewList(itemList: List<ListObject>) {
        this.itemList = itemList
        notifyDataSetChanged()
    }

//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WeatherDayForHourViewHolder =
//        WeatherDayForHourViewHolder(parent)
//
//
//    override fun onBindViewHolder(holder: WeatherDayForHourViewHolder, position: Int) {
//        holder.bind(
//            itemList[position],
//            position
//        )
//    }


}

