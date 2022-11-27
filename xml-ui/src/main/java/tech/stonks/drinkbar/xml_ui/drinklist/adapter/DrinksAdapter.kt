package tech.stonks.drinkbar.xml_ui.drinklist.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import tech.stonks.drinkbar.xml_ui.R
import tech.stonks.drinkbar.xml_ui.drinklist.model.DrinkUiModel

class DrinksAdapter : RecyclerView.Adapter<DrinksAdapter.DrinkViewHolder>() {
    var items: List<DrinkUiModel> = emptyList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DrinkViewHolder {
        return DrinkViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_drink, parent, false)
        )
    }

    override fun onBindViewHolder(holder: DrinkViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int {
        return items.size
    }

    inner class DrinkViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val nameField: TextView by lazy { view.findViewById<TextView>(R.id.drink_name) }
        private val thumbnailImage: ImageView by lazy { view.findViewById(R.id.thumbnail_image) }

        fun bind(drink: DrinkUiModel) {
            nameField.text = drink.name
            Glide.with(itemView.context)
                .load(drink.thumbnail)
                .into(thumbnailImage)
        }
    }
}