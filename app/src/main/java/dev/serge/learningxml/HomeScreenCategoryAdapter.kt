package dev.serge.learningxml

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class HomeScreenCategoryAdapter(
    private val list: List<Category>,
    private val onClick: (Category) -> Unit
): RecyclerView.Adapter<HomeScreenCategoryAdapter.CategoryViewHolder>() {

    class CategoryViewHolder(view: View): RecyclerView.ViewHolder(view) {

        val icon: ImageView = view.findViewById(R.id.imgIcon)
        val title: TextView = view.findViewById(R.id.txtTitle)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {

        val  view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_category, parent, false)

        return CategoryViewHolder(view)
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {

        val item = list[position]

        holder.title.text = item.title
        holder.icon.setImageResource(item.icon)
        holder.itemView.setOnClickListener {
            onClick(item)
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }
}