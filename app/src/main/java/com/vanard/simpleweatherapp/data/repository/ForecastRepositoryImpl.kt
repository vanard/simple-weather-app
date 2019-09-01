package com.vanard.simpleweatherapp.data.repository

import com.vanard.simpleweatherapp.data.model.CurrentWeather
import com.vanard.simpleweatherapp.data.model.FutureWeather
import com.vanard.simpleweatherapp.data.network.WeatherApixuService
import io.reactivex.Observable

class ForecastRepositoryImpl(
    private val apixuService: WeatherApixuService
    ) : ForecastRepository {
    override fun getFutureWeatherList(location: String): Observable<out FutureWeather> {
        return apixuService.getForecastWeather(location)
    }

    override fun getCurrentWeather(location: String): Observable<out CurrentWeather> {
        return apixuService.getCurrentWeather(location)
    }

}