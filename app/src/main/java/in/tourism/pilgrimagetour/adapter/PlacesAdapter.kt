package `in`.tourism.pilgrimagetour.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import `in`.tourism.pilgrimagetour.R

class PlacesToVisitAdapter(
    private val context: Context,
    private val placesList: List<String>?
) : RecyclerView.Adapter<PlacesToVisitAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_places_destination , parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val place = placesList?.get(position)
        holder.bind(place.toString())
    }

    override fun getItemCount(): Int {
        return placesList?.size ?: 0
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val placeTextView: TextView = itemView.findViewById(R.id.tVPlaceToVisit)

        fun bind(place: String) {
            placeTextView.text = place
        }
    }
}