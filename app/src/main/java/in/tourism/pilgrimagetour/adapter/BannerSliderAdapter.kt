package `in`.tourism.pilgrimagetour.adapter

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat.startActivity
import androidx.viewpager.widget.PagerAdapter
import com.google.android.material.imageview.ShapeableImageView
import com.squareup.picasso.Picasso
import `in`.tourism.pilgrimagetour.view.PackageDetailsActivity
import `in`.tourism.pilgrimagetour.R
import `in`.tourism.pilgrimagetour.SliderBanner
import kotlin.collections.ArrayList

class BannerSliderAdapter(
    private val context: Context,
    private var banners: ArrayList<SliderBanner?>,
) : PagerAdapter() {


    override fun getCount(): Int {
        return banners.size
    }

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view === `object` as ConstraintLayout
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {

        val layoutInflater =
            context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

        val itemView = layoutInflater.inflate(R.layout.slider_item, container, false)
        itemView.setOnClickListener {
            Log.d("BannerClick", "This item was clicked: $position")

            val detailsIntent = Intent(context, PackageDetailsActivity::class.java)

            detailsIntent.putExtra("detailsKey", banners[position]?.name)

            startActivity(context, detailsIntent, null)
        }

        val imageView = itemView.findViewById<View>(R.id.sIVBanner) as ShapeableImageView

        try {
            Picasso.get().load(banners[position]?.url).placeholder(R.drawable.placeholder)
                .into(imageView)
        } catch (e: Exception) {
            Log.d("BannerImage", "${e.message}")
        }

        container.addView(itemView, 0)
        return itemView
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as ConstraintLayout)
    }

    fun setImageList(newImageList: ArrayList<SliderBanner?>) {
        banners = newImageList
        notifyDataSetChanged()
    }
}
