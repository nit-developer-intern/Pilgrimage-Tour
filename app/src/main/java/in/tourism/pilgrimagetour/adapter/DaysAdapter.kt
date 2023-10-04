package `in`.tourism.pilgrimagetour.adapter

import android.os.Build
import android.util.Log
import `in`.tourism.pilgrimagetour.databinding.ItineraryDaysItemBinding
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.RecycledViewPool
import com.squareup.picasso.Picasso
import `in`.tourism.pilgrimagetour.Day
import `in`.tourism.pilgrimagetour.ItineraryDay
import `in`.tourism.pilgrimagetour.R
import java.lang.Exception

class DaysAdapter(private var days: ArrayList<Day>) :
    RecyclerView.Adapter<DaysAdapter.DaysViewHolder>() {

    private val viewPool = RecycledViewPool()

    var onItemClick: ((ItineraryDay) -> Unit)? = null

    inner class DaysViewHolder(private val binding: ItineraryDaysItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        var day: Day? = null

        init {
            itemView.setOnClickListener {
                day?.let {
                    it.isExpanded = it.isExpanded.not()

                    if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.P) notifyDataSetChanged()

                    else notifyItemChanged(days.indexOf(it))
                }
            }
        }

        fun bind(item: Day) {

            day = item

            val layoutManager = LinearLayoutManager(
                binding.rV.context,
                LinearLayoutManager.HORIZONTAL,
                false
            )
            layoutManager.initialPrefetchItemCount = item.placesList!!.size

            val placeAdapter = PlaceAdapter(item.placesList)

            binding.rV.layoutManager = layoutManager
            binding.rV.adapter = placeAdapter
            binding.rV.setRecycledViewPool(viewPool)
            binding.tVDay.text = item.textDay
            binding.tVDeparture.text = item.textDeparture
            binding.tVExplanation.text = item.textExplanation
            try {
                Picasso.get().load(item.image).placeholder(R.drawable.placeholder)
                    .into(binding.imageView)
            } catch (e: Exception){
                Log.d("Picasso" , e.stackTraceToString() )
            }

            if (item.isExpanded) {
                binding.iVDownUp.setImageResource(R.drawable.ic_up)
                binding.lLExpand.visibility = View.VISIBLE
            } else {
                binding.iVDownUp.setImageResource(R.drawable.ic_down)
                binding.lLExpand.visibility = View.GONE
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DaysViewHolder =
        DaysViewHolder(
            ItineraryDaysItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )

    override fun onBindViewHolder(holder: DaysViewHolder, position: Int) {
        holder.bind(days[position])
    }

    override fun getItemCount(): Int {
        return days.size
    }
}
