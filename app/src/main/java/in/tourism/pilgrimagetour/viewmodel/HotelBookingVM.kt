package `in`.tourism.pilgrimagetour.viewmodel

import android.annotation.SuppressLint
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import java.time.Instant
import java.util.Date

class HotelBookingVM : ViewModel() {

    private val _orderID= MutableLiveData("")
    val orderID: LiveData<String> = _orderID
    fun setOrderID(orderID: String) {
        _orderID.value = orderID
    }

    private val _hotelName = MutableLiveData("")
    val hotelName: LiveData<String> = _hotelName
    fun setHotelName(hotelName: String) {
        _hotelName.value = hotelName
    }

    @SuppressLint("NewApi")
    private val _checkIn = MutableLiveData(Date.from(Instant.now()))
    val checkIn: LiveData<Date> = _checkIn
    fun setCheckIn(checkIn: Date) {
        _checkIn.value = checkIn
    }

    @SuppressLint("NewApi")
    private val _checkOut = MutableLiveData(Date.from(Instant.now()))
    val checkOut: LiveData<Date> = _checkOut
    fun setCheckOut(checkOut: Date) {
        _checkOut.value = checkOut
    }

    private val _totalGuests = MutableLiveData(2)
    val totalGuests: LiveData<Int> = _totalGuests
    fun setTotalGuests(totalGuests: Int) {
        _totalGuests.value = totalGuests
    }

    private val _numberOfRooms = MutableLiveData(1)
    val numberOfRooms: LiveData<Int> = _numberOfRooms
    fun setNumberOfRooms(numberOfRooms: Int) {
        _numberOfRooms.value = numberOfRooms
    }

    private val _totalNights = MutableLiveData(1)
    val totalNights: LiveData<Int> = _totalNights
    fun setTotalNights(totalGuests: Int) {
        _totalNights.value = totalGuests
    }

    private val _totalRoomsCost = MutableLiveData(0)
    val totalRoomsCost: LiveData<Int> = _totalRoomsCost
    fun setTotalRoomsCost(totalGuests: Int) {
        _totalRoomsCost.value = totalGuests
    }

    private val _hotelCost = MutableLiveData(0)
    val hotelCost: LiveData<Int> = _hotelCost
    fun setHotelCost(hotelCost: Int) {
        _hotelCost.value = hotelCost
    }

    private val _gSTCost = MutableLiveData(0)
    val gSTCost: LiveData<Int> = _gSTCost
    fun setGSTCost(gSTCost: Int) {
        _gSTCost.value = gSTCost
    }

    private val _subtotalCost = MutableLiveData(0)
    val subtotalCost: LiveData<Int> = _subtotalCost
    fun setSubtotalCost(grandTotal: Int) {
        _subtotalCost.value = grandTotal
    }

    private val _advanceCost = MutableLiveData(0)
    val advanceCost: LiveData<Int> = _advanceCost
    fun setAdvanceCost(advanceCost: Int) {
        _advanceCost.value = advanceCost
    }

    private val _youPayCost = MutableLiveData(0.0)
    val youPayCost: LiveData<Double> = _youPayCost
    fun setYouPayCost(youPayCost: Double) {
        _youPayCost.value = youPayCost
    }

    private val _totalAfterCoupon = MutableLiveData(0)
    val totalAfterCoupon: LiveData<Int> = _totalAfterCoupon
    fun setTotalAfterCoupon(totalAfterCoupon: Int) {
        _totalAfterCoupon.value = totalAfterCoupon
    }

    private val _amountDueCost = MutableLiveData(0)
    val amountDueCost: LiveData<Int> = _amountDueCost
    fun setAmountDueCost(amountDueCost: Int) {
        _amountDueCost.value = amountDueCost
    }

    private val _couponCode = MutableLiveData("")
    val couponCode: LiveData<String> = _couponCode
    fun setCouponCode(couponCode: String) {
        _couponCode.value = couponCode
    }

    private val _couponCost = MutableLiveData(0)
    val couponCost: LiveData<Int> = _couponCost
    fun setCouponCost(couponCost: Int) {
        _couponCost.value = couponCost
    }

    private val _leadTraveller = MutableLiveData("")
    val leadTraveller: LiveData<String> = _leadTraveller
    fun setLeadTraveller(leadTraveller: String) {
        _leadTraveller.value = leadTraveller
    }

    private val _leadEmail = MutableLiveData("")
    val leadEmail: LiveData<String> = _leadEmail
    fun setLeadEmail(leadEmail: String) {
        _leadEmail.value = leadEmail
    }

    private val _leadContact = MutableLiveData("")
    val leadContact: LiveData<String> = _leadContact
    fun setLeadContact(leadContact: String) {
        _leadContact.value = leadContact
    }

    private val _altNum = MutableLiveData("")
    val altNum: LiveData<String> = _altNum
    fun setAltNum(altNum: String) {
        _altNum.value = altNum
    }

    private val _fullAddress = MutableLiveData("")
    val fullAddress: LiveData<String> = _fullAddress
    fun setFullAddress(fullAddress: String) {
        _fullAddress.value = fullAddress
    }

    private val _additionalRequirement = MutableLiveData("")
    val additionalRequirement: LiveData<String> = _additionalRequirement
    fun setAdditionalReq(additionalReq: String) {
        _additionalRequirement.value = additionalReq
    }


    private val _paymentType = MutableLiveData("")
    val paymentType: LiveData<String> = _paymentType
    fun setPaymentType(paymentMethod: String) {
        _paymentType.value = paymentMethod
    }


}