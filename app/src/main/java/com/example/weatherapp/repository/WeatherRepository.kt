package com.example.weatherapp.repository

import android.util.Log
import com.example.weatherapp.api.ApiClient
import com.example.weatherapp.model.WeatherModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class WeatherRepository {

    fun fetchWeatherInfo(): List<WeatherModel> {
        val dataList = mutableListOf<WeatherModel>()

        ApiClient().createService().apiDemo().enqueue(object : Callback<WeatherModel> {
            override fun onResponse(
                call: Call<WeatherModel>,
                response: Response<WeatherModel>
            ) {
                if (response.isSuccessful) {
                    Log.d("izacchi", response.body().toString())
                    response.body()?.let { dataList.add(it) }
                }
            }

            override fun onFailure(call: Call<WeatherModel>, t: Throwable) {
                Log.d("izacchi", t.toString())
            }
        })

        return dataList
    }
}
