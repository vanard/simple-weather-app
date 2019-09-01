package com.vanard.simpleweatherapp.ui.weather

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.RecyclerView
import com.vanard.simpleweatherapp.R
import com.vanard.simpleweatherapp.data.model.FutureWeatherEntry
import kotlinx.android.synthetic.main.item_weather.view.*
import java.text.SimpleDateFormat
import java.util.*
import kotlin.math.roundToInt

class WeatherAdapter (private val weatherList: List<FutureWeatherEntry>) : RecyclerView.Adapter<WeatherAdapter.WeatherViewHolder>() {
    class WeatherViewHolder (itemView: View) : RecyclerView.ViewHolder(itemView) {
        val weatherDay: AppCompatTextView = itemView.item_day
        val weatherDegree: AppCompatTextView = itemView.item_degree
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WeatherViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_weather, parent, false)

        return WeatherViewHolder(itemView)
    }

    override fun getItemCount() = weatherList.size

    fun getItem(position: Int): FutureWeatherEntry = weatherList[position]

    override fun onBindViewHolder(holder: WeatherViewHolder, position: Int) {
        val weather = getItem(position)
        val date = SimpleDateFormat("yyyy-mm-dd", Locale.getDefault()).parse(weather.date)
        val day = SimpleDateFormat("EEEE", Locale.getDefault()).format(date)
        holder.weatherDay.text = day.toLowerCase()
        holder.weatherDegree.text = weather.day.maxtempC.roundToInt().toString() + 0x00B0.toChar()
    }

}