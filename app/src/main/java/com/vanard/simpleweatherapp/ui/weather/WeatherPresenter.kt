package com.vanard.simpleweatherapp.ui.weather

import android.util.Log.d
import com.vanard.simpleweatherapp.data.model.CurrentWeather
import com.vanard.simpleweatherapp.data.model.FutureWeather
import com.vanard.simpleweatherapp.data.repository.ForecastRepositoryImpl
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableObserver
import io.reactivex.schedulers.Schedulers

class WeatherPresenter(private val mView: WeatherContract.View, private val forecastRepositoryImpl: ForecastRepositoryImpl) : WeatherContract.Presenter {

    private val compositeDisposable = CompositeDisposable()

    override fun getDataCurrentWeather(location: String) {
        val dataObserver = getDataObserver()
        compositeDisposable.add(
            forecastRepositoryImpl.getCurrentWeather(location)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(dataObserver)
        )
    }

    override fun getDataForecastWeather(location: String) {
        val forecastObserver = getWeatherObserver()
        compositeDisposable.add(
            forecastRepositoryImpl.getFutureWeatherList(location)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(forecastObserver)
        )

    }

    private fun getDataObserver(): DisposableObserver<CurrentWeather> {
        return object : DisposableObserver<CurrentWeather>() {
            override fun onNext(currentWeather: CurrentWeather) {
                mView.setDataWeather(currentWeather)
            }

            override fun onError(e: Throwable) {
                d("PTK", e.message)
            }

            override fun onComplete() {
            }
        }
    }

    private fun getWeatherObserver(): DisposableObserver<FutureWeather> {
        return object : DisposableObserver<FutureWeather>() {
            override fun onNext(weatherList: FutureWeather) {
                mView.setDataListWeather(weatherList)
            }

            override fun onError(e: Throwable) {
                d("PTK", e.localizedMessage)
            }

            override fun onComplete() {
            }
        }
    }

}