package `in`.tourism.pilgrimagetour.view

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.fragment.app.commit
import androidx.fragment.app.replace
import androidx.lifecycle.ViewModelProvider
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase
import com.google.gson.Gson
import com.google.gson.JsonObject
import com.razorpay.Checkout
import com.razorpay.PaymentData
import com.razorpay.PaymentResultWithDataListener
import `in`.tourism.pilgrimagetour.BookingCompletedHotel
import `in`.tourism.pilgrimagetour.BookingIncompleteHotel
import `in`.tourism.pilgrimagetour.CreateOrder
import `in`.tourism.pilgrimagetour.OnBookHotelListener
import `in`.tourism.pilgrimagetour.R
import `in`.tourism.pilgrimagetour.RazorpayKey
import `in`.tourism.pilgrimagetour.RazorpaySecret
import `in`.tourism.pilgrimagetour.RetrofitClient
import `in`.tourism.pilgrimagetour.viewmodel.HotelBookingVM
import okhttp3.Credentials
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Response
import java.sql.Timestamp
import java.text.SimpleDateFormat
import java.util.Date
import kotlin.math.roundToInt

class ActivityHotelBooking : AppCompatActivity() , OnBookHotelListener, PaymentResultWithDataListener{

    private var docId: String? = null
    private var appName = ""

    private var razorpayKeyValue: String ?= null
    private var razorpaySecretValue: String ?= null

    private var completedBooking : BookingCompletedHotel?= null
    private var incompleteBooking : BookingIncompleteHotel?= null

    private lateinit var bookingCompleteCollection: CollectionReference
    private lateinit var incompleteBookingCollection: CollectionReference
    private var currentUser: FirebaseUser?= null


    private var razorpayAmount = 0
    private var paymentType = ""
    private var youPayCost: Double? = null
    private var leadTraveller: String? = null
    private var fullAddress: String? = null
    private var additionalRequirement: String? = null
    private var hotelName = ""

    private var totalGuests = 0
    private lateinit var checkInDate: Date
    private lateinit var checkOutDate: Date
    private var couponCode: String? = null
    private var couponCost: Int? = null
    private var leadEmail: String? = null
    private var leadContact: String? = null
    private var altNum: String? = null
    private var amountDueCost: Int? = null
    private lateinit var dateFormat: SimpleDateFormat
    private var numberOfRooms = 1
    private var totalNights = 1
    private var hotelCost = 0
    private var firebaseTimestamp: com.google.firebase.Timestamp? = null
    private var paymentSuccess: Boolean? = null

