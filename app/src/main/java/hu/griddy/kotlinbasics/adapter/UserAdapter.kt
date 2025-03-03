import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import hu.griddy.kotlinbasics.R
import hu.griddy.kotlinbasics.model.User

class UserAdapter(private val userList: List<User>) :
    RecyclerView.Adapter<UserAdapter.UserViewHolder>(){

    class UserViewHolder(list_item: View) : RecyclerView.ViewHolder(list_item) {
        val profileImage: ImageView = list_item.findViewById(R.id.profileImage)
        val nameText: TextView = list_item.findViewById(R.id.nameText)
        val emailText: TextView = list_item.findViewById(R.id.emailText)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):
            UserViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.list_item, parent,
                false)
        return UserViewHolder(view)
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        val user = userList[position]
        holder.nameText.text = user.name
        holder.emailText.text = user.email
        holder.profileImage.setImageResource(user.profileImage)
    }

    override fun getItemCount(): Int {
        return userList.size
    }
}