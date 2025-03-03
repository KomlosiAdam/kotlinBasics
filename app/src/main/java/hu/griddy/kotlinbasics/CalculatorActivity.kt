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

class CalculatorActivity : AppCompatActivity() {
    private lateinit var SumBtn:Button;
    private lateinit var ClearBtn:Button;
    private lateinit var FirstNumEditText:EditText;
    private lateinit var SecondNumEditText:EditText;
    private lateinit var ResultTextView:TextView;
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_calculator)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        SumBtn = findViewById(R.id.SUM_Button);
        ClearBtn = findViewById(R.id.Clear_Button);
        FirstNumEditText = findViewById(R.id.NumberOne_EditText);
        SecondNumEditText = findViewById(R.id.NumberTwo_EditText);
        ResultTextView = findViewById(R.id.CalcResult_TextView);

        SumBtn.setOnClickListener{
            val sum:Int;
            hideKeyboard();

            try {
                val a = FirstNumEditText.text.toString();
                val b = SecondNumEditText.text.toString();
                sum = a.toInt() + b.toInt();
            } catch (nfe:NumberFormatException){
                Toast.makeText(this,"Kérem számokat adjon meg!", Toast.LENGTH_SHORT).show();
                return@setOnClickListener;
            }

            ResultTextView.setText(sum.toString());
        }

        ClearBtn.setOnClickListener{
            FirstNumEditText.setText("");
            SecondNumEditText.setText("");
            ResultTextView.setText("-");
            hideKeyboard();
        }
    }

    private fun hideKeyboard(){
        val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(FirstNumEditText.windowToken, 0)
        imm.hideSoftInputFromWindow(SecondNumEditText.windowToken, 0)
    }
}