    private lateinit var sharedVM : HotelBookingVM
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_hotel_booking)

        docId = intent.getStringExtra("hotelName").toString()

        sharedVM = ViewModelProvider(this)[HotelBookingVM::class.java]

        Checkout.preload(applicationContext)

        currentUser = Firebase.auth.currentUser

        dateFormat = SimpleDateFormat("yyyy.MM.dd")

        appName = resources.getString(R.string.app_name)

        firebaseTimestamp = com.google.firebase.Timestamp(
            Date(
                Timestamp(
                    System.currentTimeMillis()
                ).time
            )
        )

        val db = FirebaseFirestore.getInstance()

        bookingCompleteCollection = db.collection("completedHotelBooking")
        incompleteBookingCollection = db.collection("incompleteHotelBooking")


        val razorpayKeyDoc = db.document("razorpay/key")

        razorpayKeyDoc.get().addOnCompleteListener {task ->
            if (task.isSuccessful) {

                val document = task.result

                if (document?.exists() == true) {

                    Log.d(TAG, "DocumentSnapshot data: " + document.data)

                    val razorpayKey = document.toObject(RazorpayKey::class.java)

                    razorpayKey?.let {
                        razorpayKeyValue = it.razorpayKey
                    }
                    Log.d("BookingsHotel", "Key : ${razorpayKeyValue.toString()}")
                } else {
                    Log.d("BookingsHotel", "No such document")
                }
            } else {
                Log.d("BookingsHotel", "get failed with ", task.exception)
            }

        }

        val razorpaySecretDoc = db.document("razorpay/secret")

        razorpaySecretDoc.get().addOnCompleteListener {task ->
            if (task.isSuccessful) {

                val document = task.result

                if (document?.exists() == true) {

                    Log.d(TAG, "DocumentSnapshot data: " + document.data)

                    val razorpaySecret = document.toObject(RazorpaySecret::class.java)


                    razorpaySecret?.let {
                        razorpaySecretValue = it.razorpaySecret
                    }
                    Log.d("BookingsHotel", "SECRET: ${razorpaySecretValue.toString()}")

                } else {
                    Log.d("BookingsHotel", "No such document")
                }
            } else {
                Log.d("BookingsHotel", "get failed with ", task.exception)
            }

        }

        Log.d("BookingsHotel", razorpayKeyValue.toString())
        Log.d("BookingsHotel", razorpaySecretValue.toString())

    }

    fun getDocID() = docId!!


    private fun callApiRazorpayOrderId(amount: Int){

        val credentials = Credentials.basic(razorpayKeyValue , razorpaySecretValue)
        Log.d(TAG, "callApiRazorpayOrderId Entered")
        val obj = JsonObject()
        obj.addProperty("amount",amount)
        obj.addProperty("currency","INR")

        RetrofitClient.create().getRazorPayOrderId(credentials , BASE_URL , obj)
            .enqueue(object : retrofit2.Callback<Any> {
                override fun onResponse(call: Call<Any>, response: Response<Any>) {
                    try {
                        Log.d(TAG, "Response Code: ${response.code()} ")
                        Log.d(TAG, "Response Body: ${response.body()?.toString()}")
                        if(response.isSuccessful){

                            Log.d(TAG, "Response is Successful")

                            if(response.body() != null){
                                Log.d(TAG, "Response : ${response.body().toString()}")
                                val model = response.body()
                                val finalResponse: String = Gson().toJson(model)
                                val razorpayOrderResponse = Gson().fromJson(
                                    finalResponse , CreateOrder::class.java
                                )

                                razorpayOrderResponse.let {
                                    val razorpayOrderId = it.id
                                    val amountPaid = it.amount
                                    val currencyINR = it.currency
                                    val receipt = it.receipt

                                    sharedVM.hotelName.value?.let { it1 ->
                                        sharedVM.leadContact.value?.let { it3 ->
                                            sharedVM.leadEmail.value?.let { it4 ->

                                                startPayment(
                                                    razorpayOrderId,
                                                    hotelName = it1,
                                                    amount = amountPaid,
                                                    email = it4,
                                                    mobile = it3
                                                )
                                            }
                                        }
                                    }
                                }
                            }
                        }

                    } catch (e: Exception){

                        e.printStackTrace()
                    }
                }

                override fun onFailure(call: Call<Any>, t: Throwable) {
                    Log.d(TAG, "onFailure: ${t.message}")
                }
            })
    }

    private fun startPayment(
        orderId: String,
        hotelName: String,
        amount: Long,
        email: String,
        mobile: String
    ) {
        val activity: Activity = this
        val co = Checkout()

        co.setKeyID(razorpayKeyValue)
        co.setImage(R.mipmap.ic_launcher)

        try {
            val options = JSONObject()
            options.put("name",appName)
            options.put("description", hotelName)
            //You can omit the image option to fetch the image from the dashboard
            options.put("image","https://s3.amazonaws.com/rzp-mobile/images/rzp.jpg")
            options.put("theme.color", R.color.primary_yellow_theme);
            options.put("currency","INR");
            options.put("order_id", orderId);
            options.put("amount",amount)//pass amount in currency subunits
            options.put("send_sms_hash", true)

            val retryObj = JSONObject();
            retryObj.put("enabled", true);
            retryObj.put("max_count", 1);
            options. put("retry", retryObj);

            val prefill = JSONObject()
            prefill.put("email",email)
            prefill.put("contact",mobile)
            options.put("prefill",prefill)


            co.open(activity,options)
        }catch (e: Exception){
            Toast.makeText(activity,"Error in payment: "+ e.message, Toast.LENGTH_LONG).show()
            e.printStackTrace()
        }

    }

    companion object {
        const val BASE_URL = "https://api.razorpay.com/v1/orders/"
        const val TAG = "BookingsHotelTAG"
    }

    override fun sendData(razorpayAmount: Int) {
//        Log.d(TAG, razorpayKeyValue.toString().uppercase())
//        Log.d(TAG, razorpaySecretValue.toString().uppercase())


        callApiRazorpayOrderId(amount = razorpayAmount)
    }

    override fun onPaymentSuccess(p0: String?, paymentData: PaymentData?) {
        Log.d(TAG, "ONPaymentSuccess: Entered")

        currentUser = Firebase.auth.currentUser

        sharedVM.setOrderID(paymentData?.orderId.toString())

        try{
            getFromVM()

            completedBooking = firebaseTimestamp?.let {
                BookingCompletedHotel(
                    paymentType,
                    additionalRequirement,
                    fullAddress,
                    leadEmail,
                    leadTraveller,
                    leadContact,
                    altNum,
                    paymentData?.orderId,
                    youPayCost?.roundToInt(),
                    amountDueCost,
                    paymentData?.paymentId,
                    hotelName,
                    dateFormat.format(checkInDate),
                    dateFormat.format(checkOutDate),
                    totalGuests,
                    currentUser?.uid,
                    "Processing",
                    "",
                    it
                )
            }
            Log.d(TAG, "OnPaymentSuccess : CompletedBooking")

            completePaymentSuccess()

            Log.d(TAG, "OnPaymentSuccess : CompletedSuccess")

        } catch (e: java.lang.Exception){
            e.printStackTrace()
        }

        paymentSuccess = true

    }

    override fun onPaymentError(p0: Int, p1: String?, p2: PaymentData?) {

        currentUser = Firebase.auth.currentUser

        try {
            getFromVM()

            incompleteBooking = BookingIncompleteHotel(
                additionalRequirement,
                altNum,
                dateFormat.format(checkInDate),
                dateFormat.format(checkOutDate),
                fullAddress,
                hotelName,
                leadEmail,
                leadTraveller,
                leadContact,
                firebaseTimestamp,
                totalGuests,
                currentUser?.uid,
                numberOfRooms,
                totalNights
            )

            Log.d(TAG, "OnPaymentFailure : InCompleteBooking")

            incompletePaymentError()

        } catch (e: java.lang.Exception){
            e.printStackTrace()
        }

        paymentSuccess = false

    }

    private fun completePaymentSuccess(){

        bookingCompleteCollection.add(completedBooking!!)
            .addOnSuccessListener { document ->

                Log.d(TAG, "Document written with id ${document.id}")

            }
            .addOnFailureListener {
                Log.d(TAG, "Error in adding document: ${it.message.toString()}")
            }

        incompleteBookingCollection.whereEqualTo("userID",currentUser?.uid)
            .whereEqualTo("hotelName",hotelName)
            .get()
            .addOnSuccessListener { documents ->

                for(document in documents){

                    document.reference.delete()

                    Log.d(TAG, "Document Deleted with id ${document.id}")
                }
            }
            .addOnFailureListener {
                Log.d(TAG, "Error in Deleting document: ${it.message.toString()}")
            }

    }

    private fun incompletePaymentError(){

        incompleteBookingCollection.add(incompleteBooking!!)
            .addOnSuccessListener { document ->
                Log.d(TAG, "Document written in Incomplete Booking with id ${document.id}")
            }
            .addOnFailureListener {
                Log.d(TAG, "Error in adding document: ${it.message.toString()}")
            }
    }


    override fun onResume() {
        super.onResume()

        if(paymentSuccess == true){

            supportFragmentManager.commit {
                replace<BookingSuccessFragment>(R.id.navHostFragmentHotel)
                setReorderingAllowed(true)
                addToBackStack("name") // Name can be null
            }
            paymentSuccess = null
        }

        else if(paymentSuccess == false){

            supportFragmentManager.commit {
                replace<BookingErrorFragment>(R.id.navHostFragmentHotel)
                setReorderingAllowed(true)
                addToBackStack("name") // Name can be null
            }

            paymentSuccess = null
        }

    }

    private fun getFromVM(){

        paymentType = sharedVM.paymentType.value!!
        additionalRequirement = sharedVM.additionalRequirement.value
        fullAddress = sharedVM.fullAddress.value
        leadEmail = sharedVM.leadEmail.value
        leadTraveller = sharedVM.leadTraveller.value
        leadContact = sharedVM.leadContact.value
        altNum = sharedVM.altNum.value
        youPayCost = sharedVM.youPayCost.value
        amountDueCost = sharedVM.amountDueCost.value
        hotelName = sharedVM.hotelName.value!!
        checkInDate = sharedVM.checkIn.value!!
        checkOutDate = sharedVM.checkOut.value!!

        totalGuests = sharedVM.totalGuests.value!!
        firebaseTimestamp = com.google.firebase.Timestamp(
            Date(
                Timestamp(
                    System.currentTimeMillis()
                ).time
            )
        )

        numberOfRooms = sharedVM.numberOfRooms.value!!
        totalNights = sharedVM.totalNights.value!!
        hotelCost = sharedVM.hotelCost.value!!


    }
    
    
}