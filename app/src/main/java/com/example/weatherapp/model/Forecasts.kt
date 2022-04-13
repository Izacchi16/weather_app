package com.example.weatherapp.model

data class Forecasts(
    val date: String,
    val dateLabel: String,
    val telop: String,
    val detail: Detail
)
