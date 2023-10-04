package `in`.tourism.pilgrimagetour.view

import android.annotation.SuppressLint
import android.content.Context
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.commit
import androidx.fragment.app.replace
import com.google.android.material.datepicker.CalendarConstraints
import com.google.android.material.datepicker.MaterialDatePicker
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase
import `in`.tourism.pilgrimagetour.HotelDetails
import `in`.tourism.pilgrimagetour.OnBookHotelListener
import `in`.tourism.pilgrimagetour.OnHotelListener
import `in`.tourism.pilgrimagetour.R
import `in`.tourism.pilgrimagetour.databinding.FragmentBookHotelBinding
import `in`.tourism.pilgrimagetour.viewmodel.HotelBookingVM
import java.text.NumberFormat
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.ZoneId
import java.util.Calendar
import java.util.Date
import java.util.Locale
import java.util.Timer
import java.util.TimerTask
import kotlin.math.roundToInt

class FragmentBookHotel : Fragment() {

    private lateinit var binding: FragmentBookHotelBinding
    private var currentUser:FirebaseUser ?= null
    private var numberFormat = NumberFormat.getCurrencyInstance(Locale("en", "in"))

    private var hotel: HotelDetails?= null
    private var hotelName = ""
    private var hotelCost = 0

    private var roomsNightsTotal = 0

    private var nights = 1
    private lateinit var dateFormat: SimpleDateFormat
    private var date1string = ""
    private var date2string = ""
    private lateinit var date1: Date
    private lateinit var date2: Date
    private var date1caps = ""
    private var date2caps = ""

    private var gst = 0
    private var subTotal = 0
    private var couponCost = 0
    private var couponCode = ""
    private var advanceCost = 0
    private var totalAfterCoupon = 0
    private var amountDueAfterAdvance = 0
    private var userPayCost = 0.0
    private var razorpayAmount = 0

    private var roomsCost = 0
    private var totalGuests = 2
    private var rooms = 1
    private var thirdAdultCost = 0
    private var totalAdults = 2
    private var totalChildren = 0
    private var room1adults = 2
    private var room2adults = 0
    private var room3adults = 0
    private var room4adults = 0
    private var bed11: Boolean? = null
    private var bed12: Boolean? = null
    private var bed21: Boolean? = null
    private var bed22: Boolean? = null
    private var bed31: Boolean? = null
    private var bed32: Boolean? = null
    private var bed41: Boolean? = null
    private var bed42: Boolean? = null

    private lateinit var editableAddReq: Editable
    private lateinit var editableAltNumber: Editable
    private lateinit var editableMobileNum: Editable
    private lateinit var editableAddress: Editable
    private lateinit var editableLeadNAme: Editable

