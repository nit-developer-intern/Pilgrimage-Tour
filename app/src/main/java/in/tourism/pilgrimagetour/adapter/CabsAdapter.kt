package `in`.tourism.pilgrimagetour.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import `in`.tourism.pilgrimagetour.Cabs
import `in`.tourism.pilgrimagetour.databinding.CabsItemBinding
import `in`.tourism.pilgrimagetour.view.CabSliderAdapter
import java.util.*
import kotlin.collections.ArrayList

private const val sliderDuration : Long= 5 * 1000

class CabsAdapter(
    private val context: Context,
    private var cabsList: ArrayList<Cabs>,
) : RecyclerView.Adapter<CabsAdapter.CabsViewHolder>() {

    var onItemClick: ((Cabs) -> Unit)? = null

    inner class CabsViewHolder(private val binding: CabsItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        init {
            itemView.setOnClickListener {
                onItemClick?.invoke(cabsList[absoluteAdapterPosition])
            }
        }

            fun bind(cab: Cabs){

            binding.tVModelName.text = cab.carModel

                val sliderAdapter = CabSliderAdapter(context, cab.images!!)
                binding.vPCabsSlider.adapter = sliderAdapter

                binding.tVNumberOfSeats.text = cab.totalSeats
                binding.tVNumberOfMEmbers.text = cab.totalMembers
                binding.tVTypeOfGear.text = cab.mode


                val handler = android.os.Handler()
                Timer().schedule(object : TimerTask() {

                    override fun run() {
                        handler.post {
                            if(binding.vPCabsSlider.currentItem < cabsList.size-1){
                                binding.vPCabsSlider.currentItem = binding.vPCabsSlider.currentItem + 1
                            } else{
                                binding.vPCabsSlider.currentItem = 0
                            }
                        }
                    }


                }, sliderDuration)

                binding.tLSliderDots.setupWithViewPager(binding.vPCabsSlider, true)
            }

        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CabsViewHolder {
        return CabsViewHolder(
            CabsItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return cabsList.size
    }

    override fun onBindViewHolder(holder: CabsViewHolder, position: Int) {
        holder.bind(cabsList[position])
    }

}