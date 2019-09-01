package com.vanard.simpleweatherapp.data.model

import com.google.gson.annotations.SerializedName

data class ForecastDays(@SerializedName("forecastday")
                        val entries: List<FutureWeatherEntry>)