package com.vanard.simpleweatherapp.data.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Condition(
    val text: String,
    val icon: String,
    val code: Int
): Parcelable