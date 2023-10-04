package `in`.tourism.pilgrimagetour

import android.os.Parcelable
import com.google.firebase.Timestamp
import kotlinx.parcelize.Parcelize

data class Cabs(
    val carModel: String? = null,
    val totalSeats: String? = null,
    val totalMembers: String? = null,
    val mode: String? = null,
    val images: List<Int>? = null
)

class EnquiryCab() {

    constructor(
        timestamp: Timestamp,
        cabModel: String,
        destinationFrom: String,
        destinationTo: String,
        yatriName: String,
        emailAddress: String,
        mobileNumber: String,
        dateOfTravel: String,
        numberOfPeople: String,
        additionalInformation: String
    ) : this() {
        this.timestamp = timestamp
        this.cabModel = cabModel
        this.destinationFrom = destinationFrom
        this.destinationTo = destinationTo
        this.yatriName = yatriName
        this.emailAddress = emailAddress
        this.mobileNumber = mobileNumber
        this.dateOfTravel = dateOfTravel
        this.numberOfPeople = numberOfPeople
        this.additionalInformation = additionalInformation
    }

    var timestamp: Timestamp? = null
    var cabModel: String = ""
    var destinationFrom: String = ""
    var destinationTo: String = ""
    var yatriName: String = ""
    var emailAddress: String = ""
    var mobileNumber: String = ""
    var dateOfTravel: String = ""
    var numberOfPeople: String = ""
    var additionalInformation: String = ""
}

class ChildCost() {
    var childCostPercentHel: Double? = null
    var childCostPercentNonHel: Double? = null
}

class BookingCompleted() {

    constructor(
        additionalRequirement: String?,
        customersList: List<CustomerDetails>?,
        fullAddress: String?,
        iDType: String?,
        iDNumber: String?,
        leadEmail: String?,
        leadTraveller: String?,
        mobileNumber: String?,
        orderID: String?,
        paymentNow: Int?,
        paymentDue: Int?,
        couponCost: Int?,
        paymentType: String?,
        paymentID: String?,
        packageName: String?,
        packageDays: String?,
        travelDate: String?,
        numberAdults: Int?,
        numberChildren: Int?,
        userID: String?,
        status: String?,
        timestamp: Timestamp,
        pdf: String?
    ) : this() {
        this.timestamp = timestamp
        this.customersList = customersList
        this.leadTraveller = leadTraveller
        this.iDType = iDType
        this.iDNumber = iDNumber
        this.leadEmail = leadEmail
        this.mobileNumber = mobileNumber
        this.fullAddress = fullAddress
        this.additionalRequirement = additionalRequirement
        this.packageName = packageName
        this.packageDays = packageDays
        this.travelDate = travelDate
        this.numberAdults = numberAdults
        this.numberChildren = numberChildren
        this.paymentNow = paymentNow
        this.paymentDue = paymentDue
        this.couponCost = couponCost
        this.paymentType = paymentType
        this.orderID = orderID
        this.paymentID = paymentID
        this.userID = userID
        this.pdf = pdf
        this.status = status
    }

    var timestamp: Timestamp? = null
    var customersList: List<CustomerDetails>? = null
    var leadTraveller: String? = null
    var iDType: String? = null
    var iDNumber: String? = null
    var leadEmail: String? = null
    var mobileNumber: String? = null
    var fullAddress: String? = null
    var additionalRequirement: String? = null
    var packageName: String? = null
    var packageDays: String? = null
    var travelDate: String? = null
    var numberAdults: Int? = null
    var numberChildren: Int? = null
    var paymentNow: Int? = null
    var paymentDue: Int? = null
    var couponCost: Int? = null

    var paymentType: String? = null
    var orderID: String? = null
    var paymentID: String? = null
    var userID: String? = null
    var status: String? = null
    var pdf: String? = null
}

class ContactInfo() {
    val phoneNumber: String? = null
    val email: String? = null
    val whatsappLink: String? = null
    val address: String? = null
}

class Coupon() {
    val cost: Int? = null
    val type: Int? = null
    val couponCode: String? = null
    val description: String? = null
}

data class CreateOrder(
    val amount: Long,
    val amount_due: Long,
    val amount_paid: Long,
    val attempts: Int,
    val created_at: Int,
    val currency: String,
    val entity: String,
    val id: String,
    val offer_id: Any,
    val receipt: String,
    val status: String
)

class CustomerDetails() {

    constructor(
        customerHeading: String,
        customerNAme: String,
        customerGender: String,
        customerAge: String,
        customerWeight: String
    ) : this() {

        this.customerHeading = customerHeading
        this.customerAge = customerAge
        this.customerGender = customerGender
        this.customerNAme = customerNAme
        this.customerWeight = customerWeight
    }

    var customerHeading: String = ""
    var customerNAme: String = ""
    var customerGender: String = ""
    var customerAge: String = ""
    var customerWeight: String = ""
}

class Enquiry() {

