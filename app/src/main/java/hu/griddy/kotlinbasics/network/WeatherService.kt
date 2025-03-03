package hu.griddy.kotlinbasics.network

import hu.griddy.kotlinbasics.model.WeatherResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherService {
    @GET("data/2.5/weather")
    fun getWeather(
        @Query("q") cityName:String,
        @Query("appid") key:String,
        @Query("units") units:String
    ): Call<WeatherResponse >
}