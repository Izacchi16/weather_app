package com.example.weatherapp.model

import java.time.LocalDateTime

data class Forecasts(
    val data: LocalDateTime,
    val dateLabel: String,
    val telop: String,
    val detail: WeatherDetail,
    val temperature: Temperature,
    val chanceOfRain: ChanceOfRain,
    val Image: Image
)
