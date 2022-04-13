package com.example.weatherapp.model

data class WeatherModel(
    val publicTime: String,
    val publicTimeFormatted: String,
    val publishingOffice: String,
    val title: String,
    val link: String,
    val description: Description,
    val forecasts: List<Forecasts>,
    val temperature: Temperature,
    val chanceOfRain: ChanceOfRain,
    val image: Image
)