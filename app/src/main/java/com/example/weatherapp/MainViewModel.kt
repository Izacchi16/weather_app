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
    private val weatherRepository = WeatherRepository()

    private val _errorHandler = MutableLiveData<Unit>()
    val errorHandler: LiveData<Unit> = _errorHandler

    private val _weatherInfo = MutableLiveData<List<WeatherModel?>>()
    val weatherInfo: LiveData<List<WeatherModel?>> = _weatherInfo

    private var cityId = "130010"

    val contents = Transformations.map(weatherInfo) {
        it.toString()
    }

    fun onCreate(cityId: String) {
        this.cityId = cityId
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
