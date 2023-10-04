package `in`.tourism.pilgrimagetour.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.viewpager.widget.PagerAdapter
import com.google.android.material.imageview.ShapeableImageView
import com.squareup.picasso.Picasso
import `in`.tourism.pilgrimagetour.R
import `in`.tourism.pilgrimagetour.ReviewsSliderVM
import java.lang.Exception

class ReviewsSliderAdapter(
    private val context: Context, private val reviewList: ArrayList<ReviewsSliderVM>
) : PagerAdapter() {

    override fun getCount(): Int {
        return reviewList.size
    }

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view === `object` as ConstraintLayout
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {

        val layoutInflater =
            context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val itemView = layoutInflater.inflate(R.layout.reviews_slider_item, container, false)
        val shapeableImageView = itemView.findViewById<ShapeableImageView>(R.id.sIV)
        val textView1 = itemView.findViewById<TextView>(R.id.tVReview)
        val textView2 = itemView.findViewById<TextView>(R.id.tVCusName)
        val textView3 = itemView.findViewById<TextView>(R.id.tVComment)

        textView3.text = reviewList[position]?.textComment

        try {
            Picasso.get().load(reviewList[position]?.image!!).placeholder(R.drawable.placeholder)
                .into(shapeableImageView)
        } catch (e: Exception){
            Log.d("Picasso" , e.stackTraceToString() )
        }

        textView1.text = reviewList[position]?.textReview
        textView2.text = reviewList[position]?.textName

        container.addView(itemView, 0)

        return itemView
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {

        container.removeView(`object` as ConstraintLayout)
    }
}
