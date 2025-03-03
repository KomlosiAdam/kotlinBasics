import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import hu.griddy.kotlinbasics.R
import hu.griddy.kotlinbasics.model.RUser

class RandomUserAdapter(private val userList: List<RUser>) :
    RecyclerView.Adapter<RandomUserAdapter.UserViewHolder>(){

    class UserViewHolder(list_item: View) : RecyclerView.ViewHolder(list_item) {
        val profileImage: ImageView = list_item.findViewById(R.id.imageViewProfile)
        val nameText: TextView = list_item.findViewById(R.id.textViewName)
        val emailText: TextView = list_item.findViewById(R.id.textViewEmail)
        val country: TextView = list_item.findViewById(R.id.textViewCountry)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):
            UserViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.list_item_randomuser, parent,
                false)
        return UserViewHolder(view)
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        val user = userList[position]
        holder.nameText.text = user.name.first + " " + user.name.last
        holder.emailText.text = user.email
        holder.country.text = user.location.country
        Picasso.get().load(user.picture.medium).into(holder.profileImage);
    }

    override fun getItemCount(): Int {
        return userList.size
    }
}