package com.vanard.simpleweatherapp.api

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {
    private const val APIXU_BASE_URL = "https://api.apixu.com/v1/"
    const val API_KEY = "76cbd1c36cc44ecc82b142905193008"

    fun weatherApixu(): WeatherApixu {
        val retrofit = Retrofit.Builder()
            .baseUrl(APIXU_BASE_URL)
            .client(OkHttpClient().newBuilder().build())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        return retrofit.create(WeatherApixu::class.java)
    }
}