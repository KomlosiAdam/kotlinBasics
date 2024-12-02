package com.example.cotlinbasics.modell

data class WeatherResponse (
    val main: Main,
)

data class Main(
    val temp: Float,
)