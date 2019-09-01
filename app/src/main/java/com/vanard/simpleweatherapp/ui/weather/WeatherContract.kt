package com.vanard.simpleweatherapp.ui.weather

import com.vanard.simpleweatherapp.data.model.CurrentWeather
import com.vanard.simpleweatherapp.data.model.FutureWeather

interface WeatherContract {
    interface View {
        fun setDataWeather(currentWeather: CurrentWeather)
        fun setDataListWeather(weatherList: FutureWeather)
    }

    interface Presenter {
        fun getDataCurrentWeather(location: String)
        fun getDataForecastWeather(location: String)
    }
}