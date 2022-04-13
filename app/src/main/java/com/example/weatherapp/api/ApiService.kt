package com.example.weatherapp.api

import com.example.weatherapp.model.WeatherModel
import retrofit2.Call
import retrofit2.http.GET

interface ApiService {

    @GET("api/forecast/city/130010")
    fun apiDemo(): Call<WeatherModel>
}
