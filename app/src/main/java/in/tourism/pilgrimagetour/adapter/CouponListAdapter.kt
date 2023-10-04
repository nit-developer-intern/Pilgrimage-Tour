package `in`.tourism.pilgrimagetour.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import `in`.tourism.pilgrimagetour.Coupon
import `in`.tourism.pilgrimagetour.OnCouponClickListener
import `in`.tourism.pilgrimagetour.databinding.CouponListItemBinding

class CouponListAdapter(): RecyclerView.Adapter<CouponListAdapter.CouponsListViewHolder>() {

    private var couponsList: ArrayList<Coupon?>? = null
    private var onCouponClickListener: OnCouponClickListener?= null


    constructor(couponsList:ArrayList<Coupon?>?,
                onCouponClickListener: OnCouponClickListener
    ) : this() {
        this.couponsList = couponsList
        this.onCouponClickListener = onCouponClickListener
    }

    inner class CouponsListViewHolder(binding: CouponListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

            var couponCode : TextView ?= null
            var description : TextView ?= null
            var btRedeem : Button ?= null

        init {
            couponCode = binding.tVCouponCode
            description = binding.tVDescription
            btRedeem = binding.btRedeem
        }
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CouponsListViewHolder {
       return CouponsListViewHolder(
            CouponListItemBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun getItemCount(): Int {
        return if(couponsList != null) couponsList?.size!!
        else 0
    }

    override fun onBindViewHolder(holder: CouponsListViewHolder, position: Int) {

        if(couponsList != null){
            val noteItem = couponsList!![position]

            if(noteItem != null){
                holder.couponCode?.text = noteItem.couponCode
                holder.description?.text = noteItem.description
            }

            holder.btRedeem?.setOnClickListener{
                onCouponClickListener?.onClick(
                    couponsList!![position]?.couponCode!!,
                    couponsList!![position]?.cost!!
                )
            }
        }


    }
}