    constructor(
        timestamp: Timestamp,
        destinationText: String,
        yatriName: String,
        emailAddress: String,
        mobileNumber: String,
        dateOfTravel: String,
        numberOfPeople: String,
        additionalInformation: String
    ) : this() {
        this.timestamp = timestamp
        this.destinationText = destinationText
        this.yatriName = yatriName
        this.emailAddress = emailAddress
        this.mobileNumber = mobileNumber
        this.dateOfTravel = dateOfTravel
        this.numberOfPeople = numberOfPeople
        this.additionalInformation = additionalInformation
    }

    var timestamp: Timestamp? = null
    var destinationText: String = ""
    var yatriName: String = ""
    var emailAddress: String = ""
    var mobileNumber: String = ""
    var dateOfTravel: String = ""
    var numberOfPeople: String = ""
    var additionalInformation: String = ""
}

@Parcelize
data class HotelDetails(

    val available: String = "",
    val discounted: Int? = null,
    val hotelFacilities: List<HotelFacility>? = null,
    val hotelImages: List<String>? = null,
    val hotelName: String = "",
    val hotelType: String = "",
    val mRP: Int? = null,
    val parking: String = "",
    val restaurants: String = "",
    val textAddress: String = "",
    val textDestination: String = "",
    val textOverview: String? = null
) : Parcelable

class HotelEnquiry() {

    constructor(
        timestamp: Timestamp,
        hotelName: String,
        checkInDate: String,
        checkOutDate: String,
        yatriName: String,
        emailAddress: String,
        mobileNumber: String,
        numberOfAdults: String,
        numberOfChildren: String,
        additionalInformation: String
    ) : this() {
        this.timestamp = timestamp
        this.hotelName = hotelName
        this.checkInDate = checkInDate
        this.checkOutDate = checkOutDate
        this.yatriName = yatriName
        this.emailAddress = emailAddress
        this.mobileNumber = mobileNumber
        this.numberOfAdults = numberOfAdults
        this.numberOfChildren = numberOfChildren
        this.additionalInformation = additionalInformation
    }

    var timestamp: Timestamp? = null
    var hotelName: String = ""
    var checkInDate: String = ""
    var checkOutDate: String = ""
    var yatriName: String = ""
    var emailAddress: String = ""
    var mobileNumber: String = ""
    var numberOfAdults: String = ""
    var numberOfChildren: String = ""
    var additionalInformation: String = ""
}

@Parcelize
data class HotelFacility(

    val name: String = "", val image: String = "", val provided: Boolean? = null
) : Parcelable

data class HotelsDest(
    val image: String = "", val name: String = ""
)

class IncompleteBooking() {

    constructor(
        timestamp: Timestamp,
        numberAdults: Int?,
        numberChildren: Int?,
        adultCost: Int?,
        childCost: Int?,
        gSTCost: Double?,
        subTotalCost: Int?,
        advanceCost: Int?,
        totalAfterCoupon: Int?,
        arrivalDate: String?,
        arrivalCity: String?,
        customersList: List<CustomerDetails>?,
        leadTraveller: String?,
        iDType: String?,
        iDNumber: String?,
        leadEmail: String?,
        mobileNumber: String?,
        fullAddress: String?,
        additionalRequirement: String?,
        packageName: String?,
        packageDays: String?,
        travelDate: String?,
        totalPersons: Int?,
        paymentNow: Int?,
        paymentDue: Int?,
        paymentType: String?,
        userID: String?
    ) : this() {
        this.timestamp = timestamp
        this.numberAdults = numberAdults
        this.numberChildren = numberChildren
        this.adultCost = adultCost
        this.childCost = childCost
        this.gSTCost = gSTCost
        this.subTotalCost = subTotalCost
        this.advanceCost = advanceCost
        this.totalAfterCoupon = totalAfterCoupon
        this.arrivalDate = arrivalDate
        this.arrivalCity = arrivalCity
        this.customersList = customersList
        this.leadTraveller = leadTraveller
        this.iDType = iDType
        this.iDNumber = iDNumber
        this.leadEmail = leadEmail
        this.mobileNumber = mobileNumber
        this.fullAddress = fullAddress
        this.additionalRequirement = additionalRequirement
        this.packageName = packageName
        this.packageDays = packageDays
        this.travelDate = travelDate
        this.totalPersons = totalPersons
        this.paymentNow = paymentNow
        this.paymentDue = paymentDue
        this.paymentMethod = paymentType
        this.userID = userID
    }

    var timestamp: Timestamp? = null
    var travelDate: String? = null
    var numberAdults: Int? = null
    var numberChildren: Int? = null
    var totalPersons: Int? = null
    var adultCost: Int? = null
    var childCost: Int? = null
    var gSTCost: Double? = null
    private var subTotalCost: Int? = null
    var advanceCost: Int? = null
    var totalAfterCoupon: Int? = null
    var customersList: List<CustomerDetails>? = null
    var leadTraveller: String? = null
    var iDType: String? = null
    var iDNumber: String? = null
    var leadEmail: String? = null
    var mobileNumber: String? = null
    var fullAddress: String? = null
    var arrivalDate: String? = null
    var arrivalCity: String? = null
    var additionalRequirement: String? = null
    var packageName: String? = null
    var paymentNow: Int? = null
    var paymentDue: Int? = null
    var packageDays: String? = null
    var paymentMethod: String? = null
    var userID: String? = null
}

