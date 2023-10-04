package `in`.tourism.pilgrimagetour.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import `in`.tourism.pilgrimagetour.Destination2
import `in`.tourism.pilgrimagetour.R
import `in`.tourism.pilgrimagetour.databinding.DestinationsItemBinding

class AdapterDes(
    private var items: List<Destination2>
) : RecyclerView.Adapter<AdapterDes.Destinations2ViewHolder>() {
    var onItemClick: ((Destination2) -> Unit)? = null

    inner class Destinations2ViewHolder(private val binding: DestinationsItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        init {
            itemView.setOnClickListener {
                items?.get(adapterPosition)?.let { it1 -> onItemClick?.invoke(it1) }
            }
        }

        fun bind(item: Destination2) {
            try {
                Picasso.get().load(item.image).placeholder(R.drawable.placeholder)
                    .into(binding.iVAhm)
            } catch (e: java.lang.Exception) {
                Log.d("banner", "bind: ${e.message}")
            }
            binding.tVAhmHom.text = item.textName
            binding.tVNumOfPac.text = "${item.textNumOfPac.trim()} Packages"
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Destinations2ViewHolder =
        Destinations2ViewHolder(
            DestinationsItemBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )

    override fun onBindViewHolder(holder: Destinations2ViewHolder, position: Int) {

        items?.get(position)?.let { holder.bind(it) }
    }

    override fun getItemCount(): Int {

        return items?.size ?: 0
    }
}
