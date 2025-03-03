package hu.griddy.kotlinbasics

import android.content.Context
import android.os.Bundle
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class GreetingActivity : AppCompatActivity() {
    private lateinit var name_EditText: EditText;
    private lateinit var greeting_Button: Button;
    private lateinit var result_TextView: TextView;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_greeting)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        name_EditText = findViewById(R.id.Name_EditText);
        greeting_Button = findViewById(R.id.Greeting_Button);
        result_TextView = findViewById(R.id.Result_TextView);

        greeting_Button.setOnClickListener {
            val name = name_EditText.text;
            hideKeyboard();
            if (name.length == 0) {
                result_TextView.text = "Adjon meg egy nevet!";
                Toast.makeText(this, "Adj meg nevet!", Toast.LENGTH_SHORT).show();
                return@setOnClickListener;
            }

            val text = when {
                name.length <= 3 -> "Szia $name! De rövid neved van!";
                name.length >= 10 -> "Szia $name! De hosszú neved van!";
                else -> "Szia $name!";
            }

            result_TextView.text = text;
            name_EditText.text.clear();
        }
    }

    private fun hideKeyboard(){
        val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(name_EditText.windowToken, 0)
    }
}