data class PlacesToVisit(val destination: String = "", val places: List<String>? = null)

class RazorpayKey() {
    var razorpayKey: String? = null
}

class RazorpaySecret() {
    var razorpaySecret: String? = null
}

class ReviewHotel() {

    constructor(
        email: String?,
        id: String?,
        textReview: String?,
        rating: Float?,
        userName: String?,
        profile: String?,
        textCity: String?,
        hotelName: String?,
        textTime: String?,
        timestamp: Timestamp
    ) : this() {
        this.email = email
        this.timestamp = timestamp
        this.id = id
        this.textReview = textReview
        this.textCity = textCity
        this.rating = rating
        this.userName = userName
        this.profile = profile
        this.hotelName = hotelName
        this.textTime = textTime
    }

    var email: String? = null
    var timestamp: Timestamp? = null
    var id: String? = null
    var textReview: String? = null
    var rating: Float? = null
    var userName: String? = null
    var profile: String? = null
    var textCity: String? = null
    var hotelName: String? = null
    var textTime: String? = null
}

class Review() {

    constructor(
        email: String?,
        timestamp: Timestamp,
        id: String?,
        textReview: String?,
        rating: Float?,
        textName: String?,
        textCity: String?,
        textPackageName: String?,
        textTime: String?
    ) : this() {
        this.email = email
        this.timestamp = timestamp
        this.id = id
        this.textReview = textReview
        this.textCity = textCity
        this.rating = rating
        this.textName = textName
        this.textPackageName = textPackageName
        this.textTime = textTime
    }

    var email: String? = null
    var timestamp: Timestamp? = null
    var id: String? = null
    var textReview: String? = null
    var rating: Float? = null
    var textName: String? = null
    var textCity: String? = null
    var textPackageName: String? = null
    var textTime: String? = null
}

class BookingCompletedHotel() {

    constructor(
        paymentType: String?,
        additionalRequirement: String?,
        fullAddress: String?,
        leadEmail: String?,
        leadTraveller: String?,
        mobileNumber: String?,
        altNumber: String?,
        orderID: String?,
        paymentNow: Int?,
        paymentDue: Int?,
        paymentID: String?,
        hotelName: String?,
        checkIn: String?,
        checkOut: String?,
        totalGuests: Int?,
        userID: String?,
        status: String?,
        pdf: String?,
        timestamp: Timestamp
    ) : this() {
        this.paymentType = paymentType
        this.timestamp = timestamp
        this.altNumber = altNumber
        this.leadTraveller = leadTraveller
        this.leadEmail = leadEmail
        this.mobileNumber = mobileNumber
        this.fullAddress = fullAddress
        this.additionalRequirement = additionalRequirement
        this.hotelName = hotelName
        this.checkIn = checkIn
        this.checkOut = checkOut
        this.totalGuests = totalGuests
        this.paymentNow = paymentNow
        this.paymentDue = paymentDue
        this.orderID = orderID
        this.paymentID = paymentID
        this.userID = userID
        this.pdf = pdf
        this.status = status
    }

    var timestamp: Timestamp? = null
    var altNumber: String? = null
    var leadTraveller: String? = null
    var leadEmail: String? = null
    var mobileNumber: String? = null
    var fullAddress: String? = null
    var additionalRequirement: String? = null
    var hotelName: String? = null
    var checkIn: String? = null
    var checkOut: String? = null
    var totalGuests: Int? = null
    var paymentNow: Int? = null
    var paymentDue: Int? = null
    var paymentType: String? = null
    var orderID: String? = null
    var paymentID: String? = null
    var userID: String? = null
    var status: String? = null
    var pdf: String? = null
}

class BookingIncompleteHotel() {

    constructor(
        additionalRequirement: String?,
        altNumber: String?,
        checkIn: String?,
        checkOut: String?,
        fullAddress: String?,
        hotelName: String?,
        leadEmail: String?,
        leadTraveller: String?,
        mobileNumber: String?,
        timestamp: Timestamp?,
        totalGuests: Int?,
        userID: String?,
        totalRooms: Int?,
        totalNights: Int?
    ) : this() {
        this.totalGuests = totalGuests
        this.timestamp = timestamp
        this.altNumber = altNumber
        this.leadTraveller = leadTraveller
        this.leadEmail = leadEmail
        this.mobileNumber = mobileNumber
        this.fullAddress = fullAddress
        this.additionalRequirement = additionalRequirement
        this.hotelName = hotelName
        this.checkIn = checkIn
        this.checkOut = checkOut
        this.userID = userID

        this.totalRooms = totalRooms
        this.totalNights = totalNights

    }

    var totalGuests: Int? = null
    var timestamp: Timestamp? = null
    var altNumber: String? = null
    var checkIn: String? = null
    var checkOut: String? = null
    var leadTraveller: String? = null
    var leadEmail: String? = null
    var mobileNumber: String? = null
    var fullAddress: String? = null
    var additionalRequirement: String? = null
    var hotelName: String? = null
    var userID: String? = null
    var totalRooms: Int? = null
    var totalNights: Int? = null
}
