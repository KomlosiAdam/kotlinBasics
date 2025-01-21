package com.example.cotlinbasics

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.cotlinbasics.modell.WeatherResponse
import com.example.cotlinbasics.network.WeatherService
import retrofit2.Call
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class WeatherActivity : AppCompatActivity() {

    private lateinit var textviewTemp: TextView
    private lateinit var textviewTempMin: TextView
    private lateinit var textviewHumidity: TextView
    private lateinit var textviewWindSpeed: TextView
    private lateinit var editTextTown: EditText
    private lateinit var buttonTown: Button
    private var town: String = ""
    private var apiKey = "a1a3eefc700b66022b424ec06e245fe0";

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_weather)

        textviewTemp = findViewById(R.id.textviewTemp)
        textviewTempMin = findViewById(R.id.textviewTempMin)
        textviewHumidity = findViewById(R.id.textviewHumidity)
        textviewWindSpeed = findViewById(R.id.textviewWindSpeed)
        editTextTown = findViewById(R.id.editTextTown)
        buttonTown = findViewById(R.id.buttonTown)

        buttonTown.setOnClickListener() {
            town = editTextTown.text.toString();
            fetchWeatherData()
        }

    }
    private fun fetchWeatherData() {
        val retrofit= Retrofit.Builder()
            .baseUrl("https://api.openweathermap.org")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val weatherService = retrofit.create(WeatherService::class.java)

        val call = weatherService.getCurrentWeather(town, apiKey, "metric")
        call.enqueue(object : retrofit2.Callback<WeatherResponse> {
            override fun onResponse(
                call: Call<WeatherResponse>,
                response: Response<WeatherResponse>
            ) {
                if(response.isSuccessful()) {
                    val weatherResponse = response.body()
                    if(weatherResponse != null) {
                        val weatherInfo = weatherResponse.main.temp
                        textviewTemp.text = weatherInfo.toString()+" C"
                        val weatherInfoMin = weatherResponse.main.temp_min
                        textviewTempMin.text = weatherInfoMin.toString()+" C"
                        val weatherInfoHumidity = weatherResponse.main.humidity
                        textviewHumidity.text = weatherInfoHumidity.toString()+" %"
                        val weatherInfoWindSpeed = weatherResponse.wind.speed
                        textviewWindSpeed.text = weatherInfoWindSpeed.toString()+" KM/H"
                    }
                }
            }

            override fun onFailure(call: Call<WeatherResponse>, t: Throwable) {
                Log.e("Hiba", "Hiba az api kérés során")
            }
        })
    }
}