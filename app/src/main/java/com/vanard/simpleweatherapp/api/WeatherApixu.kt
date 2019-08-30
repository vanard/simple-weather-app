package com.vanard.simpleweatherapp.api

import com.vanard.simpleweatherapp.data.model.ResponseCities
import com.vanard.simpleweatherapp.data.model.ResponseWeatherApixu
import io.reactivex.Observable
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApixu {

    @GET("current.json/")
    fun getCurrentWeather(
        @Query("key") key: String = RetrofitClient.API_KEY,
        @Query("q") location: String
    ): Observable<ResponseWeatherApixu>

    @GET("forecast.json/")
    fun getForecastWeather(
        @Query("key") key: String = RetrofitClient.API_KEY,
        @Query("q") location: String,
        @Query("days") days: String = "7"
    ): Observable<List<ResponseWeatherApixu>>

    @GET("search.json/")
    fun getCities(
        @Query("key") key: String = RetrofitClient.API_KEY,
        @Query("q") location: String
    ): Single<List<ResponseCities>>

}