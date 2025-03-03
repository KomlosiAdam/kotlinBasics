package hu.griddy.kotlinbasics

import ColorAdapter
import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

import androidx.lifecycle.lifecycleScope
import hu.griddy.kotlinbasics.model.Color
import hu.griddy.kotlinbasics.network.ColorService
import kotlinx.coroutines.launch

class ColorListActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_color)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        lifecycleScope.launch {
            try {
                val colors = fetchColors();
                if (!colors.isNullOrEmpty()) {
                    onResult(colors)
                } else {
                    Log.e("ColorListActivity hiba", "Response body is empty.")
                };
            } catch (e: Exception) {
                Log.e("ColorListActivity hiba", "Hiba az adatletöltésben");
            }
        }
    }

    private suspend fun fetchColors() : List<Color>? {
        val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl("https://reqres.in/")
            .addConverterFactory(GsonConverterFactory.create())
            .build();

        val colorService = retrofit.create(ColorService::class.java);

        return withContext(Dispatchers.IO){
            try{
                val response = colorService.getColors()
                response.data
            } catch (e: Exception){
                Log.e("ColorListActivity","Error fetching user list", e)
                null
            }
        }
    }

    private fun onResult(colors:List<Color>){
        val recyclerview: RecyclerView = findViewById(R.id.colorRecyclerView);
        recyclerview.layoutManager = LinearLayoutManager(this@ColorListActivity);
        recyclerview.adapter = ColorAdapter(colors);
    }
}