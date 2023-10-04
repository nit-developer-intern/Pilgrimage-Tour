package `in`.tourism.pilgrimagetour

import android.net.Credentials
import com.google.gson.JsonObject
import org.json.JSONObject
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.Url
import java.time.LocalDate

interface OnCardClickListener {
    fun onClick(detailed: Boolean, id: String)
}

interface OnCardClickListener2 {
    fun onClick(detailed: Boolean?, travelPac: TravelPac?)
}

interface OnCouponClickListener {
    fun onClick(code: String, cost: Int)
}

interface FragmentInteractionListener {
    fun sendData(fragment: String, cab: String)
}

interface OnBookingFragmentInteractionListener {
    fun sendData(
        paymentMethod: String,
        razorpayAmount: Int,
        yatraPackage: String,
        yatraDate: LocalDate,
        totalYatri: Int
    )
}

interface OnBookHotelListener {
    fun sendData(razorpayAmount: Int)

}

interface OnHotelListener {
    fun sendData(
        room1adults: Int,
        room2adults: Int,
        room3adults: Int,
        room4adults: Int,
        rooms: Int,
        bed11: Boolean? = null,
        bed12: Boolean? = null,
        bed21: Boolean? = null,
        bed22: Boolean? = null,
        bed31: Boolean? = null,
        bed32: Boolean? = null,
        bed41: Boolean? = null,
        bed42: Boolean? = null,
        totalChildren: Int,
        totalAdults: Int
    )
}

interface ApiCall {
    @Headers("Content-Type: application/json")
    @POST()
    fun getRazorPayOrderId(
        @Header("Authorization") credentials: String, @Url url: String, @Body jsonObject: JsonObject
    ): Call<Any>
}