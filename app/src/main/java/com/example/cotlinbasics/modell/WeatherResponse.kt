package com.example.cotlinbasics.modell

data class WeatherResponse (
    val main: Main,
    val wind: Wind,
)

data class Main(
    val temp: Float,
    val temp_min: Float,
    val humidity: Float,
)

data class Wind(
    val speed: Float,

)