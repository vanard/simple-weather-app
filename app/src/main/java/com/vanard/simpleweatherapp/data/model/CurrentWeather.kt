package com.vanard.simpleweatherapp.data.model

import com.google.gson.annotations.SerializedName

data class CurrentWeather(
    val location: WeatherLocation,
    @SerializedName("current")
    val currentWeatherEntry: CurrentWeatherEntry
)