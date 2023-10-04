package `in`.tourism.pilgrimagetour

import android.os.Parcelable
import com.google.firebase.Timestamp
import kotlinx.parcelize.Parcelize

@Parcelize
data class ItineraryDay(
    val textDay: String,
    val textDeparture: String,
    val placesList: List<String>,
    val image: Int? = null,
    val textExplanation: String,
    var isExpanded: Boolean = false
) : Parcelable
data class Destination(
    val actualName: String, val image: Int, val textName: String, val textNumOfPac: String
)

@Parcelize
data class DestinationHotel(
    val textDestination: String, val textHotels: String, val textMeals: String
) : Parcelable
data class SearchItems(
    val actualName: String, val textName: String, val textUrl: String
)

@Parcelize
data class TravelPackage(
    val ism: String,
    val viewType: Int,
    val image: Int,
    val textName: String,
    val textUrl: String,
    val textMrp: String,
    val textDiscounted: String,
    val textDays: String,
    val textNights: String,
    val textButton: String,
    val details: PackageDetails? = null,
    val oneDay: Boolean? = null
) : Parcelable

@Parcelize
data class PackageDetails(
    val textOverview: String,
    val daysList: List<ItineraryDay>,
    val hotelsList: List<DestinationHotel>? = null,
    val inclusionsList: List<String>,
    val exclusionsList: List<String>,
    val guidelinesList: List<String>,
    val fAQsList: List<FAQ2>
) : Parcelable

@Parcelize
data class Hotel(
    val textDestination: String = "", val textHotels: String = "", val textMeals: String = ""
) : Parcelable
data class PlaceDoc(val sights: List<PlacesToVisit>? = null)



@Parcelize
data class Day(
    val textDay: String = "",
    val textDeparture: String = "",
    val placesList: ArrayList<String>? = null,
    val image: String = "",
    val textExplanation: String = "",
    var isExpanded: Boolean = false
) : Parcelable

data class Destination2(
    val image: String = "",
    val textName: String = "",
    val position: Int? = null,
    val textNumOfPac: String = ""
)

@Parcelize
data class FAQ2(
    val textQ: String = "",
    val textQuestion: String = "",
    val textAnswer: String = "",
    var isExpanded: Boolean = false
) : Parcelable

data class ReviewsSliderVM(
    val textComment: String? = null,
    val image: String? = null,
    val textReview: String? = null,
    val rating: Float? = null,
    val textName: String? = null
)

class SliderBanner() {
    var url: String = ""
    var name: String = ""
}

@Parcelize
data class TravelPac(
    var banner: String? = null,
    val chardham: String? = null,
    var days: String? = null,
    val daysList: List<Day>? = null,
    var discountedPrice: Int? = null,
    val exclusionsList: List<String>? = null,
    val fAQsList: List<FAQ2>? = null,
    val guidelinesList: List<String>? = null,
    val inclusionsList: List<String>? = null,
    var mrp: Int? = null,
    var name: String? = null,
    var nights: String? = null,
    val religious: String? = null,
    val textOverview: String? = null,
    val topSelling: String? = null,
    var viewType: Int? = null
) : Parcelable

class User() {

    constructor(
        email: String?, id: String?, name: String?, photoUrl: String?, timestamp: Timestamp
    ) : this() {
        this.timestamp = timestamp
        this.email = email
        this.id = id
        this.name = name
        this.photoUrl = photoUrl
    }

    var timestamp: Timestamp? = null
    var email: String? = null
    var id: String? = null
    var name: String? = null
    var photoUrl: String? = null
}
