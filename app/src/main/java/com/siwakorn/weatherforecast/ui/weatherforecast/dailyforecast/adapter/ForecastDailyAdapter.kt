package com.siwakorn.weatherforecast.ui.weatherforecast.dailyforecast.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.siwakorn.weatherforcast.databinding.ItemForecastBinding
import com.siwakorn.weatherforecast.util.extension.loadImageUrl

class ForecastDailyAdapter : RecyclerView.Adapter<ForecastDailyAdapter.ForecastDailyHolder>() {

    private var items: List<ForecastDailyAdapterUiModel> = listOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int)
            : ForecastDailyHolder = ForecastDailyHolder.create(parent)

    override fun onBindViewHolder(holder: ForecastDailyHolder, position: Int) {
        val item = items[position]
        holder.bind(item)
    }

    override fun getItemCount(): Int = items.count()

    fun setData(items: List<ForecastDailyAdapterUiModel>) {
        this.items = items
        notifyDataSetChanged()
    }

    class ForecastDailyHolder(private val binding: ItemForecastBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(data: ForecastDailyAdapterUiModel) {
            binding.tvItemForecastTime.text = data.time
            binding.tvItemForecastTemp.text = data.temp
            binding.tvItemForecastHumidity.text = data.humidity
            binding.ivItemForcast.loadImageUrl(data.iconUrl)
        }

        companion object {
            fun create(parent: ViewGroup): ForecastDailyHolder = ForecastDailyHolder(
                ItemForecastBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            )
        }
    }
}