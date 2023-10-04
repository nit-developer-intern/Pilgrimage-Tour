package `in`.tourism.pilgrimagetour.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import `in`.tourism.pilgrimagetour.HotelsDest
import `in`.tourism.pilgrimagetour.R
import `in`.tourism.pilgrimagetour.databinding.DestinationHotelsItemBinding
import `in`.tourism.pilgrimagetour.databinding.DestinationsItemBinding

class HotelDestinationAdapter(
    private val itemsList : ArrayList<HotelsDest>, val limited: Boolean
) : RecyclerView.Adapter<HotelDestinationAdapter.HotelsDestViewHolder>() {
    var onItemClick: ((HotelsDest) -> Unit)? = null

    private val limit = 4

    inner class HotelsDestViewHolder(private val binding: DestinationHotelsItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        init {
            itemView.setOnClickListener {
                onItemClick?.invoke(itemsList[absoluteAdapterPosition])
            }
        }

        fun bind(item: HotelsDest) {

            try {
                Picasso.get().load(item.image).placeholder(R.drawable.placeholder).into(binding.iVdestination)
            } catch (e: java.lang.Exception) {
                Log.d("banner", "bind: ${e.message}")
            }

            binding.destinationText.text = item.name

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HotelsDestViewHolder {
        return HotelsDestViewHolder(
            DestinationHotelsItemBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun getItemCount(): Int {
        return if (limited) limit
        else itemsList.size
    }

    override fun onBindViewHolder(holder: HotelsDestViewHolder, position: Int) {
       holder.bind(itemsList[position]!!)
    }

}