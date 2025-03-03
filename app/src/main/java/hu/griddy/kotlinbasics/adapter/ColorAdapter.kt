import android.graphics.drawable.ColorDrawable
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import hu.griddy.kotlinbasics.R
import hu.griddy.kotlinbasics.model.Color
import hu.griddy.kotlinbasics.model.RUser

class ColorAdapter(private val colorList: List<Color>) :
    RecyclerView.Adapter<ColorAdapter.UserViewHolder>(){

    class UserViewHolder(list_item: View) : RecyclerView.ViewHolder(list_item) {
        val colorImage: ImageView = list_item.findViewById(R.id.colorImageView)
        val nameText: TextView = list_item.findViewById(R.id.nameText)
        val yearText: TextView = list_item.findViewById(R.id.yearText)
        val pantoneText: TextView = list_item.findViewById(R.id.pantoneText)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):
            UserViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.list_item_color, parent,
                false)
        return UserViewHolder(view)
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        val color = colorList[position];
        holder.colorImage.setBackgroundColor(android.graphics.Color.parseColor(color.color))
        holder.nameText.setText(color.name)
        holder.yearText.setText(color.year.toString())
        holder.pantoneText.setText(color.pantone_value)
    }

    override fun getItemCount(): Int {
        return colorList.size
    }
}