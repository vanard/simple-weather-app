package com.vanard.simpleweatherapp.data.network

import com.vanard.simpleweatherapp.data.model.CurrentWeather
import com.vanard.simpleweatherapp.data.model.FutureWeather
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

const val FORECAST_DAYS_WEEK = 7

interface WeatherApixuService {

    @GET("current.json")
    fun getCurrentWeather(
        @Query("q") location: String
    ): Observable<CurrentWeather>

    @GET("forecast.json")
    fun getForecastWeather(
        @Query("q") location: String,
        @Query("days") days: Int = FORECAST_DAYS_WEEK
    ): Observable<FutureWeather>

}