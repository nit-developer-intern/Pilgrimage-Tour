package `in`.tourism.pilgrimagetour.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.firestore.DocumentSnapshot
import com.squareup.picasso.Picasso
import de.hdodenhof.circleimageview.CircleImageView
import `in`.tourism.pilgrimagetour.OnCardClickListener
import `in`.tourism.pilgrimagetour.R
import `in`.tourism.pilgrimagetour.ReviewHotel
import `in`.tourism.pilgrimagetour.databinding.HotelReviewItemBinding

class AdapterHotelReview() : RecyclerView.Adapter<AdapterHotelReview.HotelViewHolder>() {

    private var reviewsList: ArrayList<ReviewHotel?>? = null
    private var documentList: List<DocumentSnapshot>? = null
//    private var onCardClickListener: OnCardClickListener? = null

    constructor(
        documentList: List<DocumentSnapshot>?, onCardClickListener: OnCardClickListener?
    ) : this() {
        this.documentList = documentList
//        this.onCardClickListener = onCardClickListener

        reviewsList = ArrayList()
        if (!documentList.isNullOrEmpty()) {
            for (document in documentList) {
                (reviewsList as ArrayList<ReviewHotel?>).add(document.toObject(ReviewHotel::class.java))
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HotelViewHolder {

        return HotelViewHolder(
            HotelReviewItemBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: HotelViewHolder, position: Int) {

        if (reviewsList != null) {
            val noteItem = reviewsList!![position]

            if (noteItem != null) {
                holder.textReview?.text = noteItem.textReview
                holder.textCity?.text = noteItem.textCity
                holder.textTime?.text = noteItem.textTime
                holder.textHotelName?.text = noteItem.hotelName

                if(noteItem.profile != null) {
                    Picasso.get().load(noteItem.profile).placeholder(R.drawable.ic_noofperson)
                        .into(holder.profileImg)
                }

                holder.rating?.rating = noteItem.rating!!.toFloat()
                holder.textName?.text = noteItem.userName
            }
//            holder.itemView.setOnClickListener {
//                onCardClickListener?.onClick(
//                    true, documentList!![position].id
//                )
//            }
        }
    }

    override fun getItemCount(): Int {
        return if (reviewsList != null) reviewsList?.size!! else 0
    }

    class HotelViewHolder(binding: HotelReviewItemBinding) : RecyclerView.ViewHolder(binding.root) {
        var profileImg: CircleImageView ?= null
        var textReview: TextView? = null
        var rating: RatingBar? = null
        var textName: TextView? = null
        var textCity: TextView? = null
        var textHotelName: TextView? = null
        var textTime: TextView? = null

        init {
            profileImg = binding.profileImage
            textReview = binding.tVReview
            rating = binding.rBIndicator
            textCity = binding.tVCity
            textTime = binding.tVTime
            textName = binding.tVYatriName
            textHotelName = binding.tvHotelName
        }
    }
}
