package com.vanard.simpleweatherapp.data.model

import com.google.gson.annotations.SerializedName
import com.vanard.simpleweatherapp.data.model.ForecastRepo.Alert
import com.vanard.simpleweatherapp.data.model.ForecastRepo.Forecast

data class ResponseWeatherApixu(

    @field:SerializedName("current")
	val current: Current? = null,

    @field:SerializedName("alert")
	val alert: Alert? = null,

    @field:SerializedName("location")
	val location: Location? = null,

    @field:SerializedName("forecast")
	val forecast: Forecast? = null
)