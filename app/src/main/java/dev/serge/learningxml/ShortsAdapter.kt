package dev.serge.learningxml

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ShortsAdapter(
    private val list: List<ShortsData>
): RecyclerView.Adapter<ShortsAdapter.ShortsViewHolder>() {

    class ShortsViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val image = view.findViewById<ImageView>(R.id.shortsImage)
        val title = view.findViewById<TextView>(R.id.shortsTitle)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShortsViewHolder {

        val view = LayoutInflater.from(parent.context)
            .inflate(
                R.layout.item_shorts,
                parent,
                false
            )

        return ShortsViewHolder(view)
    }

    override fun onBindViewHolder(holder: ShortsViewHolder, position: Int) {

        val item = list[position]

        holder.image.setImageResource(item.image)
        holder.title.text = item.title
    }

    override fun getItemCount(): Int {
        return list.size
    }
}