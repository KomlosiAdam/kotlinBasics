package hu.griddy.kotlinbasics

import UserAdapter
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import hu.griddy.kotlinbasics.model.User

class UserListActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_users)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val users = listOf(
            User("Kovsács Péter","peter.kovacs@email.com",R.drawable.ic_launcher_background),
            User("Nagy Anna", "anna.nagy@email.com",R.drawable.ic_launcher_background),
            User("Szabó László", "laszlo.szabo@email.com",R.drawable.ic_launcher_background),
            User("Varga Judit","judit.varga@email.com",R.drawable.ic_launcher_background)
        )

        val recyclerview: RecyclerView = findViewById(R.id.userRecyclerView);
        recyclerview.layoutManager = LinearLayoutManager(this);
        recyclerview.adapter = UserAdapter(users);
    }
}