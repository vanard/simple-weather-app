package com.vanard.simpleweatherapp.data.repository

import com.vanard.simpleweatherapp.data.model.CurrentWeather
import com.vanard.simpleweatherapp.data.model.FutureWeather
import io.reactivex.Observable

interface ForecastRepository {
    fun getCurrentWeather(location: String): Observable<out CurrentWeather>

    fun getFutureWeatherList(location: String): Observable<out FutureWeather>
}