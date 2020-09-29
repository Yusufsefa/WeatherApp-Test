package com.yyusufsefa.weatherapp_test.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.yyusufsefa.weatherapp_test.R
import com.yyusufsefa.weatherapp_test.data.response.BaseItem
import com.yyusufsefa.weatherapp_test.data.response.ListObject
import com.yyusufsefa.weatherapp_test.databinding.ItemWeatherListBinding

class WeatherListAdapter(
    private var itemList: List<BaseItem> = listOf(),
    private inline val onItemClikListiner: (ListObject, Int) -> Unit
) : RecyclerView.Adapter<WeatherListAdapter.ViewHolder>() {

    class ViewHolder(var view: ItemWeatherListBinding) : RecyclerView.ViewHolder(view.root)

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): WeatherListAdapter.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = DataBindingUtil.inflate<ItemWeatherListBinding>(
            inflater,
            R.layout.item_weather_list,
            parent,
            false
        )
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: WeatherListAdapter.ViewHolder, position: Int) {
        holder.view.listObject = itemList[position] as ListObject?
        holder.itemView.setOnClickListener {
            onItemClikListiner(
                itemList[position] as ListObject,
                position
            )
        }
    }

    override fun getItemCount(): Int = itemList.size

    fun setNewList(itemList: List<BaseItem>) {
        this.itemList = itemList
        notifyDataSetChanged()
    }
}