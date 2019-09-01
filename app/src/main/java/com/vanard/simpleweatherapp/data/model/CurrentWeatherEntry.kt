package com.vanard.simpleweatherapp.data.model

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

data class CurrentWeatherEntry(
    @SerializedName("temp_c")
    val tempC: Double,
    @SerializedName("temp_f")
    val tempF: Double,
    @SerializedName("is_day")
    val isDay: Int,
    val condition: Condition,
    @SerializedName("wind_mph")
    val windMph: Double,
    @SerializedName("wind_kph")
    val windKph: Double,
    @SerializedName("wind_dir")
    val windDir: String,
    @SerializedName("precip_mm")
    val precipMm: Double,
    @SerializedName("precip_in")
    val precipIn: Double,
    @SerializedName("feelslike_c")
    val feelslikeC: Double,
    @SerializedName("feelslike_f")
    val feelslikeF: Double,
    @SerializedName("vis_km")
    val visKm: Double,
    @SerializedName("vis_miles")
    val visMiles: Double
): Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readDouble(),
        parcel.readDouble(),
        parcel.readInt(),
        TODO("condition"),
        parcel.readDouble(),
        parcel.readDouble(),
        parcel.readString(),
        parcel.readDouble(),
        parcel.readDouble(),
        parcel.readDouble(),
        parcel.readDouble(),
        parcel.readDouble(),
        parcel.readDouble()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeDouble(tempC)
        parcel.writeDouble(tempF)
        parcel.writeInt(isDay)
        parcel.writeDouble(windMph)
        parcel.writeDouble(windKph)
        parcel.writeString(windDir)
        parcel.writeDouble(precipMm)
        parcel.writeDouble(precipIn)
        parcel.writeDouble(feelslikeC)
        parcel.writeDouble(feelslikeF)
        parcel.writeDouble(visKm)
        parcel.writeDouble(visMiles)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<CurrentWeatherEntry> {
        override fun createFromParcel(parcel: Parcel): CurrentWeatherEntry {
            return CurrentWeatherEntry(parcel)
        }

        override fun newArray(size: Int): Array<CurrentWeatherEntry?> {
            return arrayOfNulls(size)
        }
    }
}