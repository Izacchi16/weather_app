package com.example.weatherapp.model

import com.google.gson.annotations.SerializedName

data class ChanceOfRain(
    @SerializedName("T00_06")
    val t00_06: String,
    @SerializedName("T06_12")
    val t06_12: String,
    @SerializedName("T12_18")
    val t12_18: String,
    @SerializedName("T18_24")
    val t18_24: String,
)
