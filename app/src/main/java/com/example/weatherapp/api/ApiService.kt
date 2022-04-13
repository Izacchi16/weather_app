package com.example.weatherapp.api

import com.example.weatherapp.model.WeatherDetail
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService{

    @GET("api/forecast/city")
    fun apiDemo(
        @Query("cityId") id: Int
    ): Call<List<WeatherDetail>>
}
