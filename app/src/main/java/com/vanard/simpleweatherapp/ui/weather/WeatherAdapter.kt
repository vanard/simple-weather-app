package com.vanard.simpleweatherapp.ui.weather

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import com.vanard.simpleweatherapp.R
import com.vanard.simpleweatherapp.data.model.FutureWeatherEntry
import com.vanard.simpleweatherapp.ui.detailweather.DetailWeatherActivity
import kotlinx.android.synthetic.main.item_weather.view.*
import org.jetbrains.anko.startActivity
import java.text.SimpleDateFormat
import java.util.*
import kotlin.math.roundToInt

class WeatherAdapter (private val weatherList: List<FutureWeatherEntry>) : RecyclerView.Adapter<WeatherAdapter.WeatherViewHolder>() {

    class WeatherViewHolder (itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val weatherDay: AppCompatTextView = itemView.item_day
        private val weatherDegree: AppCompatTextView = itemView.item_degree
        private val weatherIcon: AppCompatImageView = itemView.item_icon

        fun bindItems(weather: FutureWeatherEntry) {
            val date = SimpleDateFormat("yyyy-mm-dd", Locale.getDefault()).parse(weather.date)
            val day = SimpleDateFormat("EEEE", Locale.getDefault()).format(date)
            val s = weather.day.condition.icon
            Picasso.get().load("https://${s.substring(2, s.length)}").into(weatherIcon)
            weatherDay.text = day
            weatherDegree.text = weather.day.avgtempC.roundToInt().toString() + 0x00B0.toChar()

            itemView.setOnClickListener {
                itemView.context.startActivity<DetailWeatherActivity>("data" to weather.day, "day" to day)
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WeatherViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_weather, parent, false)

        return WeatherViewHolder(itemView)
    }

    override fun getItemCount() = weatherList.size

    fun getItem(position: Int): FutureWeatherEntry = weatherList[position]

    override fun onBindViewHolder(holder: WeatherViewHolder, position: Int) {
        val weather = getItem(position)
        holder.bindItems(weather)
    }

}