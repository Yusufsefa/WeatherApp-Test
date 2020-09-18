package com.yyusufsefa.weatherapp_test.ui.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.yyusufsefa.weatherapp_test.data.response.BaseItem
import com.yyusufsefa.weatherapp_test.data.response.HeaderObject
import com.yyusufsefa.weatherapp_test.data.response.ListObject

class WeatherListAdapter(
    private var itemList: List<BaseItem> = listOf(),
    private inline val onItemClikListiner: (ListObject, Int) -> Unit
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            ITEM -> {
                WeatherListItemViewHolder(parent)
            }
            HEADER -> {
                WeatherListHeaderViewHolder(parent)
            }
            else -> {
                WeatherListItemViewHolder(parent)
            }
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

        when (getItemViewType(position)) {
            HEADER -> {
                (holder as WeatherListHeaderViewHolder).bind(
                    itemList[position] as HeaderObject

                )
            }
            ITEM -> {
                (holder as WeatherListItemViewHolder).bind(
                    itemList[position] as ListObject,
                    onItemClikListiner,
                    position
                )
            }
        }
    }

    override fun getItemCount(): Int = itemList.size

    override fun getItemViewType(position: Int): Int {
        return when (itemList[position]) {
            is ListObject -> {
                ITEM
            }
            is HeaderObject -> {
                HEADER
            }
            else -> {
                ITEM
            }
        }
    }

    fun setNewList(itemList: List<BaseItem>) {
        this.itemList = itemList
        notifyDataSetChanged()
    }

    companion object {
        private const val HEADER = 1
        private const val ITEM = 2
    }

}