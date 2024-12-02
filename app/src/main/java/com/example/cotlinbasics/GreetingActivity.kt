package com.example.cotlinbasics

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity

private lateinit var nameEditText: EditText
private lateinit var greetButton: Button
private lateinit var resultTextView: TextView

class GreetingActivity : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_greeting)
        /*ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }*/

        nameEditText = findViewById(R.id.nameEditText);
        greetButton = findViewById(R.id.greetButton);
        resultTextView = findViewById(R.id.resultTextView);

        greetButton.setOnClickListener() {
            val name = nameEditText.text
            if(name.isNotEmpty()) {
                val greetingText = when {
                    name.length<=3 -> "Hello " + name + ", de rövid neved van"
                    name.length>=10 -> "Hello " + name + ", de hosszú neved van"
                    else -> "Hello " + name
                }
                resultTextView.setText(greetingText)

            } else {
                Toast.makeText(this, "Kérlek add meg a neved!", Toast.LENGTH_SHORT).show()
            }
        }
    }
}