package com.vanard.simpleweatherapp.data.model

import com.google.gson.annotations.SerializedName

data class FutureWeather(
    @SerializedName("forecast")
    val futureWeatherEntries: ForecastDays,
    val location: WeatherLocation
)