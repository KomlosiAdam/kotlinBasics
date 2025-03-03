package hu.griddy.kotlinbasics

import RandomUserAdapter
import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import hu.griddy.kotlinbasics.model.RUser
import hu.griddy.kotlinbasics.network.RandomUserService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.launch

class RandomUserListActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_random_users)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        lifecycleScope.launch {
            try {
                val users = fetchRandomUsers();
                if (!users.isNullOrEmpty()) {
                    onResult(users)
                } else {
                    Log.e("RandomUserListActivity hiba", "Response body is empty.")
                };
            } catch (e: Exception) {
                Log.e("RandomUserListActivity hiba", "Hiba az adatletöltésben");
            }
        }
    }

    private suspend fun fetchRandomUsers() : List<RUser>? {
        val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl("https://randomuser.me/")
            .addConverterFactory(GsonConverterFactory.create())
            .build();

        val randomUserService = retrofit.create(RandomUserService::class.java);

        return withContext(Dispatchers.IO){
            try{
                val response = randomUserService.getRandomUsers("20")
                response.results
            } catch (e: Exception){
                Log.e("UserListActivity","Error fetching user list", e)
                null
            }
        }
    }

    private fun onResult(users:List<RUser>){
        val recyclerview: RecyclerView = findViewById(R.id.randomUserListRecyclerView);
        recyclerview.layoutManager = LinearLayoutManager(this@RandomUserListActivity);
        recyclerview.adapter = RandomUserAdapter(users);
    }
}