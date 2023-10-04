package `in`.tourism.pilgrimagetour.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import `in`.tourism.pilgrimagetour.R

class PlaceAdapter(private var places: List<String>): RecyclerView.Adapter<PlaceAdapter.PlacesViewHolder>() {

    inner class PlacesViewHolder(view: View): RecyclerView.ViewHolder(view){

        val textView: TextView

        init {

            textView= view.findViewById(R.id.tVPlace1)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlacesViewHolder {

        return PlacesViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.days_place_item, parent, false))
    }

    override fun onBindViewHolder(holder: PlacesViewHolder, position: Int) {

        holder.textView.text= places[position]
    }

    override fun getItemCount(): Int {

        return places.size
    }

}