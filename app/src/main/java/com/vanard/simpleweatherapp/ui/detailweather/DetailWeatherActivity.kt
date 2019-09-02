package com.vanard.simpleweatherapp.ui.detailweather

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.squareup.picasso.Picasso
import com.vanard.simpleweatherapp.R
import com.vanard.simpleweatherapp.data.model.Day
import kotlinx.android.synthetic.main.activity_detail_weather.*
import kotlin.math.roundToInt

class DetailWeatherActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_weather)

        setUpView()
    }

    private fun setUpView() {
        supportActionBar?.title = "Detail"

        val data = intent.getParcelableExtra<Day>("data")
        val day = intent.getStringExtra("day")
        val s = data.condition.icon

        Picasso.get().load("https://${s.substring(2, s.length)}").into(detail_icon)
        detail_day.text = day
        detail_degree.text = data.avgtempC.roundToInt().toString() + 0x00B0.toChar()
        detail_condition.text = data.condition.text
        detail_precipitation.text = "${data.totalprecipMm.roundToInt()} %"
        detail_humidity.text = "${data.humidity.toInt()} %"
        detail_wind.text = "${data.maxwindKph.toInt()} km/h"
    }
}
