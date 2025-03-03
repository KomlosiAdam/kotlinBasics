package hu.griddy.kotlinbasics

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import hu.griddy.kotlinbasics.model.WeatherResponse
import hu.griddy.kotlinbasics.network.WeatherService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class WeatherActivity : AppCompatActivity() {

    private lateinit var textviewTemp:TextView;
    private lateinit var cityEditText: EditText;
    private lateinit var searchWeatherButton: Button;

    private val apiKey:String = "87c5342f9b13c3689c69caaf71ecfaa0"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_weather)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        textviewTemp = findViewById(R.id.textview_temp);
        cityEditText = findViewById(R.id.cityEditText);
        searchWeatherButton = findViewById(R.id.searchWeatherButton);

        searchWeatherButton.setOnClickListener{
            if (cityEditText.text.toString() == ""){
                Toast.makeText(this,"Adjon meg várost!", Toast.LENGTH_SHORT).show();
                return@setOnClickListener
            }

            fetchWeatherData(cityEditText.text.toString());
        }
    }

    private fun fetchWeatherData(city:String){
        val retrofit:Retrofit = Retrofit.Builder()
            .baseUrl("https://api.openweathermap.org/")
            .addConverterFactory(GsonConverterFactory.create())
            .build();

        val weatherService = retrofit.create(WeatherService::class.java);
        val call = weatherService.getWeather(city, apiKey, "metric");

        call.enqueue(object: Callback<WeatherResponse> {
            @Override
            override fun onResponse(
                call: Call<WeatherResponse>,
                response: Response<WeatherResponse>
            ) {
                if (!response.isSuccessful){
                    Log.e("fetchWeatherData ERR", "Sikertelen API kérés!");
                    return;
                }

                val weatherResponse = response.body();
                if (weatherResponse == null){
                    Log.e("fetchWeatherData ERR", "Az API válasz üres!");
                }

                val weatherInfo = weatherResponse?.main?.temp;
                textviewTemp.setText(weatherInfo.toString()+" C\n"+ weatherResponse?.weather?.get(0)?.description);
            }

            @Override
            override fun onFailure(call: Call<WeatherResponse>, t: Throwable) {
                Log.e("fetchWeatherData ERR", "Hiba az api kérés során!");
            }
        });
    }
}