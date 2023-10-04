package `in`.tourism.pilgrimagetour.view

import android.app.Activity
import android.content.Context
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.VibrationEffect
import android.os.VibratorManager
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
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
import `in`.tourism.pilgrimagetour.*
import `in`.tourism.pilgrimagetour.databinding.ActivityBookingsBinding
import `in`.tourism.pilgrimagetour.viewmodel.BookingViewModel
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Response
import java.sql.Timestamp
import java.time.LocalDate
import java.util.*
import kotlin.collections.ArrayList
import kotlin.math.roundToInt

class BookingActivity : AppCompatActivity(), PaymentResultWithDataListener,
    OnBookingFragmentInteractionListener {

    private lateinit var binding: ActivityBookingsBinding
    private lateinit var navController: NavController
    private lateinit var incompleteBookingCollection: CollectionReference
    private var currentUser: FirebaseUser? = null
    private var razorpayKeyValue: String? = null
    private var razorpaySecretValue: String? = null
    private var appName = ""
    private var packageName = ""
    private var packageDays = ""
    private lateinit var selectedDate: LocalDate

    private var totalNumberOfPersons = 1
    private lateinit var customersList: ArrayList<CustomerDetails>
    private lateinit var adultsList: ArrayList<CustomerDetails>
    private lateinit var childrenList: ArrayList<CustomerDetails>
    private var leadTraveller: String? = null
    private var iDType: String? = null
    private var iDNumber: String? = null
    private var fullAddress: String? = null
    private var additionalRequirement: String? = null

    private var numberOfAdults = 1
    private var numberOfChildren = 0
    private var userPayCost: Double? = null
    private var amountDueCost: Int? = null
    private var paymentType: String? = null
    private var packageAdultCost: Int? = null
    private var packageChildCost: Int? = null
    private var gSTCost: Double? = null
    private var grandTotal: Int? = null

    private var couponCost: Int? = null
    private var advanceCost: Int? = null
    private var totalAfterCoupon: Int? = null
    private var arrivalDate: String? = null
    private var arrivalCity: String? = null
    private var leadEmail: String? = null
    private var leadContact: String? = null
    private var selectedDateFormatted: String? = null
    private lateinit var completedBooking: BookingCompleted

    private lateinit var incompleteBooking: IncompleteBooking
    private var docId: String? = null
    private var paymentSuccess: Boolean? = null
    private lateinit var sharedViewModel: BookingViewModel

    val db = FirebaseFirestore.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityBookingsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        currentUser = Firebase.auth.currentUser

        incompleteBookingCollection = db.collection("incompleteBooking")

        sharedViewModel = ViewModelProvider(this)[BookingViewModel::class.java]

        appName = resources.getString(R.string.app_name)

        Checkout.preload(applicationContext)

        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.navHostFragmentBooking) as NavHostFragment
        navController = navHostFragment.navController

        docId = intent.getStringExtra("detailsKey")

        sharedViewModel.setPackageName(docId!!)

        val razorpayKeyDoc = db.document("razorpay/key")

        razorpayKeyDoc.get().addOnCompleteListener { task ->
            if (task.isSuccessful) {

                val document = task.result

                if (document?.exists() == true) {

                    Log.d(TAG, "DocumentSnapshot data: " + document.data)

                    val razorpayKey = document.toObject(RazorpayKey::class.java)

                    razorpayKey?.let {
                        razorpayKeyValue = it.razorpayKey
                    }
                    Log.d(TAG, "Key : ${razorpayKeyValue.toString()}")
                } else {
                    Log.d("TAG", "No such document")
                }
            } else {
                Log.d("TAG", "get failed with ", task.exception)
            }

        }

        val razorpaySecretDoc = db.document("razorpay/secret")

        razorpaySecretDoc.get().addOnCompleteListener { task ->
            if (task.isSuccessful) {

                val document = task.result

                if (document?.exists() == true) {

                    Log.d(TAG, "DocumentSnapshot data: " + document.data)

                    val razorpaySecret = document.toObject(RazorpaySecret::class.java)


                    razorpaySecret?.let {
                        razorpaySecretValue = it.razorpaySecret
                    }
                    Log.d(TAG, "SECRET: ${razorpaySecretValue.toString()}")

                } else {
                    Log.d("TAG", "No such document")
                }
            } else {
                Log.d("TAG", "get failed with ", task.exception)
            }

        }

        razorpayKeyValue?.let { Log.d("Razorpay", it) }
        Log.d(TAG, "$razorpaySecretValue")

        if (intent.extras?.getBoolean("showHistory") == true)

            navController.navigate(R.id.bookingOrderHistoryFragment)

    }

    fun getDocID() = docId

    private fun callApiRazorpayOrderId(amount: Int?) {

        val credentials = okhttp3.Credentials.basic(razorpayKeyValue, razorpaySecretValue)
        Log.d(TAG, "callApiRazorpayOrderId Entered")
        val obj = JsonObject()
        obj.addProperty("amount", amount)
        obj.addProperty("currency", "INR")

        RetrofitClient.create().getRazorPayOrderId(credentials, BASE_URL, obj)
            .enqueue(object : retrofit2.Callback<Any> {
                override fun onResponse(call: Call<Any>, response: Response<Any>) {

                    try {
                        Log.d(TAG, "Response Code: ${response.code()} ")
                        Log.d(TAG, "Response Body: ${response.body()?.toString()}")
                        if (response.isSuccessful) {

                            Log.d(TAG, "Response is Successful")

                            if (response.body() != null) {
                                Log.d(TAG, "Response : ${response.body().toString()}")
                                val model = response.body()
                                val finalResponse: String = Gson().toJson(model)
                                val razorpayOrderResponse = Gson().fromJson(
                                    finalResponse, CreateOrder::class.java
                                )

                                razorpayOrderResponse.let {
                                    val razorpayOrderId = it.id
                                    val amountPaid = it.amount
                                    val currencyINR = it.currency
                                    val receipt = it.receipt

                                    sharedViewModel.packageName.value?.let { it1 ->
                                        sharedViewModel.packageDays.value?.let { it2 ->
                                            sharedViewModel.leadContact.value?.let { it3 ->
                                                sharedViewModel.leadEmail.value?.let { it4 ->

                                                    startPayment(
                                                        razorpayOrderId,
                                                        packageName = it1,
                                                        packageDays = it2,
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
                        }

                    } catch (e: Exception) {

                        e.printStackTrace()
                    }
                }

                override fun onFailure(call: Call<Any>, t: Throwable) {
                    Log.d(TAG, "onFailure: ${t.message}")
                }


            })


    }

    override fun sendData(
        paymentMethod: String,
        razorpayAmount: Int,
        yatraPackage: String,
        yatraDate: LocalDate,
        totalYatri: Int
    ) {
        totalNumberOfPersons = totalYatri

        packageName = yatraPackage

        selectedDate = yatraDate

        Log.d(TAG, "SendData Entered")

        callApiRazorpayOrderId(razorpayAmount)
    }

    private fun startPayment(
        orderId: String,
        packageName: String,
        packageDays: String,
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
            options.put("name", appName)
            options.put("description", "$packageName - $packageDays")
            //You can omit the image option to fetch the image from the dashboard
            options.put("image", "https://s3.amazonaws.com/rzp-mobile/images/rzp.jpg")
            options.put("theme.color", R.color.primary_yellow_theme);
            options.put("currency", "INR");
            options.put("order_id", orderId);
            options.put("amount", amount)//pass amount in currency subunits
            options.put("send_sms_hash", true)

            val retryObj = JSONObject();
            retryObj.put("enabled", true);
            retryObj.put("max_count", 1);
            options.put("retry", retryObj);

            val prefill = JSONObject()
            prefill.put("email", email)
            prefill.put("contact", mobile)
            options.put("prefill", prefill)


            co.open(activity, options)
        } catch (e: Exception) {
            Toast.makeText(activity, "Error in payment: " + e.message, Toast.LENGTH_LONG).show()
            e.printStackTrace()
        }
    }

    override fun onPaymentSuccess(p0: String?, paymentData: PaymentData?) {

        Log.d(TAG, "ONPaymentSuccess: Entered")

        try {
            paymentData?.orderId?.let { sharedViewModel.setOrderID(it) }

            paymentData?.paymentId?.let { sharedViewModel.setPaymentID(it) }

            selectedDate = sharedViewModel.date.value!!
            selectedDateFormatted =
                "${selectedDate.dayOfMonth} ${selectedDate.month} , ${selectedDate.year}"

            packageName = sharedViewModel.packageName.value!!
            packageDays = sharedViewModel.packageDays.value!!

            customersList = sharedViewModel.customersList
            numberOfAdults = sharedViewModel.numberofadults.value!!
            numberOfChildren = sharedViewModel.numberOfChildren.value!!
            adultsList = sharedViewModel.adultsList
            childrenList = sharedViewModel.childrenList

            userPayCost = sharedViewModel.userPayCost.value
            amountDueCost = sharedViewModel.amountDueCost.value
            couponCost = sharedViewModel.couponCost.value
            paymentType = sharedViewModel.paymentType.value

            leadTraveller = sharedViewModel.leadTraveller.value
            iDType = sharedViewModel.iDType.value!!
            iDNumber = sharedViewModel.iDNumber.value!!
            leadEmail = sharedViewModel.leadEmail.value!!
            fullAddress = sharedViewModel.fullAddress.value!!
            additionalRequirement = sharedViewModel.additionalRequirement.value!!

            currentUser = Firebase.auth.currentUser

            completedBooking = BookingCompleted(
                additionalRequirement,
                customersList,
                fullAddress,
                iDType,
                iDNumber,
                leadEmail,
                leadTraveller,
                leadContact,
                paymentData?.orderId,
                userPayCost?.roundToInt(),
                amountDueCost,
                couponCost,
                paymentType,
                paymentData?.paymentId,
                packageName,
                packageDays,
                "$selectedDate",
                numberOfAdults,
                numberOfChildren,
                currentUser?.uid,
                "Processing",
                com.google.firebase.Timestamp(
                    Date(
                        Timestamp(
                            System.currentTimeMillis()
                        ).time
                    )
                ),
                ""
            )

            Log.d(TAG, "OnPaymentSuccess : CompletedBooking")

            completePaymentSuccess()

            Log.d(TAG, "OnPaymentSuccess : CompletedSuccess")

        } catch (e: java.lang.Exception) {
            e.printStackTrace()
        }
        paymentSuccess = true

    }

    override fun onPaymentError(p0: Int, p1: String?, paymentData: PaymentData?) {

        currentUser = Firebase.auth.currentUser

        try {

            numberOfAdults = sharedViewModel.numberofadults.value!!
            numberOfChildren = sharedViewModel.numberOfChildren.value!!
            packageAdultCost = sharedViewModel.packageAdultCost.value!!
            packageChildCost = sharedViewModel.packageChildrenCost.value!!
            gSTCost = sharedViewModel.gSTCost.value!!
            grandTotal = sharedViewModel.grandTotal.value!!
            advanceCost = sharedViewModel.advanceCost.value!!
            totalAfterCoupon = sharedViewModel.totalafterCoupon.value!!
            arrivalDate = sharedViewModel.arrivalDate.value!!
            arrivalCity = sharedViewModel.arrivalCity.value!!
            customersList = sharedViewModel.customersList

            leadTraveller = sharedViewModel.leadTraveller.value!!
            iDType = sharedViewModel.iDType.value!!
            iDNumber = sharedViewModel.iDNumber.value!!
            leadEmail = sharedViewModel.leadEmail.value!!
            fullAddress = sharedViewModel.fullAddress.value!!
            leadContact = sharedViewModel.leadContact.value!!
            additionalRequirement = sharedViewModel.additionalRequirement.value!!

            packageName = sharedViewModel.packageName.value!!
            packageDays = sharedViewModel.packageDays.value!!
            selectedDate = sharedViewModel.date.value!!
            totalNumberOfPersons = numberOfAdults + numberOfChildren
            userPayCost = sharedViewModel.userPayCost.value!!
            amountDueCost = sharedViewModel.amountDueCost.value!!
            paymentType = sharedViewModel.paymentType.value!!

            incompleteBooking = IncompleteBooking(
                com.google.firebase.Timestamp(
                    Date(
                        Timestamp(
                            System.currentTimeMillis()
                        ).time
                    )
                ),
                numberOfAdults,
                numberOfChildren,
                packageAdultCost,
                packageChildCost,
                gSTCost,
                grandTotal,
                advanceCost,
                totalAfterCoupon,
                arrivalDate,
                arrivalCity,
                customersList,
                leadTraveller,
                iDType,
                iDNumber,
                leadEmail,
                fullAddress,
                leadContact,
                additionalRequirement,
                packageName,
                packageDays,
                "$selectedDateFormatted",
                totalNumberOfPersons,
                userPayCost?.roundToInt(),
                amountDueCost,
                paymentType,
                currentUser?.uid
            )

            incompletePaymentError()

        } catch (e: Exception) {

            Log.d(TAG, "OnPaymentError: ${e.message.toString()}")
            e.printStackTrace()
        }
        paymentSuccess = false
    }

    private fun completePaymentSuccess() {

        db.collection("completeBooking").add(completedBooking).addOnSuccessListener { document ->

            Log.d(TAG, "Document written with id ${document.id}")

        }.addOnFailureListener {
            Log.d(TAG, "Error in adding document: ${it.message.toString()}")
        }

        incompleteBookingCollection.whereEqualTo("userID", currentUser?.uid)
            .whereEqualTo("packageName", packageName).get().addOnSuccessListener { documents ->

                for (document in documents) {

                    document.reference.delete()

                    Log.d(TAG, "Document Deleted with id ${document.id}")
                }
            }.addOnFailureListener {
                Log.d(TAG, "Error in Deleting document: ${it.message.toString()}")
            }

    }

    private fun incompletePaymentError() {

        incompleteBookingCollection.add(incompleteBooking).addOnSuccessListener { document ->
            Log.d(TAG, "Document written in Incomplete Booking with id ${document.id}")
        }.addOnFailureListener {
            Log.d(TAG, "Error in adding document: ${it.message.toString()}")
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            val vibratorManager =
                getSystemService(Context.VIBRATOR_MANAGER_SERVICE) as VibratorManager
            val vibrator = vibratorManager.defaultVibrator

            vibrator.vibrate(VibrationEffect.createOneShot(100, VibrationEffect.DEFAULT_AMPLITUDE))
        }

    }

    override fun onResume() {
        super.onResume()

        if (paymentSuccess == true) {
            navController.navigate(R.id.bookingSuccessFragment)

            paymentSuccess = null
        } else if (paymentSuccess == false) {
            navController.navigate(
                R.id.bookingErrorFragment
            )
            paymentSuccess = null
        }

    }

    companion object {
        const val BASE_URL = "https://api.razorpay.com/v1/orders/"
        const val TAG = "BookingActivityTAG"
    }

}
