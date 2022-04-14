package com.example.weatherapp.repository

import com.example.weatherapp.api.ApiClient
import com.example.weatherapp.model.WeatherModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class WeatherRepository {

    suspend fun fetchWeatherInfo(cityId: String): List<WeatherModel> = withContext(Dispatchers.IO) {
        val dataList = mutableListOf<WeatherModel>()

        try {
            ApiClient().createService().apiDemo(cityId).enqueue(object : Callback<WeatherModel> {
                override fun onResponse(
                    call: Call<WeatherModel>,
                    response: Response<WeatherModel>
                ) {
                    if (response.isSuccessful) {
                        response.body()?.let { dataList.add(it) }
                    }
                }

                override fun onFailure(call: Call<WeatherModel>, t: Throwable) {
                }
            })
            // TODO: これは良く無い
            delay(500)
            return@withContext dataList
        } catch (t: Throwable) {
            return@withContext emptyList<WeatherModel>()
        }
    }
}
