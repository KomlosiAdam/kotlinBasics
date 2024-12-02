package com.example.cotlinbasics

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity

private lateinit var elsoSzam: EditText;
private lateinit var masodikSzam: EditText;
private lateinit var osszeadas: Button;
private lateinit var torles: Button;
private lateinit var eredmeny: TextView;

class CalculatorActivity : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.calculator)

        elsoSzam = findViewById(R.id.elsoSzam);
        masodikSzam = findViewById(R.id.masodikSzam);
        osszeadas = findViewById(R.id.osszeadas);
        torles = findViewById(R.id.torles);
        eredmeny = findViewById(R.id.eredmeny);

        osszeadas.setOnClickListener {
            if (elsoSzam.text.isNotEmpty() && masodikSzam.text.isNotEmpty()) {
                val szam1 = elsoSzam.text.toString().toDoubleOrNull()
                val szam2 = masodikSzam.text.toString().toDoubleOrNull()

                if (szam1 != null && szam2 != null) {
                    val osszeg = szam1 + szam2
                    eredmeny.text = "Eredmény: "+osszeg.toString()
                } else {
                    Toast.makeText(this, "Kérlek adj meg érvényes számokat!", Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(this, "Kérlek add meg a számokat!", Toast.LENGTH_SHORT).show()
            }
        }


        torles.setOnClickListener() {
            elsoSzam.setText("");
            masodikSzam.setText("");
            eredmeny.setText("Eredmény: -")
        }
    }
}
