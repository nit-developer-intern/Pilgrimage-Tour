package `in`.tourism.pilgrimagetour.view

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.activityViewModels
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FirebaseFirestore
import `in`.tourism.pilgrimagetour.Coupon
import `in`.tourism.pilgrimagetour.OnCouponClickListener
import `in`.tourism.pilgrimagetour.R
import `in`.tourism.pilgrimagetour.adapter.CouponListAdapter
import `in`.tourism.pilgrimagetour.databinding.FragmentCouponsBinding
import `in`.tourism.pilgrimagetour.viewmodel.BookingViewModel
import `in`.tourism.pilgrimagetour.viewmodel.HotelBookingVM

class CouponsFragment : Fragment() {

    private lateinit var binding: FragmentCouponsBinding
    private var db : FirebaseFirestore ?= null

    private var couponCollection: CollectionReference? = null
    private var hotelCouponCollection: CollectionReference? = null
    private var secretCouponCollection: CollectionReference? = null
    private var helicopterCouponCollection: CollectionReference?= null
    private var busCouponCollection: CollectionReference?= null
    private var couponsList: ArrayList<Coupon?> ?= null
    private var couponListAdapter: CouponListAdapter?= null

    private var secretCodeInput : String = ""
    private var couponCode : String ?= null
    private var couponCost : Int ?= null
    private var viewType : Int ?= null

    private val sharedViewModel: BookingViewModel by activityViewModels()
    private val hotelVM: HotelBookingVM by activityViewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        db = FirebaseFirestore.getInstance()

        couponCollection = db?.collection("coupon")
        hotelCouponCollection = db?.collection("couponHotel")
        secretCouponCollection = db?.collection("secretCoupon")


    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentCouponsBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        couponsList = ArrayList()

        viewType = sharedViewModel.viewType.value!!

        binding.iVBackArrow.setOnClickListener {
            activity?.supportFragmentManager?.popBackStack()
        }


        when(viewType){


        }

        if(hotelVM.hotelName.value != ""){
            hotelCouponCollection?.addSnapshotListener { value, error ->

                Log.d("CouponsFragment", value.toString())
                if(value != null){

                    val documentList = value.documents

                    if(documentList.isNotEmpty()){
                        for(document in documentList){

                            (couponsList as java.util.ArrayList<Coupon?>).add(document.toObject(
                                Coupon::class.java))
                        }
                    }

                    binding.rVcoupons.adapter = couponListAdapter
                }
            }

            couponListAdapter = CouponListAdapter(couponsList , onCouponClickListener)

        } else {

            couponCollection?.addSnapshotListener { value, error ->

                Log.d("CouponsFragment", value.toString())
                if (value != null) {

                    val documentList = value.documents

                    if (documentList.isNotEmpty()) {
                        for (document in documentList) {

                            (couponsList as java.util.ArrayList<Coupon?>).add(
                                document.toObject(
                                    Coupon::class.java
                                )
                            )
                        }
                    }

                    binding.rVcoupons.adapter = couponListAdapter
                }
            }

            couponListAdapter = CouponListAdapter(couponsList, onCouponClickListener)

        }

        binding.mCVapply.setOnClickListener {

            secretCodeInput = binding.tIETsecretCode.text.toString().trim().uppercase()

            if(secretCodeInput != ""){

                secretCouponCollection?.whereEqualTo("couponCode" , secretCodeInput)
                    ?.get()
                    ?.addOnSuccessListener { documentList ->

                        if(documentList.isEmpty){
                            binding.tIETsecretCode.error = "Invalid Coupon Code"

                            return@addOnSuccessListener
                        } else {

                            val secretcoupon : List<Coupon> =
                                    documentList.toObjects(Coupon::class.java)
                            val secretCouponList = arrayListOf<Coupon>()

                            secretCouponList.addAll(secretcoupon)

                            couponCode = secretCouponList[0].couponCode
                            couponCost = secretCouponList[0].cost

                            if(secretCouponList[0].type == 2 && hotelVM.hotelName.value != ""){

                                hotelVM.setCouponCode(couponCode!!)
                                hotelVM.setCouponCost(couponCost!!)

                                val snackBar= Snackbar.make(binding.root, "Yay! Secret coupon $couponCode applied.", Snackbar.LENGTH_LONG)
                                val sBView= snackBar.view

                                sBView.setBackgroundColor(ContextCompat.getColor(requireContext(),
                                    R.color.green_whatsapp
                                ))

                                snackBar.show()

                                activity?.supportFragmentManager?.popBackStack()

                            }
                            else if(secretCouponList[0].type == 1 && sharedViewModel.packageName.value != "") {
                                sharedViewModel.setCouponCode(couponCode!!)
                                sharedViewModel.setCouponCost(couponCost!!)

                                val snackBar= Snackbar.make(binding.root, "Yay! Secret coupon $couponCode applied.", Snackbar.LENGTH_LONG)
                                val sBView= snackBar.view

                                sBView.setBackgroundColor(ContextCompat.getColor(requireContext(),
                                    R.color.green_whatsapp
                                ))

                                snackBar.show()

                                activity?.supportFragmentManager?.popBackStack()
                            } else {
                                binding.tIETsecretCode.error = "Invalid Coupon Code"
                            }
                        }
                    }
                    ?.addOnFailureListener {
                        Log.w("CouponsFragment", "Error getting documents: ", it)
                    }

            } else{
                binding.tIETsecretCode.error = "Please enter a Code or Select a code from the List "
            }
        }

    }

    private  val onCouponClickListener = object : OnCouponClickListener {
        override fun onClick(code: String, cost: Int) {

            couponCode = code
            couponCost = cost

            sharedViewModel.setCouponCode(couponCode!!)
            sharedViewModel.setCouponCost(couponCost!!)

            hotelVM.setCouponCode(couponCode!!)
            hotelVM.setCouponCost(couponCost!!)


            activity?.supportFragmentManager?.popBackStack()

        }


    }
}