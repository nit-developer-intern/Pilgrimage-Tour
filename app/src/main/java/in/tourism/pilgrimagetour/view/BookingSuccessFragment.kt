package `in`.tourism.pilgrimagetour.view

import android.content.Intent
import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.fragment.app.activityViewModels
import `in`.tourism.pilgrimagetour.databinding.FragmentBookingSuccessBinding
import `in`.tourism.pilgrimagetour.viewmodel.BookingViewModel
import `in`.tourism.pilgrimagetour.viewmodel.HotelBookingVM
import java.text.NumberFormat
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.util.Currency
import java.util.Locale


class BookingSuccessFragment : Fragment() {

    private lateinit var binding: FragmentBookingSuccessBinding
    private var orderID : String ?= null
    private var packageName : String ?= null
    private var duration : String ?= null
    @RequiresApi(Build.VERSION_CODES.O)
    private var selectedDate : LocalDate = LocalDate.now()
    private var selectedDateFormatted : String ?= null
    private var totalNumberofPersons : Int ?= null
    private var totalCost = 0
    private var userPayCost = 0.0
    private var amountDueCost = 0

    private var numberFormat = NumberFormat.getCurrencyInstance(Locale("en","in"))
    private val sharedViewModel: BookingViewModel by activityViewModels()
    private val hotelVM: HotelBookingVM by activityViewModels()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentBookingSuccessBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        numberFormat.maximumFractionDigits = 0
        numberFormat.currency = Currency.getInstance("INR")

        if (hotelVM.hotelName.value != "") {

            binding.tRDuration.visibility = View.GONE
            binding.tRPackageName.visibility = View.GONE
            binding.tRTravelDate.visibility = View.GONE
            val dateFormat = SimpleDateFormat("yyyy.MM.dd")

            binding.tVOrderId.text = hotelVM.orderID.value
            binding.tVHotel.text = hotelVM.hotelName.value
            binding.tVCheckIn.text = hotelVM.checkIn.value?.let { dateFormat.format(it) }
            binding.tVCheckOut.text = hotelVM.checkOut.value?.let { dateFormat.format(it) }
            binding.tVnumberofPersons.text = hotelVM.totalGuests.value.toString()
            /*
                        binding.tVTotalCost.text = hotelVM.youPayCost.value?.let {
                            numberFormat.format(hotelVM.amountDueCost.value?.let { it1 ->
                                it.plus(
                                    it1
                                )
                            })
                        }
            */
            binding.tVtotalCost.text = hotelVM.youPayCost.value?.let {
                numberFormat.format(hotelVM.amountDueCost.value?.let { it1 ->
                    it.plus(
                        it1
                    )
                })
            }

            binding.tVUserCost.text = numberFormat.format(hotelVM.youPayCost.value)
            binding.tVamountDue.text = numberFormat.format(hotelVM.amountDueCost.value)

        } else {
            getFromVM()
            updateViews()
        }
        binding.buttonBrowse.setOnClickListener {
            val intent = Intent(requireContext() ,MainActivity::class.java)
            startActivity(intent)
        }

        /*binding.buttonDetails.setOnClickListener {
            findNavController().navigate(BookingSuccessFragmentDirections.actionBookingSuccessFragmentToBookingDetailsFragment())
        }*/
    }

    private fun getFromVM() {

        orderID = sharedViewModel.orderID.value!!
        packageName = sharedViewModel.packageName.value!!
        duration = sharedViewModel.packageDays.value!!
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            selectedDate = sharedViewModel.date.value!!
        }
        totalNumberofPersons = (sharedViewModel.numberofadults.value!!) + (sharedViewModel.numberOfChildren.value!!)
        totalCost  = sharedViewModel.totalafterCoupon.value!!
        userPayCost = sharedViewModel.userPayCost.value!!
        amountDueCost  = sharedViewModel.amountDueCost.value!!
    }


    private fun updateViews(){

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            selectedDateFormatted =
                "${selectedDate.dayOfMonth} ${selectedDate.month} , ${selectedDate.year}"
        }


        binding.tVOrderId.text = orderID
        binding.tVPackageName.text = packageName
        binding.tVDuration.text = duration
        binding.tVTravelDate.text = selectedDateFormatted
        binding.tVnumberofPersons.text = totalNumberofPersons.toString()
        binding.tVtotalCost.text = numberFormat.format(totalCost)
        binding.tVUserCost.text = numberFormat.format(userPayCost)
        binding.tVamountDue.text = numberFormat.format(amountDueCost)
    }



}