package com.yyusufsefa.weatherapp_test.ui.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.yyusufsefa.weatherapp_test.data.response.BaseItem
import com.yyusufsefa.weatherapp_test.data.response.ListObject

class WeatherListAdapter(
    private var itemList: List<BaseItem> = listOf(),
    private inline val onItemClikListiner: (ListObject, Int) -> Unit
) : RecyclerView.Adapter<WeatherListItemViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        WeatherListItemViewHolder(parent)

    override fun onBindViewHolder(holder: WeatherListItemViewHolder, position: Int) {
        holder.bind(
            itemList[position] as ListObject,
            onItemClikListiner,
            position
        )
    }

    override fun getItemCount(): Int = itemList.size

    fun setNewList(itemList: List<BaseItem>) {
        this.itemList = itemList
        notifyDataSetChanged()
    }

}