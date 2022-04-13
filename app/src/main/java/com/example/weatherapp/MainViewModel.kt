package com.example.weatherapp

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weatherapp.model.WeatherModel
import com.example.weatherapp.repository.WeatherRepository
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {
    private val weatherRepository = WeatherRepository()

    private val _errorHandler = MutableLiveData<Unit>()
    val errorHandler: LiveData<Unit> = _errorHandler

    private val _weatherInfo = MutableLiveData<List<WeatherModel>>()
    val weatherInfo: LiveData<List<WeatherModel>> = _weatherInfo

    fun onCreate() {
        fetchWeatherInfo()
    }

    private fun fetchWeatherInfo() = viewModelScope.launch {
        try {
            val result = weatherRepository.fetchWeatherInfo()
            _weatherInfo.value = result
        } catch (throwable: Throwable) {
            _errorHandler.postValue(Unit)
            Log.d("izacchi", throwable.toString())
        }
    }
}
