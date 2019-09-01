package com.vanard.simpleweatherapp.ui.weather

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DividerItemDecoration
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationResult
import com.vanard.simpleweatherapp.R
import com.vanard.simpleweatherapp.data.model.CurrentWeather
import com.vanard.simpleweatherapp.data.model.FutureWeather
import com.vanard.simpleweatherapp.data.model.FutureWeatherEntry
import com.vanard.simpleweatherapp.data.network.ConnectivityInterceptorImpl
import com.vanard.simpleweatherapp.data.network.RetrofitClient
import com.vanard.simpleweatherapp.data.repository.ForecastRepositoryImpl
import com.vanard.simpleweatherapp.utility.LifecycleBoundLocationManager
import com.vanard.simpleweatherapp.utility.SetUpLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.support.v4.onRefresh
import org.kodein.di.KodeinAware
import org.kodein.di.android.closestKodein
import org.kodein.di.generic.instance
import kotlin.math.roundToInt

private const val MY_PERMISSION_ACCESS_COARSE_LOCATION = 1

class WeatherActivity : AppCompatActivity(), KodeinAware, WeatherContract.View {

    private lateinit var presenter: WeatherPresenter

    override fun setDataWeather(currentWeather: CurrentWeather) {
        location_text.text = currentWeather.location.name
        condition_text.text = currentWeather.currentWeatherEntry.condition.text
        suhu_text.text = currentWeather.currentWeatherEntry.tempC.roundToInt().toString() + 0x00B0.toChar()
        refresh_layout.isRefreshing = false
    }

    override fun setDataListWeather(weatherList: FutureWeather) {
        mWeatherList.addAll(weatherList.futureWeatherEntries.entries)
        mWeatherAdapter.notifyDataSetChanged()
        refresh_layout.isRefreshing = false
    }

    override val kodein by closestKodein()

    private lateinit var mWeatherAdapter: WeatherAdapter
    private val mWeatherList = ArrayList<FutureWeatherEntry>()

    private val fusedLocationProviderClient: FusedLocationProviderClient by instance()

    private val locationCallback = object : LocationCallback() {
        override fun onLocationResult(p0: LocationResult?) {
            super.onLocationResult(p0)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        refresh_layout.isRefreshing = true

        requestLocationPermission()

        if (hasLocationPermission()) {
            bindLocationManager()
        }
        else
            requestLocationPermission()

        presenter = WeatherPresenter(this, ForecastRepositoryImpl(RetrofitClient.apiService(ConnectivityInterceptorImpl(this))))
        SetUpLayoutManager.verticalLinearLayout(applicationContext, list_weather)

        mWeatherAdapter = WeatherAdapter(mWeatherList)
        list_weather.addItemDecoration(DividerItemDecoration(this, DividerItemDecoration.VERTICAL))
        list_weather.adapter = mWeatherAdapter

        refresh_layout.onRefresh {
            presenter.getDataCurrentWeather("Jakarta")
            presenter.getDataForecastWeather("Jakarta")
        }
    }

    override fun onResume() {
        super.onResume()
        refresh_layout.isRefreshing = true
    }

    private fun bindLocationManager() {
        LifecycleBoundLocationManager(
            this,
            fusedLocationProviderClient, locationCallback
        )
    }

    private fun requestLocationPermission() {
        ActivityCompat.requestPermissions(
            this,
            arrayOf(Manifest.permission.ACCESS_COARSE_LOCATION),
            MY_PERMISSION_ACCESS_COARSE_LOCATION
        )
    }

    private fun hasLocationPermission(): Boolean {
        return ContextCompat.checkSelfPermission(this,
            Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        if (requestCode == MY_PERMISSION_ACCESS_COARSE_LOCATION) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED)
                bindLocationManager()
            else
                Toast.makeText(this, "Please, set location manually in settings", Toast.LENGTH_LONG).show()
        }
    }
}
