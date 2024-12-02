package com.example.cotlinbasics.network

import retrofit2.Call
import com.example.cotlinbasics.modell.WeatherResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherService {
    @GET("data/2.5/weather")
    fun getCurrentWeather(
        @Query("q") cityName: String,
        @Query("appid") apiKey: String,
        @Query("units") units: String
    ): Call<WeatherResponse>
}