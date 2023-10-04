package `in`.tourism.pilgrimagetour.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import `in`.tourism.pilgrimagetour.CustomerDetails
import java.time.LocalDate

class BookingViewModel : ViewModel() {

    private val _viewType = MutableLiveData(1)
    val viewType: LiveData<Int> = _viewType
    fun setViewType(viewType: Int) {
        _viewType.value = viewType
    }

    private val _date = MutableLiveData(LocalDate.now())
    val date: LiveData<LocalDate> = _date
    fun setDate(date: LocalDate) {
        _date.value = date
    }

    private val _numOfAdults = MutableLiveData(1)
    val numberofadults: LiveData<Int> = _numOfAdults
    fun setNumberofAdults(numberofAdults: Int) {
        _numOfAdults.value = numberofAdults
    }

    private val _numberOfChildren = MutableLiveData(0)
    val numberOfChildren: LiveData<Int> = _numberOfChildren
    fun setNumberOfChildren(numberOfChildren: Int) {
        _numberOfChildren.value = numberOfChildren
    }

    private val _packageName = MutableLiveData("")
    val packageName: LiveData<String> = _packageName
    fun setPackageName(packageName: String) {
        _packageName.value = packageName
    }

    private val _packageDays = MutableLiveData("")
    val packageDays: LiveData<String> = _packageDays
    fun setPackageDays(packageDays: String) {
        _packageDays.value = packageDays
    }

    private val _packageAdultCost = MutableLiveData(0)
    val packageAdultCost: LiveData<Int> = _packageAdultCost
    fun setPackageAdultCost(packageAdultCost: Int) {
        _packageAdultCost.value = packageAdultCost
    }

    private val _packageChildrenCost = MutableLiveData(0)
    val packageChildrenCost: LiveData<Int> = _packageChildrenCost
    fun setPackageChildrenCost(packageChildrenCost: Int) {
        _packageChildrenCost.value = packageChildrenCost
    }

    private val _gSTCost = MutableLiveData(0.0)
    val gSTCost: LiveData<Double> = _gSTCost
    fun setGSTCost(gSTCost: Double) {
        _gSTCost.value = gSTCost
    }

    private val _grandTotal = MutableLiveData(0)
    val grandTotal: LiveData<Int> = _grandTotal
    fun setGrandTotal(grandTotal: Int) {
        _grandTotal.value = grandTotal
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

    private val _totalafterCoupon = MutableLiveData(0)
    val totalafterCoupon: LiveData<Int> = _totalafterCoupon
    fun setTotalaftercoupon(totalafterCoupon: Int) {
        _totalafterCoupon.value = totalafterCoupon
    }

    private val _advanceCost = MutableLiveData(0)
    val advanceCost: LiveData<Int> = _advanceCost
    fun setAdvanceCost(advanceCost: Int) {
        _advanceCost.value = advanceCost
    }

    private val _amountDueCost = MutableLiveData(0)
    val amountDueCost: LiveData<Int> = _amountDueCost
    fun setAmountDueCost(amountDueCost: Int) {
        _amountDueCost.value = amountDueCost
    }

    private val _userPayCost = MutableLiveData(0.0)
    val userPayCost: LiveData<Double> = _userPayCost
    fun setUserPayCost(userPayCost: Double) {
        _userPayCost.value = userPayCost
    }

    var customersList: ArrayList<CustomerDetails> = ArrayList()
    fun setCustomerList(customersList: ArrayList<CustomerDetails>) {
        this.customersList = customersList
    }

    var adultsList: ArrayList<CustomerDetails> = ArrayList()
    fun setadultsList(adultsList: ArrayList<CustomerDetails>) {
        this.adultsList = adultsList
    }

    var childrenList: ArrayList<CustomerDetails> = ArrayList()
    fun setChildList(childrenList: ArrayList<CustomerDetails>) {
        this.childrenList = childrenList
    }


    private val _leadTraveller = MutableLiveData("")
    val leadTraveller: LiveData<String> = _leadTraveller
    fun setLeadTraveller(leadTraveller: String) {
        _leadTraveller.value = leadTraveller
    }

    private val _iDType = MutableLiveData("")
    val iDType: LiveData<String> = _iDType
    fun setIDType(iDType: String) {
        _iDType.value = iDType
    }

    private val _iDNumber = MutableLiveData("")
    val iDNumber: LiveData<String> = _iDNumber
    fun setIDNumber(iDNumber: String) {
        _iDNumber.value = iDNumber
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

    private val _fullAddress = MutableLiveData("")
    val fullAddress: LiveData<String> = _fullAddress
    fun setFullAddress(fullAddress: String) {
        _fullAddress.value = fullAddress
    }

    private val _arrivalDate = MutableLiveData("")
    val arrivalDate: LiveData<String> = _arrivalDate
    fun setArrivalDate(arrivalDate: String) {
        _arrivalDate.value = arrivalDate
    }

    private val _arrivalCity = MutableLiveData("")
    val arrivalCity: LiveData<String> = _arrivalCity
    fun setArrivalCity(arrivalCity: String) {
        _arrivalCity.value = arrivalCity
    }

    private val _additionalRequirement = MutableLiveData("")
    val additionalRequirement: LiveData<String> = _additionalRequirement
    fun setAdditionalRequirement(additionalRequirement: String) {
        _additionalRequirement.value = additionalRequirement
    }

    private val _paymentMethod = MutableLiveData("")
    val paymentType: LiveData<String> = _paymentMethod
    fun setPaymentType(paymentMethod: String) {
        _paymentMethod.value = paymentMethod
    }

    private val _isAdvance = MutableLiveData(true)
    val isAdvance: LiveData<Boolean> = _isAdvance
    fun setAdvance(isAdvance: Boolean) {
        _isAdvance.value = isAdvance
    }

    private val _orderID = MutableLiveData("")
    val orderID: LiveData<String> = _orderID
    fun setOrderID(orderID: String) {
        _orderID.value = orderID
    }


    private val _paymentID = MutableLiveData("")
    val paymentID: LiveData<String> = _paymentID
    fun setPaymentID(paymentID: String) {
        _paymentID.value = paymentID
    }

    private val _bookingStatus = MutableLiveData("")
    val bookingStatus: LiveData<String> = _bookingStatus
    fun setBookingStatus(bookingStatus: String) {
        _bookingStatus.value = bookingStatus
    }

    private val _bookingPDF = MutableLiveData("")
    val bookingPDF: LiveData<String> = _bookingPDF
    fun setBookingPDF(bookingPDF: String) {
        _bookingPDF.value = bookingPDF
    }


}