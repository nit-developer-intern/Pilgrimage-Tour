package `in`.tourism.pilgrimagetour.adapter

import android.app.Activity
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import `in`.tourism.pilgrimagetour.R
import `in`.tourism.pilgrimagetour.SliderBanner
import `in`.tourism.pilgrimagetour.databinding.NotifyItemBinding
import `in`.tourism.pilgrimagetour.view.MainActivity
import java.util.ArrayList

class NotifyFragmentAdapter(val activity: Activity,var offersList: ArrayList<SliderBanner?>) :
    RecyclerView.Adapter<NotifyFragmentAdapter.NotifyViewHolder>() {

    class NotifyViewHolder(binding: NotifyItemBinding) : RecyclerView.ViewHolder(binding.root){
         var imageView : ImageView? = null
        init {
            imageView = binding.iVbanner
        }

    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotifyViewHolder {
        return NotifyViewHolder(NotifyItemBinding.inflate(LayoutInflater.from(parent.context) ,
                parent ,
                false)
        )
    }

    override fun onBindViewHolder(holder: NotifyViewHolder, position: Int) {
        Log.d("Banner" , "${offersList?.size}")
        Picasso.get().load(offersList?.get(position)?.url).placeholder(R.drawable.placeholder)
            .into(holder.imageView)

        holder.itemView.setOnClickListener { (activity as MainActivity).selectTab(1) }
    }

    override fun getItemCount(): Int {
        return offersList?.size ?: 0
    }



}
