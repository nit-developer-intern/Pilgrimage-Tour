package `in`.tourism.pilgrimagetour.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import `in`.tourism.pilgrimagetour.HotelFacility
import `in`.tourism.pilgrimagetour.R

class AdapterHotelFacility(private var places: List<HotelFacility>) :
    RecyclerView.Adapter<AdapterHotelFacility.PlacesViewHolder>() {

    inner class PlacesViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        val textView: TextView
        val imageView: ImageView
//        var cross: ImageView

        init {
            imageView = view.findViewById(R.id.iVfacility)
            textView = view.findViewById(R.id.tVfacility)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlacesViewHolder {

        return PlacesViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.facilities_item, parent, false)
        )
    }

    override fun onBindViewHolder(holder: PlacesViewHolder, position: Int) {

        holder.textView.text = places[position].name

//        if (places[position].provided != true) holder.cross.visibility = View.VISIBLE

        Picasso.get().load(places[position].image).placeholder(R.drawable.placeholder)
            .into(holder.imageView)
    }

    override fun getItemCount(): Int {

        return places.size
    }
}
