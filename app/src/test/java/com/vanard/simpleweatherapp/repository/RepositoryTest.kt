package com.vanard.simpleweatherapp.repository

import com.vanard.simpleweatherapp.data.network.WeatherApixuService
import com.vanard.simpleweatherapp.data.repository.ForecastRepositoryImpl
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

class RepositoryTest {
    @Mock
    lateinit var apiService: WeatherApixuService

    private lateinit var forecastRepositoryImpl: ForecastRepositoryImpl

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        forecastRepositoryImpl = ForecastRepositoryImpl(apiService)
    }

    @Test
    fun getCurrentWeatherTest() {
        forecastRepositoryImpl.getCurrentWeather("Jakarta")
        Mockito.verify(apiService).getCurrentWeather("Jakarta")
    }

    @Test
    fun getForecastWeatherTest() {
        forecastRepositoryImpl.getFutureWeatherList("Bandung")
        Mockito.verify(apiService).getForecastWeather("Bandung")
    }

}