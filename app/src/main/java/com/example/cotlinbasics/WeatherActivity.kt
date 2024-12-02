package com.example.cotlinbasics

import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.cotlinbasics.modell.WeatherResponse
import com.example.cotlinbasics.network.WeatherService
import retrofit2.Call
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class WeatherActivity : AppCompatActivity() {

    private lateinit var textviewTemp: TextView
    private var apiKey = "a1a3eefc700b66022b424ec06e245fe0";

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_weather)

        textviewTemp = findViewById(R.id.textviewTemp)

        fetchWeatherData()

    }
    private fun fetchWeatherData() {
        val retrofit= Retrofit.Builder()
            .baseUrl("https://api.openweathermap.org")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val weatherService = retrofit.create(WeatherService::class.java)

        val call = weatherService.getCurrentWeather("Tatabanya", apiKey, "metric")
        call.enqueue(object : retrofit2.Callback<WeatherResponse> {
            override fun onResponse(
                call: Call<WeatherResponse>,
                response: Response<WeatherResponse>
            ) {
                if(response.isSuccessful()) {
                    val weatherResponse = response.body()
                    if(weatherResponse != null) {
                        val weatherInfo = weatherResponse.main.temp
                        textviewTemp.text = weatherInfo.toString()
                    }
                }
            }

            override fun onFailure(call: Call<WeatherResponse>, t: Throwable) {
                Log.e("Hiba", "Hiba az api kérés során")
            }
        })
    }
}