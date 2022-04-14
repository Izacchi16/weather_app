package com.example.weatherapp.api

import com.example.weatherapp.model.WeatherModel
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {

    @GET("api/forecast/city/{cityId}")
    fun apiDemo(
        @Path("cityId") cityId: String
    ): Call<WeatherModel>
}
