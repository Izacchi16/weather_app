package com.example.weatherapp.repository

import com.example.weatherapp.api.ApiClient
import com.example.weatherapp.model.WeatherModel
import com.example.weatherapp.preference.PreferenceStorage
import javax.inject.Inject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.withContext
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class WeatherRepository @Inject constructor(
    private val apiClient: ApiClient,
    private val preferenceStorage: PreferenceStorage
) {

    suspend fun fetchWeatherInfo(cityId: String): List<WeatherModel> = withContext(Dispatchers.IO) {
        val dataList = mutableListOf<WeatherModel>()

        try {
            apiClient.createService().apiDemo(cityId).enqueue(object : Callback<WeatherModel> {
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

    suspend fun getCityId(): String {
        return preferenceStorage.getCityId().first()
    }

    suspend fun setCityId(cityId: String) {
        preferenceStorage.updateCityId(cityId)
    }
}
