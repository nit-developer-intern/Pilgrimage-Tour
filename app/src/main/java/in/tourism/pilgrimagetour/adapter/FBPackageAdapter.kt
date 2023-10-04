package `in`.tourism.pilgrimagetour.adapter

import android.graphics.Paint
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.firestore.DocumentSnapshot
import com.squareup.picasso.Picasso
import `in`.tourism.pilgrimagetour.OnCardClickListener2
import `in`.tourism.pilgrimagetour.R
import `in`.tourism.pilgrimagetour.TravelPac
import `in`.tourism.pilgrimagetour.databinding.BusItemBinding
import `in`.tourism.pilgrimagetour.databinding.ChardhamTrainItemBinding
import `in`.tourism.pilgrimagetour.databinding.HelicopterItemBinding
import `in`.tourism.pilgrimagetour.databinding.TrendingPackagesItemBinding
import java.text.NumberFormat
import java.util.Currency
import java.util.Locale

class FBPackageAdapter() : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private var onCardClickListener2: OnCardClickListener2? = null
    private var travelPacsList: List<TravelPac?>? = null
    private var documentList: List<DocumentSnapshot>? = null
    private var numberFormat = NumberFormat.getCurrencyInstance(Locale("en", "in"))

    constructor(
//        documentList: List<DocumentSnapshot>?,
        travelPacsList: List<TravelPac?>?,
        onCardClickListener2: OnCardClickListener2
    ) : this() {

//        this.documentList = documentList
        this.onCardClickListener2 = onCardClickListener2
        this.travelPacsList = travelPacsList
        numberFormat.maximumFractionDigits = 0
        numberFormat.currency = Currency.getInstance("INR")
    }

    inner class PackagesViewHolder(private val binding: TrendingPackagesItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: TravelPac) {

            try {

                Picasso.get().load(item.banner).placeholder(R.drawable.placeholder).into(binding.iV)
            } catch (e: java.lang.Exception) {
                Log.d("banner", "bind: ${e.message}")
            }

            binding.tVDiscounted.text = numberFormat.format(item.discountedPrice)
            binding.tVMrp.text = numberFormat.format(item.mrp)
            binding.tVMrp.paintFlags = Paint.STRIKE_THRU_TEXT_FLAG

            if (item.discountedPrice == 0) {

                binding.tVMrp.text = "Price On"
                binding.tVDiscounted.text = "Request"
            }

            binding.tVName.text = item.name
            binding.tVDays.text = item.days
            binding.tVNights.text = item.nights
            binding.tVName.postDelayed(Runnable {
                binding.tVName.isSelected = true
            }, 4000)

            if (item.daysList?.isEmpty() == true || item.daysList == null) binding.tVDetails.text =
                "Enquire Now"

        }
    }

    inner class HelicopterViewHolder(private val binding: HelicopterItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        /*
                    init {
                        itemView.setOnClickListener {
                            onItemClick?.invoke(items[adapterPosition])
                        }
                    }
            */
        fun bind(item: TravelPac) {

            try {

                Picasso.get().load(item.banner).placeholder(R.drawable.placeholder)
                    .into(binding.iV)
            } catch (e: java.lang.Exception) {
                Log.d("banner", "bind: ${e.message}")
            }

            binding.tVDiscounted.text = numberFormat.format(item.discountedPrice)
            binding.tVMrp.text = numberFormat.format(item.mrp)
            binding.tVMrp.paintFlags = Paint.STRIKE_THRU_TEXT_FLAG

            if (item.discountedPrice == 0) {

                binding.tVMrp.text = "Price On"
                binding.tVDiscounted.text = "Request"
            }

            binding.tVName.text = item.name
            binding.tVDays.text = item.days
            binding.tVNights.text = item.nights
            binding.tVName.postDelayed(Runnable {
                binding.tVName.isSelected = true
            }, 4000)

            if (item.daysList?.isEmpty() == true || item.daysList == null) binding.tVDetails.text =
                "Enquire Now"
        }
    }

    inner class BusViewHolder(private val binding: BusItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        /*
                        init {
                            itemView.setOnClickListener {
                                onItemClick?.invoke(items[adapterPosition])
                            }
                        }
                */
        fun bind(item: TravelPac) {

            try {

                Picasso.get().load(item.banner).placeholder(R.drawable.placeholder)
                    .into(binding.iV)
            } catch (e: java.lang.Exception) {
                Log.d("banner", "bind: ${e.message}")
            }

            binding.tVDiscounted.text = numberFormat.format(item.discountedPrice)
            binding.tVMrp.text = numberFormat.format(item.mrp)
            binding.tVMrp.paintFlags = Paint.STRIKE_THRU_TEXT_FLAG

            if (item.discountedPrice == 0) {

                binding.tVMrp.text = "Price On"
                binding.tVDiscounted.text = "Request"
            }

            binding.tVName.text = item.name
            binding.tVDays.text = item.days
            binding.tVNights.text = item.nights
            binding.tVName.postDelayed(Runnable {
                binding.tVName.isSelected = true
            }, 4000)

            if (item.daysList?.isEmpty() == true || item.daysList == null) binding.tVDetails.text =
                "Enquire Now"

        }
    }

    inner class TrainViewHolder(private val binding: ChardhamTrainItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        /*
                            init {
                                itemView.setOnClickListener {
                                    onItemClick?.invoke(items[adapterPosition])
                                }
                            }
                    */
        fun bind(item: TravelPac) {

            try {

                Picasso.get().load(item.banner).placeholder(R.drawable.placeholder)
                    .into(binding.iV)
            } catch (e: java.lang.Exception) {
                Log.d("banner", "bind: ${e.message}")
            }

            binding.tVDiscounted.text = numberFormat.format(item.discountedPrice)
            binding.tVMrp.text = numberFormat.format(item.mrp)
            binding.tVMrp.paintFlags = Paint.STRIKE_THRU_TEXT_FLAG

            if (item.discountedPrice == 0) {

                binding.tVMrp.text = "Price On"
                binding.tVDiscounted.text = "Request"
            }

            binding.tVName.text = item.name
            binding.tVDays.text = item.days
            binding.tVNights.text = item.nights
            binding.tVName.postDelayed(Runnable {
                binding.tVName.isSelected = true
            }, 4000)

            if (item.daysList?.isEmpty() == true || item.daysList == null) binding.tVDetails.text =
                "Enquire Now"

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {

            VIEW_TYPE_1 -> PackagesViewHolder(
                TrendingPackagesItemBinding.inflate(
                    LayoutInflater.from(parent.context), parent, false
                )
            )

            VIEW_TYPE_2 -> HelicopterViewHolder(
                HelicopterItemBinding.inflate(
                    LayoutInflater.from(parent.context), parent, false
                )
            )

            VIEW_TYPE_3 -> BusViewHolder(
                BusItemBinding.inflate(
                    LayoutInflater.from(parent.context), parent, false
                )
            )

            else -> TrainViewHolder(
                ChardhamTrainItemBinding.inflate(
                    LayoutInflater.from(parent.context), parent, false
                )
            )
        }
    }

    override fun getItemCount(): Int {
        return if (travelPacsList != null) travelPacsList?.size!! else 0
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

        if(travelPacsList != null) {

            val noteItem = travelPacsList!![position]

            when (travelPacsList!![position]?.viewType) {

                VIEW_TYPE_1 -> {

                    if (noteItem != null) {
                        (holder as PackagesViewHolder).bind(travelPacsList!![position]!!)
                    }
                    (holder as PackagesViewHolder).itemView.setOnClickListener {
                        if(noteItem?.daysList?.isEmpty() == true)
                            onCardClickListener2?.onClick(
                                false , noteItem
                            )
                        else
                            onCardClickListener2?.onClick(true , noteItem)
                    }
                }

                VIEW_TYPE_2 -> {

                    if (noteItem != null) {
                        (holder as HelicopterViewHolder).bind(travelPacsList!![position]!!)
                    }
                    (holder as HelicopterViewHolder).itemView.setOnClickListener {
                        if(noteItem?.daysList?.isEmpty() == true)
                            onCardClickListener2?.onClick(
                                false , noteItem
                            )
                        else
                            onCardClickListener2?.onClick(true , noteItem)
                    }
                }

                VIEW_TYPE_3 -> {

                    if (noteItem != null) {
                        (holder as BusViewHolder).bind(travelPacsList!![position]!!)
                    }
                    (holder as BusViewHolder).itemView.setOnClickListener {
                        if(noteItem?.daysList?.isEmpty() == true)
                            onCardClickListener2?.onClick(
                                false , noteItem
                            )
                        else
                            onCardClickListener2?.onClick(true , noteItem)
                    }
                }
                else ->{

                    if (noteItem != null) {
                        (holder as TrainViewHolder).bind(travelPacsList!![position]!!)
                    }
                    (holder as TrainViewHolder).itemView.setOnClickListener {
                        if(noteItem?.daysList?.isEmpty() == true)
                            onCardClickListener2?.onClick(
                                false , noteItem
                            )
                        else
                            onCardClickListener2?.onClick(true , noteItem)
                    }
                }

            }
        }
    }

    override fun getItemViewType(position: Int): Int {

        return travelPacsList?.getOrNull(position)?.viewType ?: 0
    }

    fun addData(list: List<TravelPac?>?) {
        travelPacsList = list
        notifyDataSetChanged()
    }

    companion object {

        const val VIEW_TYPE_1 = 1
        const val VIEW_TYPE_2 = 2
        const val VIEW_TYPE_3 = 3
        const val VIEW_TYPE_4 = 4
    }
}