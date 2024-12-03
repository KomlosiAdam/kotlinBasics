package com.example.cotlinbasics

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity

private lateinit var openCalculatorButton: Button
private lateinit var udvozlesOldalraButton: Button
private lateinit var openWeatherButton: Button

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        /*ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }*/

        openCalculatorButton = findViewById(R.id.openCalculatorButton);
        udvozlesOldalraButton = findViewById(R.id.udvozlesOldalraButton);
        openWeatherButton = findViewById(R.id.openWeatherButton);

        udvozlesOldalraButton.setOnClickListener() {
            val intent = Intent(this, GreetingActivity::class.java)
            startActivity(intent)
        }

        openCalculatorButton.setOnClickListener() {
            val intent = Intent(this, CalculatorActivity::class.java)
            startActivity(intent)
        }

        openWeatherButton.setOnClickListener() {
            val intent = Intent(this, WeatherActivity::class.java)
            startActivity(intent)
        }


        data class Main(
            val temp: Float,
        )

        data class WeatherResponse(
            val main: Main,
        )//uj funkcio
    }
}