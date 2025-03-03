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
import androidx.lifecycle.lifecycleScope
import hu.griddy.kotlinbasics.model.WeatherResponse
import hu.griddy.kotlinbasics.network.StudentCountService
import hu.griddy.kotlinbasics.network.WeatherService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class StudentsActivity : AppCompatActivity() {
    private lateinit var textviewUserCount:TextView;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_students)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        textviewUserCount = findViewById(R.id.userCountTextView);

        lifecycleScope.launch {
            try {
                val count = fetchUserCount();
                textviewUserCount.setText(count.toString());
            } catch (e: Exception) {
                Log.e("StudentsActivity","Failed to get student count."+e.toString())
            }
        }
    }

    private suspend fun fetchUserCount() : Int{
        val retrofit:Retrofit = Retrofit.Builder()
            .baseUrl("https://banki13.komarom.net/")
            .addConverterFactory(GsonConverterFactory.create())
            .build();

        val studentcountService = retrofit.create(StudentCountService::class.java);

        return withContext(Dispatchers.IO){
            try{
                val response = studentcountService.getStudentCount("count");
                response.count
            } catch (e: Exception){
                Log.e("UserListActivity","Error fetching user list", e)
                null
            }!!
        }
    }
}