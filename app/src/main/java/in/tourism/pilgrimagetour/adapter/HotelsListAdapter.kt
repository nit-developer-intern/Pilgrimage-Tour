package `in`.tourism.pilgrimagetour.adapter

import android.content.Context
import android.content.Intent
import android.graphics.Paint
import android.os.Handler
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager.widget.PagerAdapter
import androidx.viewpager.widget.ViewPager
import com.google.android.material.imageview.ShapeableImageView
import com.google.android.material.tabs.TabLayout
import com.squareup.picasso.Picasso
import `in`.tourism.pilgrimagetour.HotelDetails
import `in`.tourism.pilgrimagetour.R
import `in`.tourism.pilgrimagetour.databinding.HotelItemBinding
import `in`.tourism.pilgrimagetour.view.HotelDetailsActivity
import java.text.NumberFormat
import java.util.Currency
import java.util.Locale
import java.util.Timer
import java.util.TimerTask

private const val sliderTimerDuration: Long = 5 * 1000


class HotelsListAdapter() : RecyclerView.Adapter<HotelsListAdapter.HotelsViewHolder>() {

    private var hotelsList: ArrayList<HotelDetails?>? = null
    private var numberFormat = NumberFormat.getCurrencyInstance(Locale("en", "in"))
    private var context: Context? = null
    private lateinit var handler: Handler

    var onItemClick: ((HotelDetails) -> Unit)? = null

    constructor(
        context: Context, hotelsList: ArrayList<HotelDetails?>?
    ) : this() {

        this.hotelsList = hotelsList
        this.context = context

        numberFormat.maximumFractionDigits = 0
        numberFormat.currency = Currency.getInstance("INR")
    }

    inner class HotelsViewHolder(binding: HotelItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        var titleText: TextView? = null
        var viewPager: ViewPager? = null
        var textMrp: TextView? = null
        var textDiscounted: TextView? = null
        var textDetails: TextView? = null
        var textAddress: TextView? = null
        var tabLayout: TabLayout? = null

        var res: TextView? = null
        var par: TextView? = null
        var avail: TextView? = null

        init {
            titleText = binding.tVHotelName
            viewPager = binding.vPHotelSlider
            textMrp = binding.tVMrp
            textDiscounted = binding.tVdiscounted
            textDetails = binding.tVBook
            textAddress = binding.tVAddress
            tabLayout = binding.tLSliderDots

//         itemView.setOnClickListener {
//             onItemClick?.invoke(hotelsList?.get(absoluteAdapterPosition)!!)
//         }

            /* res= binding.tVRes
            par= binding.tVPar
            avail= binding.tVAvail*/
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HotelsViewHolder {
        return HotelsViewHolder(
            HotelItemBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun getItemCount(): Int {
        return hotelsList?.size ?: 0
    }

    override fun onBindViewHolder(holder: HotelsViewHolder, position: Int) {
        var hotel = hotelsList!![position]

        holder.titleText?.text = hotel?.hotelName.toString()
        holder.textAddress?.text = hotel?.textAddress.toString()
        holder.textMrp?.text = numberFormat.format(hotel?.mRP)
        holder.textMrp?.paintFlags = Paint.STRIKE_THRU_TEXT_FLAG
        holder.textDiscounted?.text = numberFormat.format(hotel?.discounted)
        holder.textAddress?.text = hotel?.textAddress

        if (hotel?.discounted == 0) {

            holder.textMrp?.text = "Price On"
            holder.textDiscounted?.text = "Request"
        }

        try {

            val bannerSliderAdapter = AdapterHotelSlider(context!!, hotel?.hotelImages!!)

            holder.viewPager?.adapter = bannerSliderAdapter

            handler = Handler()

            Timer().schedule(object : TimerTask() {
                override fun run() {

                    handler.post {

                        if (holder.viewPager?.currentItem!! < hotel?.hotelImages!!.size - 1) {

                            holder.viewPager?.currentItem = holder.viewPager?.currentItem!! + 1
                        } else {
                            holder.viewPager?.currentItem = 0
                        }
                    }
                }
            }, sliderTimerDuration, sliderTimerDuration)

            holder.tabLayout?.setupWithViewPager(holder.viewPager, true)
        } catch (e: java.lang.Exception) {
            Log.d("banner", "onBindViewHolder: ${e.message}")
        }

        holder.itemView.setOnClickListener {
            val detailsIntent = Intent(context, HotelDetailsActivity::class.java)
            detailsIntent.putExtra("hotel", hotel)
            context?.startActivity(detailsIntent)
        }


    }

    fun addData(list: ArrayList<HotelDetails?>?) {

        hotelsList = list

        notifyDataSetChanged()
    }


}

class AdapterHotelSlider(private val context: Context, private val imageList: List<String?>) :
    PagerAdapter() {

    override fun getCount(): Int {

        return imageList.size
    }

    override fun isViewFromObject(view: View, `object`: Any): Boolean {

        return view === `object` as ConstraintLayout
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {

        val layoutInflater =
            context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

        val itemView = layoutInflater.inflate(R.layout.slider_item, container, false)
        val imageView = itemView.findViewById<View>(R.id.sIVBanner) as ShapeableImageView

        try {

            Picasso.get().load(imageList[position]).placeholder(R.drawable.placeholder)
                .into(imageView)
        } catch (e: java.lang.Exception) {
            Log.d("imageList", "instantiateItem: ${e.message}")
        }

        container.addView(itemView, 0)

        return itemView
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {

        container.removeView(`object` as ConstraintLayout)
    }
}