    private var bookHotelListener: OnBookHotelListener ?= null
    private val sharedVM : HotelBookingVM by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentBookHotelBinding.inflate(layoutInflater)
        return binding.root
    }


    @RequiresApi(Build.VERSION_CODES.O)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        currentUser = Firebase.auth.currentUser
        numberFormat.maximumFractionDigits = 0

        editableAddReq = binding.eTAdditionalReq.text
        editableAddress = binding.eTLeadAddress.text
        editableAltNumber = binding.eTAltNum.text
        editableMobileNum = binding.eTLeadMobile.text
        editableLeadNAme = binding.eTLeadTraveler.text

        updateViews()

        binding.tIETGuestsRooms.setText("$totalGuests Guests/ $rooms Rooms")

        dateFormat = SimpleDateFormat("dd/MM/yyyy")

        date1 = sharedVM.checkIn.value!!
        date2 = sharedVM.checkOut.value!!

        totalGuests = sharedVM.totalGuests.value!!
        rooms = sharedVM.numberOfRooms.value!!

        couponCode = sharedVM.couponCode.value.toString()
        couponCost = sharedVM.couponCost.value!!

        if(sharedVM.hotelName.value != ""){

        } else {
            binding.eTLeadTraveler.setText(currentUser?.displayName)
            binding.eTLeadEmail.setText(currentUser?.email)
            binding.eTLeadMobile.setText(currentUser?.phoneNumber)

            val today = System.currentTimeMillis()
            date1 = Date(today + 24 * 60 * 60 * 1000)
            date2 = Date(date1.time.plus(24 * 60 * 60 * 1000))
        }

        val today = LocalDate.now()
        val tomorrow = today.plusDays(1)
        val dayAfter = tomorrow.plusDays(1)

        binding.tIETCheckIn.setText(
            dateFormat.format(
                Date.from(
                    tomorrow.atStartOfDay(ZoneId.systemDefault()).toInstant()
                )
            )
        )

        binding.tIETCheckOut.setText(
            dateFormat.format(
                Date.from(
                    dayAfter.atStartOfDay(ZoneId.systemDefault()).toInstant()
                )
            )
        )

        binding.tVCheckIn.text = "${today.dayOfMonth} ${today.month}, ${today.year}"
        binding.tVCheckOut.text = "${tomorrow.dayOfMonth} ${tomorrow.month}, ${tomorrow.year}"

        val constraints = CalendarConstraints.Builder()
            .setValidator(DateValidator(Calendar.getInstance()))
            .build()

        val dateRangePicker = MaterialDatePicker.Builder
            .dateRangePicker()
            .setTitleText("Select Dates")
            .setCalendarConstraints(constraints).build()

        dateRangePicker.addOnPositiveButtonClickListener { range ->
            date1 = Date(range.first)
            date2 = Date(range.second)

            nights = (date2.time.minus(date1.time)/ (24 * 60 * 60 * 1000)).toInt()

            binding.tVNights.text = nights.toString()
            sharedVM.setTotalNights(nights)

            roomsNightsTotal = roomsCost.times(nights)
            sharedVM.setTotalRoomsCost(roomsNightsTotal)

            date1string = dateFormat.format(date1)
            date2string = dateFormat.format(date2)

            updateViews()

            val date1loc = date1.toInstant().atZone(ZoneId.systemDefault())
            val date2loc = date2.toInstant().atZone(ZoneId.systemDefault())

            date1caps = "${date1loc.dayOfMonth} ${date1loc.month}, ${date1loc.year}"
            date2caps = "${date2loc.dayOfMonth} ${date2loc.month}, ${date2loc.year}"

            binding.tIETCheckIn.setText(date1string)
            binding.tIETCheckOut.setText(date2string)
            binding.tVCheckIn.text = date1caps
            binding.tVCheckOut.text = date2caps

            sharedVM.setCheckIn(date1)
            sharedVM.setCheckOut(date2)
        }

        val date1loc = date1.toInstant().atZone(ZoneId.systemDefault())
        val date2loc = date2.toInstant().atZone(ZoneId.systemDefault())

        date1caps = "${date1loc.dayOfMonth} ${date1loc.month}, ${date1loc.year}"
        date2caps = "${date2loc.dayOfMonth} ${date2loc.month}, ${date2loc.year}"

        binding.tIETCheckIn.setOnClickListener {
            dateRangePicker.show(activity?.supportFragmentManager!!, "tag")
        }

        binding.tIETCheckOut.setOnClickListener {
            dateRangePicker.show(activity?.supportFragmentManager!!, "tag")
        }

        binding.tVCheckIn.text = date1caps
        binding.tVCheckOut.text = date2caps

        binding.eTAdditionalReq.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

                editableAddReq = p0 as Editable
            }

            override fun afterTextChanged(p0: Editable?) {
            }
        })
        binding.eTAltNum.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

                editableAltNumber = p0 as Editable
            }

            override fun afterTextChanged(p0: Editable?) {
            }
        })
        binding.eTLeadAddress.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

                editableAddress = p0 as Editable
            }

            override fun afterTextChanged(p0: Editable?) {
            }
        })
        binding.eTLeadMobile.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

                editableMobileNum = p0 as Editable
            }

            override fun afterTextChanged(p0: Editable?) {
            }
        })
        binding.eTLeadTraveler.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

                editableLeadNAme = p0 as Editable
            }

            override fun afterTextChanged(p0: Editable?) {
            }
        })

        val docID = (activity as ActivityHotelBooking).getDocID()

        FirebaseFirestore.getInstance().collection("hotelDetails")
            .whereEqualTo("hotelName" , docID)
            .get()
            .addOnSuccessListener {

                for(doc in it){

                    hotel = doc.toObject(HotelDetails::class.java)

                    hotelName = hotel?.hotelName!!
                    sharedVM.setHotelName(hotelName)

                    hotel?.discounted?.let { it1 -> sharedVM.setHotelCost(it1) }

                    updateViews()
                }
            }

        if(binding.cB1PayAdvance.isChecked) {

            userPayCost = advanceCost.toDouble()

            sharedVM.setYouPayCost(userPayCost)

            amountDueAfterAdvance = (totalAfterCoupon - advanceCost)

            sharedVM.setAmountDueCost(amountDueAfterAdvance)
            razorpayAmount = userPayCost.times(100).roundToInt()

            binding.tVShowPaymentType.text = "Partial Payment"
            binding.textAmountDue.visibility = View.VISIBLE
            binding.tVAmountDueCost.visibility = View.VISIBLE
        } else if (binding.cB2Payfull.isChecked) {

            userPayCost = totalAfterCoupon.toDouble()

            sharedVM.setYouPayCost(userPayCost)

            amountDueAfterAdvance = 0

            sharedVM.setAmountDueCost(amountDueAfterAdvance)

            razorpayAmount = userPayCost.times(100).roundToInt()

            binding.tVShowPaymentType.text = "Full Payment"
        }

        razorpayAmount = userPayCost.times(100).roundToInt()

        binding.cB2Payfull.setOnCheckedChangeListener { buttonView, isChecked ->
            // Responds to checkbox being checked/unchecked
            if (isChecked) {

                binding.cB1PayAdvance.isChecked = false
                binding.tVAmountDueCost.visibility = View.GONE
                binding.textAmountDue.visibility = View.GONE

                buttonView.isEnabled = false

                updateViews()
            } else {
                buttonView.isEnabled = true
            }
        }
        binding.cB1PayAdvance.setOnCheckedChangeListener { buttonView, isChecked ->
            // Responds to checkbox being checked/unchecked
            if (isChecked) {

                binding.cB2Payfull.isChecked = false
                binding.tVAmountDueCost.visibility = View.VISIBLE
                binding.textAmountDue.visibility = View.VISIBLE

                buttonView.isEnabled = false

                updateViews()

                var handler = Handler()
                Timer().schedule(object : TimerTask() {
                    override fun run() {

                        handler.post(Runnable {

                            binding.sVBookHotel.post {
                                binding.sVBookHotel.fullScroll(View.FOCUS_DOWN)
                            }
                        })
                    }
                }, 100)
            } else buttonView.isEnabled = true
        }

        binding.mCvApplyCoupon.setOnClickListener {

            sharedVM.setHotelName(hotelName)

            activity?.supportFragmentManager?.commit {
                replace<CouponsFragment>(R.id.navHostFragmentHotel)
                setReorderingAllowed(true)
                addToBackStack("name") // Name can be null
            }
        }
        binding.tVMoreCoupons.setOnClickListener {

            activity?.supportFragmentManager?.commit {
                replace<CouponsFragment>(R.id.navHostFragmentHotel)
                setReorderingAllowed(true)
                addToBackStack("name") // Name can be null
            }
        }

        binding.iVDeleteCoupon.setOnClickListener {

            couponCode = ""
            couponCost = 0

            sharedVM.setCouponCode(couponCode)
            sharedVM.setCouponCost(couponCost)

            updateViews()
        }

        binding.btContinueBooking.setOnClickListener {

            if (binding.eTLeadTraveler.text.toString().trim() == "")
                binding.eTLeadTraveler.error = "Please enter name"
            else if (!android.util.Patterns.EMAIL_ADDRESS.matcher(
                    binding.eTLeadEmail.text.toString().trim()).matches()){
                binding.eTLeadEmail.error = "Enter valid email"
            }
            else if (binding.eTLeadMobile.text.toString().trim().length != 10) {
                binding.eTLeadMobile.error = "Enter valid number"
                binding.eTLeadMobile.requestFocus()
            }
            else if (binding.eTAltNum.text.toString()
                    .trim() != "" && binding.eTAltNum.text.toString().trim().length != 10
            ) {
                binding.eTAltNum.error = "Enter valid number"
                binding.eTAltNum.requestFocus()
            } else {

                setInVM()
                bookHotelListener?.sendData(razorpayAmount)
//                Toast.makeText(requireContext(), "RazororPay: ${numberFormat.format(razorpayAmount)}", Toast.LENGTH_SHORT).show()
            }




        }

    }

    private fun setInVM(){

        sharedVM.setHotelName(hotelName)
        sharedVM.setCheckIn(date1)
        sharedVM.setCheckOut(date2)

        sharedVM.setLeadTraveller(binding.eTLeadTraveler.text.toString())
        sharedVM.setLeadEmail(binding.eTLeadEmail.text.toString())
        sharedVM.setLeadContact(binding.eTLeadMobile.text.toString())
        sharedVM.setAltNum(binding.eTAltNum.text.toString())
        sharedVM.setFullAddress(binding.eTLeadAddress.text.toString())
        sharedVM.setAdditionalReq(binding.eTAdditionalReq.text.toString())

        sharedVM.setYouPayCost(userPayCost)
        sharedVM.setPaymentType(binding.tVShowPaymentType.text.toString())
    }

    private fun updateViews() {

        if (hotel != null) {
            hotelCost = hotel?.discounted!!
            sharedVM.setHotelCost(hotelCost)
        }

        if(couponCode != "" && couponCost > 0){
            binding.mCvApplyCoupon.visibility = View.GONE
            binding.mCVCouponApplied.visibility = View.VISIBLE
            binding.tVYouSaved.text = "Yay!, You Saved ${numberFormat.format(couponCost)}"
            binding.tVCouponApplied.text = "$couponCode Applied"
            binding.sVBookHotel.fullScroll(View.FOCUS_DOWN)
        } else {
            binding.mCvApplyCoupon.visibility = View.VISIBLE
            binding.mCVCouponApplied.visibility = View.GONE
        }

        thirdAdultCost = (40 * hotelCost) / 100

        val room1adultsCost = when (room1adults) {
            3 -> hotelCost.plus(thirdAdultCost)

            0 -> 0

            else -> hotelCost
        }
        val room2adultsCost = when (room2adults) {

            3 -> hotelCost.plus(thirdAdultCost)

            0 -> 0

            else -> hotelCost
        }
        val room3adultsCost = when (room3adults) {

            3 -> hotelCost.plus(thirdAdultCost)

            0 -> 0

            else -> hotelCost
        }
        val room4adultsCost = when (room4adults) {

            3 -> hotelCost.plus(thirdAdultCost)

            0 -> 0

            else -> hotelCost
        }

        val withBedCost = (30 * hotelCost) / 100
        val withoutBedCost = (15 * hotelCost) / 100

        val child11cost = when (bed11) {

            null -> 0

            true -> withBedCost

            false -> withoutBedCost
        }
        val child12cost = when (bed12) {

            null -> 0

            true -> withBedCost

            false -> withoutBedCost
        }
        val child21cost = when (bed21) {

            null -> 0

            true -> withBedCost

            false -> withoutBedCost
        }
        val child22cost = when (bed22) {

            null -> 0

            true -> withBedCost

            false -> withoutBedCost
        }
        val child31cost = when (bed31) {

            null -> 0

            true -> withBedCost

            false -> withoutBedCost
        }
        val child32cost = when (bed32) {

            null -> 0

            true -> withBedCost

            false -> withoutBedCost
        }
        val child41cost = when (bed41) {

            null -> 0

            true -> withBedCost

            false -> withoutBedCost
        }
        val child42cost = when (bed42) {

            null -> 0

            true -> withBedCost

            false -> withoutBedCost
        }

        val room1Cost = room1adultsCost.plus(child11cost).plus(child12cost)
        val room2Cost = room2adultsCost.plus(child21cost).plus(child22cost)
        val room3Cost = room3adultsCost.plus(child31cost).plus(child32cost)
        val room4Cost = room4adultsCost.plus(child41cost).plus(child42cost)

        roomsCost = room1Cost.plus(room2Cost).plus(room3Cost).plus(room4Cost)
        roomsNightsTotal = roomsCost.times(nights)

        sharedVM.setTotalRoomsCost(roomsNightsTotal)

        gst = ((7.5 * roomsNightsTotal) / 100).toInt()

        sharedVM.setGSTCost(gst)

        subTotal = roomsNightsTotal.plus(gst)

        sharedVM.setSubtotalCost(subTotal)

        totalAfterCoupon = subTotal.minus(couponCost)

        sharedVM.setTotalAfterCoupon(totalAfterCoupon)

        advanceCost = (25 * totalAfterCoupon) / 100

        sharedVM.setAdvanceCost(advanceCost)

        sharedVM.setNumberOfRooms(rooms)

        totalGuests = totalAdults.plus(totalChildren)

        sharedVM.setTotalGuests(totalGuests)

        binding.tIETGuestsRooms.setText("$totalGuests Guests/ $rooms Rooms")
        binding.tIETGuestsRooms.setOnClickListener {
            val modalBottomSheet = GuestsRoomsSheet()
            modalBottomSheet.setListener(listener)
            modalBottomSheet.show(activity?.supportFragmentManager!!, "TAG")
        }

        val adultsCost =  room1adultsCost.plus(
            room2adultsCost.plus(room3adultsCost.plus(room4adultsCost))
        )

        val childrenCost = child11cost.plus(child12cost).plus(child21cost).plus(child22cost).plus(child31cost)
            .plus(child32cost).plus(child41cost).plus(child42cost)

        if (totalChildren > 0) {
            binding.groupChildren.visibility = View.VISIBLE
        } else {
            binding.groupChildren.visibility = View.GONE
        }

        binding.tVBookingHotelName.text = hotel?.hotelName

        binding.tVHotelCost.text = numberFormat.format(hotelCost)
        binding.tVNumOfAdults.text = "(${totalAdults})"
        binding.tVNumOfChildren.text = "(${totalChildren})"
        binding.tVRoomNight.text = "$rooms Rooms X $nights Nights:"

        binding.tVAdultsTotalCost.text = numberFormat.format(adultsCost)
        binding.tVChildrenTotalCost.text = numberFormat.format(childrenCost)
        binding.tVTotalCost.text = numberFormat.format(roomsNightsTotal)
        binding.tVGSTCost.text = numberFormat.format(gst)
        binding.tVGrandTotalCost.text = numberFormat.format(subTotal)

        if(binding.cB1PayAdvance.isChecked) {

            userPayCost = advanceCost.toDouble()

            sharedVM.setYouPayCost(userPayCost)

            amountDueAfterAdvance = (totalAfterCoupon - advanceCost)

            sharedVM.setAmountDueCost(amountDueAfterAdvance)
            razorpayAmount = userPayCost.times(100).roundToInt()

            binding.tVShowPaymentType.text = "Partial Payment"
            binding.textAmountDue.visibility = View.VISIBLE
            binding.tVAmountDueCost.visibility = View.VISIBLE
        }
        else if (binding.cB2Payfull.isChecked) {

            userPayCost = totalAfterCoupon.toDouble()

            sharedVM.setYouPayCost(userPayCost)

            amountDueAfterAdvance = 0

            sharedVM.setAmountDueCost(amountDueAfterAdvance)

            razorpayAmount = userPayCost.times(100).roundToInt()

            binding.tVShowPaymentType.text = "Full Payment"
        }

        binding.tVPayAdvance.text = numberFormat.format(advanceCost)
        binding.tVPayAdvance.setOnClickListener { binding.cB1PayAdvance.performClick() }

        binding.tVPayFull.text = numberFormat.format(totalAfterCoupon)
        binding.tVPayFull.setOnClickListener { binding.cB2Payfull.performClick() }
        binding.tVPayCost.text = numberFormat.format(userPayCost)
        binding.tVAmountDueCost.text = numberFormat.format(amountDueAfterAdvance)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)

        if (context is OnBookHotelListener) bookHotelListener = context
        else throw IllegalArgumentException("$context must implement OnBookHotelListener")
    }

    val listener = object : OnHotelListener {
        override fun sendData(
            room1adults: Int,
            room2adults: Int,
            room3adults: Int,
            room4adults: Int,
            rooms: Int,
            bed11: Boolean?,
            bed12: Boolean?,
            bed21: Boolean?,
            bed22: Boolean?,
            bed31: Boolean?,
            bed32: Boolean?,
            bed41: Boolean?,
            bed42: Boolean?,
            totalChildren: Int,
            totalAdults: Int
        ) {
            this@FragmentBookHotel.rooms = rooms
            this@FragmentBookHotel.room1adults = room1adults
            this@FragmentBookHotel.room2adults = room2adults
            this@FragmentBookHotel.room3adults = room3adults
            this@FragmentBookHotel.room4adults = room4adults
            this@FragmentBookHotel.bed11 = bed11
            this@FragmentBookHotel.bed12 = bed12
            this@FragmentBookHotel.bed21 = bed21
            this@FragmentBookHotel.bed22 = bed22
            this@FragmentBookHotel.bed31 = bed31
            this@FragmentBookHotel.bed32 = bed32
            this@FragmentBookHotel.bed41 = bed41
            this@FragmentBookHotel.bed42 = bed42
            this@FragmentBookHotel.totalChildren = totalChildren
            this@FragmentBookHotel.totalAdults = totalAdults


            if (totalChildren > 0) {
                binding.groupChildren.visibility = View.VISIBLE
            } else {
                binding.groupChildren.visibility = View.GONE
            }

            thirdAdultCost = (40 * hotelCost) / 100

            updateViews()
//
//            totalGuests = totalAdults.plus(totalChildren)
//            sharedVM.setTotalGuests(totalGuests)
//
//            var room1adultsCost = when (room1adults) {
//                3 -> hotelCost.plus(thirdAdultCost)
//
//                0 -> 0
//
//                else -> hotelCost
//            }
//            var room2adultsCost = when (room2adults) {
//                3 -> hotelCost.plus(thirdAdultCost)
//
//                0 -> 0
//
//                else -> hotelCost
//            }
//            var room3adultsCost = when (room3adults) {
//                3 -> hotelCost.plus(thirdAdultCost)
//
//                0 -> 0
//
//                else -> hotelCost
//            }
//            var room4adultsCost = when (room4adults) {
//                3 -> hotelCost.plus(thirdAdultCost)
//
//                0 -> 0
//
//                else -> hotelCost
//            }
//
//            val withBedCost = (30 * hotelCost) / 100
//            val withoutBedCost  = (15 * hotelCost) / 100
//
//            val child11cost = when (bed11) {
//
//                null -> 0
//
//                true -> withBedCost
//
//                false -> withoutBedCost
//            }
        }
    }

    @SuppressLint("ParcelCreator")
    private class DateValidator(private val today: Calendar) : CalendarConstraints.DateValidator {

        override fun isValid(date: Long): Boolean {
            return date >= today.timeInMillis
        }

        override fun writeToParcel(dest: android.os.Parcel, flags: Int) {
            dest.writeLong(today.timeInMillis)
        }

        override fun describeContents(): Int {
            return 0
        }
    }
}