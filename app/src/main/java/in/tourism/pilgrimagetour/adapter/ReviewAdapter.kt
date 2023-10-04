package `in`.tourism.pilgrimagetour.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.RatingBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.firestore.DocumentSnapshot
import `in`.tourism.pilgrimagetour.OnCardClickListener
import `in`.tourism.pilgrimagetour.Review
import `in`.tourism.pilgrimagetour.databinding.ReviewItemBinding

class ReviewAdapter() : RecyclerView.Adapter<ReviewAdapter.ReviewViewHolder>() {

    private var reviewArrayList: ArrayList<Review?>? = null
    private var documentList: List<DocumentSnapshot>? = null
    private var onCardClickListener: OnCardClickListener? = null

    constructor(
        documentList: List<DocumentSnapshot>?, onCardClickListener: OnCardClickListener?) : this() {
        this.documentList = documentList
        this.onCardClickListener = onCardClickListener

        reviewArrayList = ArrayList()
        if(!documentList.isNullOrEmpty()){
            for (document in documentList){
                (reviewArrayList as ArrayList<Review?>).add(
                    document.toObject(Review::class.java)
                )
            }
        }
        }

    class ReviewViewHolder(binding: ReviewItemBinding) : RecyclerView.ViewHolder(binding.root) {

        var textReview: TextView? = null
        var rating: RatingBar? = null
        var textName: TextView? = null
        var textCity: TextView? = null
        var textPackageName: TextView? = null
        var textTime: TextView? = null

        init {
            textReview = binding.tVReview
            rating = binding.rBIndicator
            textName = binding.tVName
            textCity = binding.tVCity
            textPackageName = binding.tvPackageName
            textTime = binding.tVTime
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReviewViewHolder {
        return ReviewViewHolder(
            ReviewItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: ReviewViewHolder, position: Int) {
      if(reviewArrayList != null) {
          val noteitem = reviewArrayList!![position]

          if(noteitem != null){
              holder.textPackageName?.text = noteitem.textPackageName
              holder.textName?.text = noteitem.textName
              holder.textCity?.text = noteitem.textCity
              holder.rating?.rating = noteitem.rating!!.toFloat()
              holder.textReview?.text = noteitem.textReview
              holder.textTime?.text = noteitem.textTime
          }
          holder.itemView.setOnClickListener {
              onCardClickListener?.onClick(
                  true, documentList!![position].id
              )
          }
       }
    }

    override fun getItemCount(): Int {
        return reviewArrayList?.size ?: 0
    }


}