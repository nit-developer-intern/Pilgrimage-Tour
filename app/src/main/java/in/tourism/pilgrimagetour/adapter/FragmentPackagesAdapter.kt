package `in`.tourism.pilgrimagetour.adapter

import `in`.tourism.pilgrimagetour.databinding.PackagesHelicopterItemBinding
import `in`.tourism.pilgrimagetour.databinding.PackagesListItemBinding
import `in`.tourism.pilgrimagetour.databinding.PackagesTrainItemBinding
import android.annotation.SuppressLint
import android.graphics.Paint
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import `in`.tourism.pilgrimagetour.OnCardClickListener2
import `in`.tourism.pilgrimagetour.R
import `in`.tourism.pilgrimagetour.TravelPac
import `in`.tourism.pilgrimagetour.TravelPackage
import java.text.NumberFormat
import java.util.Currency
import java.util.Locale

class FragmentPackagesAdapter() :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    var onItemClick: ((TravelPackage) -> Unit)? = null
    private var onCardClickListener: OnCardClickListener2? = null
    private var travelPacsList: ArrayList<TravelPac?>? = null
    private var numberFormat = NumberFormat.getCurrencyInstance(Locale("en", "in"))

    constructor(onCardClickListener: OnCardClickListener2, travelPacsList: ArrayList<TravelPac?>?) :
            this() {
//
        this.travelPacsList = travelPacsList
        this.onCardClickListener = onCardClickListener
        /*travelPacsList = ArrayList()
        if (documentList != null && documentList.isNotEmpty()) {
            for (document in documentList) {
                (travelPacsList as java.util.ArrayList<TravelPac?>).add(document.toObject(TravelPac::class.java))
            }
        }*/
        numberFormat.maximumFractionDigits = 0
        numberFormat.currency = Currency.getInstance("INR")
    }

    inner class FragmentPackagesViewHolder(private val binding: PackagesListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

//        init {
////            itemView.setOnClickListener {
////                onItemClick?.invoke(items[adapterPosition])
////            }
//        }
//
//        fun bind(item: TravelPackage) {
//
//            binding.iV.setImageResource(item.image)
//            binding.tVDiscounted.text = item.textDiscounted
//            binding.tVMrp.text = item.textMrp
//            binding.tVMrp.paintFlags = Paint.STRIKE_THRU_TEXT_FLAG
//            binding.tVName.text = item.textName
//            binding.tVDays.text = item.textDays
//            binding.tVNights.text = item.textNights
//            binding.tVDetails.text = item.textButton
//            binding.tVName.maxLines = 1
//            binding.tVName.postDelayed(Runnable {
//                binding.tVName.isSelected = true
//            }, 3000)
//        }

        var titleText: TextView? = null
        var imageView: ImageView? = null
        var textMrp: TextView? = null
        var textDiscounted: TextView? = null
        var textDays: TextView? = null
        var textNights: TextView? = null
        var textDetails: TextView? = null

        init {
            titleText = binding.tVName
            imageView = binding.iV
            textMrp = binding.tVMrp
            textDiscounted = binding.tVDiscounted
            textDays = binding.tVDays
            textNights = binding.tVNights
            textDetails = binding.tVDetails
        }
    }

    inner class FragmentPackagesHelicopterViewHolder(private val binding: PackagesHelicopterItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

//        init {
//            itemView.setOnClickListener {
//                onItemClick?.invoke(items[adapterPosition])
//            }
//        }
//
//        fun bind(item: TravelPackage) {
//
//            binding.iV.setImageResource(item.image)
//            binding.tVDiscounted.text = item.textDiscounted
//            binding.tVMrp.text = item.textMrp
//            binding.tVMrp.paintFlags = Paint.STRIKE_THRU_TEXT_FLAG
//            binding.tVName.text = item.textName
//            binding.tVDays.text = item.textDays
//            binding.tVNights.text = item.textNights
//            binding.tVDetails.text = item.textButton
//            binding.tVName.maxLines = 1
//            binding.tVName.postDelayed(Runnable {
//                binding.tVName.isSelected = true
//            }, 3000)
//        }


        var titleText: TextView? = null
        var imageView: ImageView? = null
        var textMrp: TextView? = null
        var textDiscounted: TextView? = null
        var textDays: TextView? = null
        var textNights: TextView? = null
        var textDetails: TextView? = null

        init {
            titleText = binding.tVName
            imageView = binding.iV
            textMrp = binding.tVMrp
            textDiscounted = binding.tVDiscounted
            textDays = binding.tVDays
            textNights = binding.tVNights
            textDetails = binding.tVDetails
        }
    }


    inner class FragmentPackagesTrainViewHolder(private val binding: PackagesTrainItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

//        init {
//            itemView.setOnClickListener {
//                onItemClick?.invoke(items[adapterPosition])
//            }
//        }
//
//        fun bind(item: TravelPackage) {
//
//            binding.iV.setImageResource(item.image)
//            binding.tVDiscounted.text = item.textDiscounted
//            binding.tVMrp.text = item.textMrp
//            binding.tVMrp.paintFlags = Paint.STRIKE_THRU_TEXT_FLAG
//            binding.tVName.text = item.textName
//            binding.tVDays.text = item.textDays
//            binding.tVNights.text = item.textNights
//            binding.tVDetails.text = item.textButton
//            binding.tVName.maxLines = 1
//            binding.tVName.postDelayed(Runnable {
//                binding.tVName.isSelected = true
//            }, 3000)
//        }

        var titleText: TextView? = null
        var imageView: ImageView? = null
        var textMrp: TextView? = null
        var textDiscounted: TextView? = null
        var textDays: TextView? = null
        var textNights: TextView? = null
        var textDetails: TextView? = null

        init {
            titleText = binding.tVName
            imageView = binding.iV
            textMrp = binding.tVMrp
            textDiscounted = binding.tVDiscounted
            textDays = binding.tVDays
            textNights = binding.tVNights
            textDetails = binding.tVDetails

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        return when (viewType) {

            VIEW_TYPE_1 -> FragmentPackagesViewHolder(
                PackagesListItemBinding.inflate(
                    LayoutInflater.from(parent.context), parent, false
                )
            )
            VIEW_TYPE_2 -> FragmentPackagesHelicopterViewHolder(
                PackagesHelicopterItemBinding.inflate(
                    LayoutInflater.from(parent.context), parent, false
                )
            )

            else -> FragmentPackagesTrainViewHolder(
                PackagesTrainItemBinding.inflate(
                    LayoutInflater.from(
                        parent.context
                    ), parent, false
                )
            )
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

        if (travelPacsList != null) {

            val noteItem = travelPacsList!![position]

            when (travelPacsList!![position]?.viewType) {

            VIEW_TYPE_1 -> {

                if(noteItem != null){
                    (holder as FragmentPackagesViewHolder).titleText?.text = noteItem.name
                    holder.textDays?.text = noteItem.days
                    holder.textMrp?.text = numberFormat.format(noteItem.mrp)
                    holder.textMrp?.paintFlags = Paint.STRIKE_THRU_TEXT_FLAG
                    holder.textNights?.text = noteItem.nights
                    holder.textDiscounted?.text = numberFormat.format(noteItem.discountedPrice)

                    if (noteItem.discountedPrice == 0) {

                        holder.textMrp?.text = "Price On"
                        holder.textDiscounted?.text = "Request"
                    }

                    try {

                        Picasso.get().load(noteItem.banner).placeholder(R.drawable.placeholder)
                            .into(holder.imageView)
                    } catch (e: java.lang.Exception) {
                        Log.d("banner", "onBindViewHolder: ${e.message}")
                    }

                    if(noteItem.daysList?.isEmpty() == true)
                        holder.textDetails?.text = "Enquire Now"
                    else
                        holder.textDetails?.text = "View details"
                }
                (holder as FragmentPackagesViewHolder).itemView.setOnClickListener {

                    if (noteItem?.daysList?.isEmpty() == true) onCardClickListener?.onClick(
                        detailed = false, travelPac = noteItem
                    )
                    else onCardClickListener?.onClick(
                        detailed = true,
//                            documentList!![position].id
                        travelPac = noteItem
                    )
                }
             }
                VIEW_TYPE_2 -> {

                    if (noteItem != null) {
                        (holder as FragmentPackagesHelicopterViewHolder).titleText?.text = noteItem.name
                        holder.textDays?.text = noteItem.days
                        holder.textMrp?.text = numberFormat.format(noteItem.mrp)
                        holder.textMrp?.paintFlags = Paint.STRIKE_THRU_TEXT_FLAG
                        holder.textNights?.text = noteItem.nights
                        holder.textDiscounted?.text = numberFormat.format(noteItem.discountedPrice)

                        if (noteItem.discountedPrice == 0) {

                            holder.textMrp?.text = "Price On"
                            holder.textDiscounted?.text = "Request"
                        }

                        try {

                            Picasso.get().load(noteItem.banner).placeholder(R.drawable.placeholder)
                                .into(holder.imageView)
                        } catch (e: java.lang.Exception) {
                            Log.d("banner", "onBindViewHolder: ${e.message}")
                        }

                        if (noteItem.daysList?.isEmpty() == true)
                            holder.textDetails?.text = "Enquire Now"
                        else
                            holder.textDetails?.text = "View Details"

                    }
                    (holder as FragmentPackagesHelicopterViewHolder).itemView.setOnClickListener {
                        onCardClickListener?.onClick(
                            true,
//                            documentList!![position].id
                            travelPac = noteItem
                        )
                        if (noteItem?.daysList?.isEmpty() == true) onCardClickListener?.onClick(
                            false, travelPac = noteItem
                        )

                    }

                }
                else -> {

                    if (noteItem != null) {
                        (holder as FragmentPackagesTrainViewHolder).titleText?.text = noteItem.name
                        /*
                                                holder.titleText?.postDelayed(Runnable {
                                                    holder.titleText?.isSelected = true
                                                }, 3000)
                        */
                        holder.textDays?.text = noteItem.days
                        holder.textMrp?.text = numberFormat.format(noteItem.mrp)
                        holder.textMrp?.paintFlags = Paint.STRIKE_THRU_TEXT_FLAG
                        holder.textNights?.text = noteItem.nights
                        holder.textDiscounted?.text = numberFormat.format(noteItem.discountedPrice)

                        if (noteItem.discountedPrice == 0) {

                            holder.textMrp?.text = "Price On"
                            holder.textDiscounted?.text = "Request"
                        }

                        try {

                            Picasso.get().load(noteItem.banner).placeholder(R.drawable.placeholder)
                                .into(holder.imageView)
                        } catch (e: java.lang.Exception) {
                            Log.d("banner", "onBindViewHolder: ${e.message}")
                        }

                        if (noteItem.daysList?.isEmpty() == true) holder.textDetails?.text =
                            "Enquire Now" else holder.textDetails?.text = "View Details"

                    }
                    (holder as FragmentPackagesTrainViewHolder).itemView.setOnClickListener {
                        onCardClickListener?.onClick(
                            true,
//                            documentList!![position].id
                            travelPac = noteItem
                        )
                        if (noteItem?.daysList?.isEmpty() == true) onCardClickListener?.onClick(
                            false, travelPac = noteItem
                        )
                    }
                }
            }
        }
    }

    override fun getItemViewType(position: Int): Int {

        return travelPacsList?.getOrNull(position)?.viewType ?: 0
    }

    override fun getItemCount(): Int {

        return if (travelPacsList != null) travelPacsList?.size!! else 0
    }

    @SuppressLint("NotifyDataSetChanged")
    fun addData(list: ArrayList<TravelPac?>?) {

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
