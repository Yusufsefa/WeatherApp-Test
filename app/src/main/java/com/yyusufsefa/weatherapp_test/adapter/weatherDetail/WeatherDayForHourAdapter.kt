package com.yyusufsefa.weatherapp_test.adapter.weatherDetail

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.yyusufsefa.weatherapp_test.data.response.ListObject

class WeatherDayForHourAdapter(
    private var itemList: List<ListObject> = listOf()
) : RecyclerView.Adapter<WeatherDayForHourViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WeatherDayForHourViewHolder =
        WeatherDayForHourViewHolder(parent)


    override fun onBindViewHolder(holder: WeatherDayForHourViewHolder, position: Int) {
        holder.bind(itemList[position])
    }

    override fun getItemCount(): Int = itemList.size

    fun setNewList(itemList: List<ListObject>) {
        this.itemList = itemList
        notifyDataSetChanged()
    }

}

val diffCallback = object : DiffUtil.ItemCallback<ListObject>() {
    override fun areItemsTheSame(oldItem: ListObject, newItem: ListObject): Boolean =
        oldItem == newItem

    override fun areContentsTheSame(oldItem: ListObject, newItem: ListObject): Boolean =
        oldItem.dt_txt == newItem.dt_txt
}
