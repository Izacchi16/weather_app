package com.example.weatherapp

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weatherapp.model.WeatherModel
import com.example.weatherapp.repository.WeatherRepository
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {
    enum class CITY(val cityId: String) {
        TOKYO("130010"),
        OTSU("250010")
    }

    private val weatherRepository = WeatherRepository()

    private val _errorHandler = MutableLiveData<Unit>()
    val errorHandler: LiveData<Unit> = _errorHandler

    private val _weatherInfo = MutableLiveData<List<WeatherModel?>>()
    val weatherInfo: LiveData<List<WeatherModel?>> = _weatherInfo

    private var cityId = CITY.TOKYO.cityId

    val contents = Transformations.map(weatherInfo) {
        it.toString()
    }

    fun onCreate() {
        fetchWeatherInfo(cityId)
    }

    private fun fetchWeatherInfo(cityId: String) = viewModelScope.launch {
        try {
            val result = weatherRepository.fetchWeatherInfo(cityId)
            _weatherInfo.value = result
        } catch (throwable: Throwable) {
            _errorHandler.value = Unit
        }
    }
}
