package `in`.tourism.pilgrimagetour.view

import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.RatingBar
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.firebase.ui.auth.AuthUI
import com.firebase.ui.auth.FirebaseAuthUIActivityResultContract
import com.firebase.ui.auth.data.model.FirebaseAuthUIAuthenticationResult
import com.google.android.gms.common.api.ApiException
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.tabs.TabLayout
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import com.google.firebase.ktx.Firebase
import `in`.tourism.pilgrimagetour.*
import `in`.tourism.pilgrimagetour.adapter.*
import `in`.tourism.pilgrimagetour.databinding.ActivityHotelDetailsBinding
import java.sql.Timestamp
import java.text.SimpleDateFormat
import java.time.Instant
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.time.format.FormatStyle
import java.util.*
import kotlin.collections.ArrayList

private const val sliderTimerDuration: Long = 5 * 1000

class HotelDetailsActivity : AppCompatActivity(), TabLayout.OnTabSelectedListener {

    private lateinit var binding: ActivityHotelDetailsBinding
    private lateinit var handler: Handler
    private lateinit var dialog: Dialog
    private val numberFormat = java.text.NumberFormat.getCurrencyInstance(Locale("en", "in"))
    private var hotel: HotelDetails? = null
    private var hotelName: String = ""
    private var overview: String = ""
    private var destination: String = ""
    private var mrp: Double = 0.0
    private var discountedPrice: Double = 0.0
    private var hotelImages: List<String>? = null
    private var hotelFacilities: List<HotelFacility>? = null
    private lateinit var userName: EditText
    private lateinit var eTCity: EditText
    private lateinit var eTName: EditText
    private lateinit var eTReview: EditText
    private lateinit var ratingBar: RatingBar
    private lateinit var buttonSubmit: Button
    private var packagesList: ArrayList<TravelPac?>? = null
    private lateinit var filteredList: ArrayList<TravelPac?>
    private var placesToVisitList: ArrayList<PlacesToVisit>? = null
    private val signInLauncher =
        registerForActivityResult(FirebaseAuthUIActivityResultContract()) { res ->
            this.onSignInResult(res)
        }

    private val signInLauncher2 =
        registerForActivityResult(FirebaseAuthUIActivityResultContract()) { res ->
            this.onSignInResult2(res)
        }

    private lateinit var placesCollection: CollectionReference
    private lateinit var reviewCollection: CollectionReference
    private var currentUser: FirebaseUser? = null
    private val sdf = SimpleDateFormat("dd/M/yyyy      hh:mm a")
    private val currentDate: String = sdf.format(Date())
    private val db = FirebaseFirestore.getInstance()
    private val userCollection = db.collection("user")
    private var long = 0
    private var green = 0
    private lateinit var queryUser: Query
    private var queryHotel: Query? = null
    private lateinit var dialogTVHotelName: TextView
    private val dateFormatter = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.SHORT)
    private val formattedZone = dateFormatter.withZone(ZoneId.systemDefault())
    private lateinit var queryHotelName: Query
    private lateinit var orderedReviews: Query
    private lateinit var reviewAdapter: AdapterHotelReview

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHotelDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        placesCollection = FirebaseFirestore.getInstance().collection("placesToVist")

        reviewCollection = FirebaseFirestore.getInstance().collection("reviewHotel")

        queryHotelName = reviewCollection.whereEqualTo("hotelName", hotelName)

        orderedReviews = queryHotelName.orderBy(
            "timestamp", Query.Direction.DESCENDING
        )
        queryUser = reviewCollection.whereEqualTo("id", currentUser?.uid)
        queryHotel = queryUser.whereEqualTo("hotelName", hotelName)

        green = ContextCompat.getColor(
            this, R.color.green_whatsapp
        )
        long = Snackbar.LENGTH_LONG

        currentUser = Firebase.auth.currentUser

        numberFormat.maximumFractionDigits = 0

        hotel = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {

            intent.getParcelableExtra("hotel", HotelDetails::class.java)
        } else {
            intent.getParcelableExtra("hotel")
        }


        hotel?.let { hotelDetails ->
            hotelName = hotelDetails.hotelName

            hotelImages = hotelDetails.hotelImages
            overview = hotelDetails.textOverview.toString()
            destination = hotelDetails.textDestination
            mrp = hotelDetails.mRP!!.toDouble()
            discountedPrice = hotelDetails.discounted!!.toDouble()
            hotelFacilities = hotelDetails.hotelFacilities

            updateViews()
        }

        binding.tL.addOnTabSelectedListener(this)

        binding.tVReadMore.setOnClickListener {
            binding.tVReadMore.visibility = View.INVISIBLE
            binding.tVReadLess.visibility = View.VISIBLE
            binding.tVOverviewText.maxLines = Int.MAX_VALUE
        }

        binding.tVReadLess.setOnClickListener {
            binding.tVReadMore.visibility = View.VISIBLE
            binding.tVReadLess.visibility = View.INVISIBLE
            binding.tVOverviewText.maxLines = 3
        }

        placesToVisitList = ArrayList()
        placesCollection.whereEqualTo("destination", destination).addSnapshotListener { value, _ ->

            if (value != null) {
                val documentList = value.documents
                Log.d("PlacesToVisit", "onViewCreated: ${documentList.size}")

                if (documentList.isNotEmpty()) {
                    for (document in documentList) {

                        try {
                            (placesToVisitList as ArrayList<PlacesToVisit?>).add(
                                document.toObject(
                                    PlacesToVisit::class.java
                                )
                            )
                        } catch (e: java.lang.Exception) {
                            Log.d("PlacesToVisitEx", "onViewCreated: ${e.message}")
                        }
                        Log.d("PlacesToVisit", placesToVisitList.toString())
                    }
                    if (placesToVisitList?.isNotEmpty() == true) {
                        val placesToVisit = placesToVisitList!![0]
                        binding.rVPlaces.adapter = PlacesToVisitAdapter(
                            this, placesToVisit.places
                        )
                    } else {
                        binding.tVSightseeing.visibility = View.GONE
                    }
                }
            }
        }


        packagesList = ArrayList()
        filteredList = ArrayList()
        packagesList = Contact.packagesList
        packagesList?.filter {
            it?.name?.contains(destination, true) == true
        }?.forEach { filteredList.add(it) }

        var packageAdapter = FBPackageAdapter(filteredList, onCardClickListener)

        binding.rVPackages.adapter = packageAdapter
        Log.d("PackageinHotels", packagesList!!.size.toString())

        binding.mT.setOnClickListener {
            onBackPressed()
        }

        dialog = Dialog(this)
        dialog.setContentView(R.layout.dialog_review)
        dialog.setCancelable(false)

        dialogTVHotelName = dialog.findViewById(R.id.tVPackageDialog)
        dialogTVHotelName.text = binding.tVName.text.toString()

        eTName = dialog.findViewById(R.id.eTName)
        eTCity = dialog.findViewById(R.id.eTCity)
        eTReview = dialog.findViewById(R.id.eTReview)
        ratingBar = dialog.findViewById(R.id.rB)

        dialog.findViewById<Button>(R.id.bTCancel).setOnClickListener {

            dialog.cancel()

            val snackBar = Snackbar.make(binding.root, "Review cancelled", Snackbar.LENGTH_LONG)
            val snackbarView = snackBar.view
            val redTheme = ContextCompat.getColor(this, R.color.red_theme)

            snackbarView.setBackgroundColor(redTheme)
            snackBar.show()
        }

        dialog.findViewById<Button>(R.id.bTSubmit).setOnClickListener {


            if (eTName.text.toString().trim() == "") eTName.error = "Please Enter Your name"
            else if (eTCity.text.toString().trim() == "") eTCity.error = "Please Enter Your City"
            else if (eTReview.text.toString().trim() == "") eTReview.error =
                "Please Enter Your Review"
            else {
                val review = ReviewHotel(
                    currentUser?.email,
                    currentUser?.uid,
                    eTReview.text.toString().trim(),
                    ratingBar.rating,
                    eTName.text.toString().trim(),
                    currentUser?.photoUrl.toString(),
                    eTCity.text.toString().trim(),
                    hotelName,
                    currentDate,
                    com.google.firebase.Timestamp(
                        Date(
                            Timestamp(
                                System.currentTimeMillis()
                            ).time
                        )
                    )
                )

                addReview(review)
                dialog.dismiss()
            }

        }
        /* reviewCollection.whereEqualTo("hotelName", hotelName)
             .orderBy("timestamp", Query.Direction.DESCENDING).get().addOnCompleteListener { task ->

                 Log.d("reviewHotel", "Not showing")
                 if (task.isSuccessful) {
                     if (!task.result.isEmpty) {
                         for (doc in task.result.documents) {
                             if (doc["hotelName"].toString() == hotelName) {

                                 var reviewHotelAdapter = AdapterHotelReview(task.result.documents)
                                 binding.rVReviewsHotels.visibility = View.VISIBLE
                                 binding.rVReviewsHotels.adapter = reviewHotelAdapter

                             } else {
                                 binding.rVReviewsHotels.visibility = View.GONE
                             }
                         }
                     } else {
                         Log.d("review", "onCreate: isEmpty")
                     }
                 } else {
                     Log.d("error", "Reviews")
                 }
             }*/
        orderedReviews.get().addOnSuccessListener {
            for (doc in it.documents) {

                if (doc["hotelName"] == hotelName) {

                    reviewAdapter = AdapterHotelReview(
                        it.documents, onReviewClickListener
                    )

                    binding.rVReviewsHotels.adapter = reviewAdapter
                } else {
                    binding.rVReviewsHotels.visibility = View.GONE
                }
            }
        }.addOnFailureListener {
            Log.d(
                "TAG", "onCreate: ${it.message}"
            )
        }

        binding.eFABWriteReview.setOnClickListener {

            currentUser = Firebase.auth.currentUser

            if (currentUser != null) {
                /* eTName.setText(currentUser?.displayName)
                 reviewCollection.whereEqualTo("id", currentUser?.uid)
                     .whereEqualTo("hotelName", hotelName).get().addOnCompleteListener { task ->

                         if (task.isSuccessful) {
                             if (!task.result.isEmpty) {
                                 for (doc in task.result.documents) {
                                     if (doc["hotelName"].toString().trim() == hotelName) {
                                         Snackbar.make(
                                             binding.root,
                                             "You've already reviewed.",
                                             Snackbar.LENGTH_LONG
                                         ).show()

                                         val alreadyReviewAdapter = AdapterHotelReview(
                                             task.result.documents
                                         )

                                         binding.rVReviewsHotels.visibility = View.VISIBLE
                                         binding.rVReviewsHotels.adapter = alreadyReviewAdapter
                                     } else {
                                         dialog.show()
                                         binding.rVReviewsHotels.visibility = View.GONE
                                     }
                                 }
                             } else {
                                 dialog.show()
                             }
                         } else {
                             Log.d("review", "Error getting documents: ", task.exception)
                         }
                     }*/
                queryHotel!!.get().addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        if (!task.result.isEmpty) {
                            for (doc in task.result.documents) {

                                if (doc["hotelName"].toString() == hotelName) {

                                    Snackbar.make(
                                        binding.root, "You've already reviewed.", long
                                    ).show()

                                    val alreadyReviewedAdapter = AdapterHotelReview(
                                        task.result.documents, onReviewClickListener
                                    )

                                    binding.rVReviewsHotels.visibility = View.VISIBLE
                                    binding.rVReviewsHotels.adapter = alreadyReviewedAdapter

                                    scrollToPoint(binding.fLWriteReview.top)
                                } else {
                                    dialog.show()

//                                    binding.rVReview.visibility = View.GONE
                                }
                            }
                        } else {
                            dialog.show()

                        }
                    } else {
                        Log.d("review", "Error getting documents: ", task.exception)
                    }
                }

            } else {

                val msg = "You must be Logged in to Write a Review"
                showLogoutDialog(this@HotelDetailsActivity, msg)

            }
        }
        binding.buttonContinueBooking.setOnClickListener {
            if (currentUser != null) {

                val intent = Intent(this, ActivityHotelBooking::class.java)
                intent.putExtra("hotelName", hotelName)
                Log.d("hotelName", hotelName)
                startActivity(intent)

            } else {
                createSignInIntent()
            }
        }
    }

    private fun addReview(reviewHotel: ReviewHotel) {

        reviewCollection.add(reviewHotel).addOnSuccessListener {
            Log.d("review", "Review added: ${it.id}")
            val snackBar = Snackbar.make(binding.root, "Review Submitted", Snackbar.LENGTH_LONG)
            val snackbarView = snackBar.view

            snackbarView.setBackgroundColor(
                ContextCompat.getColor(
                    this, R.color.green_whatsapp
                )
            )
            snackBar.show()
            binding.rVReviewsHotels.visibility = View.VISIBLE
            /* reviewCollection.whereEqualTo("hotelName", hotelName)
                 .orderBy("timestamp", Query.Direction.DESCENDING)
                 .addSnapshotListener { value, _ ->
                     if (value != null) {
                         val documentList = value.documents
                         var reviewAdapter = AdapterHotelReview(documentList)
                         binding.rVReviewsHotels.adapter = reviewAdapter
                     }
                 }*/
            orderedReviews.get().addOnSuccessListener {
                for (doc in it.documents) {

                    if (doc["hotelName"] == hotelName) {

                        reviewAdapter = AdapterHotelReview(
                            it.documents, onReviewClickListener
                        )

                        binding.rVReviewsHotels.adapter = reviewAdapter
                    } else {
                        binding.rVReviewsHotels.visibility = View.GONE
                    }
                }
            }.addOnFailureListener {
                Log.d(
                    "TAG", "onCreate: ${it.message}"
                )
            }
        }.addOnFailureListener {
            Log.d("review", "Error adding Document: ${it.message}")
        }
    }

    private fun showLogoutDialog(context: Context, msg: String) {

        val builder = AlertDialog.Builder(context)
        builder.setTitle("Log In")
        builder.setMessage(msg).setPositiveButton("Proceed") { _, _ ->
            createSignInIntent()
        }.setNegativeButton("Cancel") { dialog, _ ->
            // User cancelled the Log Out action, dismiss the dialog
            dialog.cancel()
        }
        builder.create().show()
    }

    private fun createSignInIntent() {
        val providers = arrayListOf(
            AuthUI.IdpConfig.EmailBuilder().build(), AuthUI.IdpConfig.GoogleBuilder().build()
        )

        val signInintent =
            AuthUI.getInstance().createSignInIntentBuilder().setAvailableProviders(providers)
                .build()
        signInLauncher.launch(signInintent)
    }

    private fun onSignInResult(result: FirebaseAuthUIAuthenticationResult?) {

        if (result?.resultCode == RESULT_OK) {
            val currentUser = FirebaseAuth.getInstance().currentUser
            try {
                /* val user = User(
                     currentUser?.email,
                     currentUser?.uid,
                     currentUser?.displayName,
                     currentUser?.photoUrl.toString()
                 )

                 FirebaseFirestore.getInstance().collection("user").add(user).addOnSuccessListener {
                         Log.d(
                             "User", "DocumentSnapshot written with ID: " + it?.id
                         )
                         val snackBar = Snackbar.make(
                             binding.root, "Signed in successfully", Snackbar.LENGTH_LONG
                         )
                         val snackbarView = snackBar.view
                         snackbarView.setBackgroundColor(
                             ContextCompat.getColor(
                                 this@HotelDetailsActivity, R.color.green_whatsapp
                             )
                         )
                         snackBar.show()

                     }.addOnFailureListener {
                         Log.d(
                             "User", "Error in adding user" + it.message
                         )
                     }*/
                val userCollection = db.collection("user")
                val queryUser = userCollection.whereEqualTo("id", currentUser?.uid)
                val sig = "signed in successfully"

                queryUser.get().addOnCompleteListener {
                    if (it.isSuccessful) {
                        if (!it.result.isEmpty) {

                            val user = User(
                                currentUser?.email,
                                currentUser?.uid,
                                currentUser?.displayName,
                                currentUser?.photoUrl.toString(),
                                timestamp = com.google.firebase.Timestamp(
                                    Date(
                                        Timestamp(
                                            System.currentTimeMillis()
                                        ).time
                                    )
                                )
                            )

                            userCollection.document(it.result.documents[0].id).set(user)
                                .addOnSuccessListener {
                                    Log.d(
                                        "user", "onSignInResult: Updated"
                                    )
                                    val snackBar = Snackbar.make(
                                        binding.root, sig, Snackbar.LENGTH_LONG
                                    )
                                    val snackbarView = snackBar.view

                                    snackbarView.setBackgroundColor(
                                        ContextCompat.getColor(
                                            this, R.color.green_whatsapp
                                        )
                                    )
                                    snackBar.show()

                                    dialog.findViewById<TextView>(R.id.tVPackageName).text =
                                        binding.tVName.text.toString()

                                    dialog.findViewById<EditText>(R.id.eTName)
                                        .setText(currentUser!!.displayName)

                                    queryHotel?.get()?.addOnCompleteListener { task ->
                                        if (task.isSuccessful) {
                                            if (!task.result.isEmpty) {
                                                for (doc in task.result.documents) {

                                                    if (doc["hotelName"].toString() == hotelName) {

                                                        Snackbar.make(
                                                            binding.root,
                                                            "You've already reviewed",
                                                            long
                                                        ).show()

                                                        val alreadyReviewedAdapter =
                                                            AdapterHotelReview(
                                                                task.result.documents,
                                                                onReviewClickListener
                                                            )
                                                        binding.rVReviewsHotels.visibility =
                                                            View.VISIBLE
                                                        binding.rVReviewsHotels.adapter =
                                                            alreadyReviewedAdapter

                                                        scrollToPoint(binding.fLWriteReview.top)
                                                    } else {
                                                        dialog.show()
                                                    }
                                                }
                                            } else {
                                                dialog.show()

                                            }
                                        } else {
                                            Log.d(
                                                "review",
                                                "Error getting documents: ",
                                                task.exception
                                            )
                                        }
                                    }
                                }.addOnFailureListener {
                                    Log.d("user", "onSignInResult: Failed")
                                }
                            Log.d("user", "user: Existing")
                        } else {
                            val user = User(
                                currentUser?.email,
                                currentUser?.uid,
                                currentUser?.displayName,
                                currentUser?.photoUrl.toString(),
                                timestamp = com.google.firebase.Timestamp(
                                    Date(
                                        Timestamp(
                                            System.currentTimeMillis()
                                        ).time
                                    )
                                )
                            )
                            userCollection.add(user).addOnSuccessListener {

                                val snackBar = Snackbar.make(
                                    binding.root, sig, Snackbar.LENGTH_LONG
                                )
                                val snackbarView = snackBar.view

                                snackbarView.setBackgroundColor(
                                    ContextCompat.getColor(
                                        this, R.color.green_whatsapp
                                    )
                                )
                                snackBar.show()

                                dialog.findViewById<TextView>(R.id.tVPackageName).text =
                                    binding.tVName.text.toString()

                                dialog.findViewById<EditText>(R.id.eTName)
                                    .setText(currentUser!!.displayName)

                                queryHotel?.get()?.addOnCompleteListener { task ->
                                    if (task.isSuccessful) {
                                        if (!task.result.isEmpty) {
                                            for (doc in task.result.documents) {

                                                if (doc["hotelName"].toString() == hotelName) {

                                                    Snackbar.make(
                                                        binding.root,
                                                        "You've already reviewed",
                                                        long
                                                    ).show()

                                                    val alreadyReviewedAdapter = AdapterHotelReview(
                                                        task.result.documents, onReviewClickListener
                                                    )
                                                    binding.rVReviewsHotels.visibility =
                                                        View.VISIBLE
                                                    binding.rVReviewsHotels.adapter =
                                                        alreadyReviewedAdapter

                                                    scrollToPoint(binding.fLWriteReview.top)
                                                } else {
                                                    dialog.show()
                                                }
                                            }
                                        } else {
                                            dialog.show()

                                        }
                                    } else {
                                        Log.d("review", "Error getting documents: ", task.exception)
                                    }
                                }
                            }.addOnFailureListener { e ->
                                Log.w(
                                    "onFailure", "Error adding document", e
                                )
                            }
                        }
                    }
                }
            } catch (e: ApiException) {
                Log.d(
                    "SignIn", "Can't Sign in" + e.message
                )
            }
        }
    }

    private val onCardClickListener = object : OnCardClickListener2 {

        override fun onClick(detailed: Boolean?, travelPac: TravelPac?) {

//            docId = id

            val detailsIntent =
                Intent(this@HotelDetailsActivity, PackageDetailsActivity::class.java)

//            detailsIntent.putExtra("detailsKey", docId)
            detailsIntent.putExtra("travelPac", travelPac)

            if (detailed == false || travelPac?.daysList == null) {
                var bottomSheetEnquiry = BottomSheetFragment(
                    packageNAme = travelPac?.name.toString(),
                    enquiryFragment = "hotelDetailsActivity"
                )
                bottomSheetEnquiry.show(supportFragmentManager, bottomSheetEnquiry.tag)

            } else startActivity(detailsIntent)
        }
    }
    private val onReviewClickListener = object : OnCardClickListener {

        override fun onClick(detailed: Boolean, id: String) {

            try {

                queryHotel?.get()?.addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        if (!task.result.isEmpty) {

                            for (doc in task.result.documents) {

                                if (doc["hotelName"].toString() == hotelName) {

                                    dialogTVHotelName.text = hotelName

                                    userName.setText(doc.get("userName").toString())

                                    eTCity.setText(doc.get("textCity").toString())
                                    eTReview.setText(doc.get("textReview").toString())

                                    val alreadyRated =
                                        (doc.get("rating").toString() + "f").toFloat()

                                    ratingBar.rating = alreadyRated

                                    buttonSubmit.setOnClickListener {
                                        val isReviewChanged =
                                            eTReview.text.toString().trim() != doc.get("textReview")
                                                .toString()

                                        val updatedReview = ReviewHotel()

                                        updatedReview.timestamp = com.google.firebase.Timestamp(
                                            Date(
                                                Timestamp(
                                                    System.currentTimeMillis()
                                                ).time
                                            )
                                        )
                                        updatedReview.textReview = eTReview.text.toString().trim()
                                        updatedReview.email = currentUser?.email
                                        updatedReview.textCity = eTCity.text.toString().trim()
                                        updatedReview.textTime = formattedZone.format(Instant.now())
                                        updatedReview.hotelName = hotelName
                                        updatedReview.id = currentUser?.uid
                                        updatedReview.rating = ratingBar.rating
                                        updatedReview.userName = userName.text.toString()

                                        val reviewDoc = reviewCollection.document(id)

                                        reviewDoc.set(updatedReview).addOnSuccessListener {

                                            val snackBar = Snackbar.make(
                                                binding.root, "Review Updated", long
                                            )
                                            val snackbarView = snackBar.view

                                            snackbarView.setBackgroundColor(
                                                green
                                            )
                                            snackBar.show()

                                            dialog.dismiss()

                                            binding.rVReviewsHotels.visibility = View.VISIBLE
                                            binding.eFABWriteReview.visibility = View.GONE

                                            orderedReviews.addSnapshotListener { value, _ ->
                                                if (value != null) {
                                                    val documentList = value.documents

                                                    reviewAdapter = AdapterHotelReview(
                                                        documentList, null
                                                    )

                                                    binding.rVReviewsHotels.adapter = reviewAdapter
                                                }
                                            }
                                        }.addOnFailureListener { e ->
                                            Log.w(
                                                "onFailure", "Error updating document", e
                                            )
                                        }
                                    }
                                    dialog.show()
                                }
                            }
                        } else {
                            Log.d("TAG", "No such document")
                        }
                    } else {
                        Log.d("TAG", "get failed with ", task.exception)
                    }
                }
            } catch (e: java.lang.Exception) {
                Log.d("review", "onClick: ${e.message}")
            }
        }
    }

    private fun onSignInResult2(result: FirebaseAuthUIAuthenticationResult?) {

        if (result?.resultCode == RESULT_OK) {
            val currentUser = FirebaseAuth.getInstance().currentUser
            try {
                /* val user = User(
                     currentUser?.email,
                     currentUser?.uid,
                     currentUser?.displayName,
                     currentUser?.photoUrl.toString()
                 )
                 FirebaseFirestore.getInstance().collection("user").add(user).addOnSuccessListener {
                     Log.d(
                         "User", "DocumentSnapshot written with ID: " + it?.id
                     )
                     val snackBar = Snackbar.make(
                         binding.root, "Signed in successfully", Snackbar.LENGTH_LONG
                     )
                     val snackbarView = snackBar.view

                     snackbarView.setBackgroundColor(
                         ContextCompat.getColor(
                             this@HotelDetailsActivity, R.color.green_whatsapp
                         )
                     )
                     snackBar.show()

                     val intent = Intent(
                         this, ActivityHotelBooking::class.java
                     )
                     intent.putExtra("hotelName", hotelName)

                     startActivity(
                         intent
                     )

                 }.addOnFailureListener {
                     Log.d(
                         "User", "Error in adding user" + it.message
                     )
                 }*/
                val user = User(
                    currentUser?.email,
                    currentUser?.uid,
                    currentUser?.displayName,
                    currentUser?.photoUrl.toString(),
                    timestamp = com.google.firebase.Timestamp(
                        Date(
                            Timestamp(
                                System.currentTimeMillis()
                            ).time
                        )
                    )
                )
                userCollection.add(user).addOnSuccessListener { documentReference ->

                    val snackBar = Snackbar.make(binding.root, "signed in successfully", long)
                    val snackbarView = snackBar.view

                    snackbarView.setBackgroundColor(
                        green
                    )
                    snackBar.show()


                }.addOnFailureListener { e ->
                    Log.w(
                        "onFailure", "Error adding document", e
                    )
                }

                val userCollection = db.collection("user")
                val queryUser = userCollection.whereEqualTo("id", currentUser?.uid)
                val sig = "signed in successfully"

                queryUser.get().addOnCompleteListener {
                    if (it.isSuccessful) {
                        if (!it.result.isEmpty) {

                            val user = User(
                                currentUser?.email,
                                currentUser?.uid,
                                currentUser?.displayName,
                                currentUser?.photoUrl.toString(),
                                timestamp = com.google.firebase.Timestamp(
                                    Date(
                                        Timestamp(
                                            System.currentTimeMillis()
                                        ).time
                                    )
                                )
                            )

                            userCollection.document(it.result.documents[0].id).set(user)
                                .addOnSuccessListener {
                                    Log.d(
                                        "user", "onSignInResult: Updated"
                                    )
                                    val snackBar = Snackbar.make(
                                        binding.root, sig, Snackbar.LENGTH_LONG
                                    )
                                    val snackbarView = snackBar.view

                                    snackbarView.setBackgroundColor(
                                        ContextCompat.getColor(
                                            this, R.color.green_whatsapp
                                        )
                                    )
                                    snackBar.show()

                                    val intent = Intent(
                                        this, ActivityHotelBooking::class.java
                                    )
                                    intent.putExtra("detailsKey", hotelName)

                                    startActivity(
                                        intent
                                    )
                                }.addOnFailureListener {
                                    Log.d("user", "onSignInResult: Failed")
                                }
                            Log.d("user", "user: Existing")
                        } else {
                            val user = User(
                                currentUser?.email,
                                currentUser?.uid,
                                currentUser?.displayName,
                                currentUser?.photoUrl.toString(),
                                timestamp = com.google.firebase.Timestamp(
                                    Date(
                                        Timestamp(
                                            System.currentTimeMillis()
                                        ).time
                                    )
                                )
                            )
                            userCollection.add(user).addOnSuccessListener {

                                val snackBar = Snackbar.make(
                                    binding.root, sig, Snackbar.LENGTH_LONG
                                )
                                val snackbarView = snackBar.view

                                snackbarView.setBackgroundColor(
                                    ContextCompat.getColor(
                                        this, R.color.green_whatsapp
                                    )
                                )
                                snackBar.show()

                                val intent = Intent(
                                    this, ActivityHotelBooking::class.java
                                )
                                intent.putExtra("detailsKey", hotelName)

                                startActivity(
                                    intent
                                )
                            }.addOnFailureListener { e ->
                                Log.w(
                                    "onFailure", "Error adding document", e
                                )
                            }
                        }
                    }
                }

            } catch (e: ApiException) {
                Log.d(
                    "SignIn", "Can't Sign in" + e.message
                )
            }
        }
    }

    private fun updateViews() {

        val adapterHotelSlider = AdapterHotelSlider(this, hotelImages!!)

        binding.vPSlider.adapter = adapterHotelSlider

        handler = Handler()

        Timer().schedule(
            object : TimerTask() {
                override fun run() {

                    handler.post {

                        if (binding.vPSlider.currentItem < hotelImages!!.size - 1) {

                            binding.vPSlider.currentItem = binding.vPSlider.currentItem + 1
                        } else {
                            binding.vPSlider.currentItem = 0
                        }
                    }
                }
            }, sliderTimerDuration, sliderTimerDuration
        )

        binding.tVPackages.text = "Budget Friendly $destination Packages"
        binding.tVName.text = hotelName
        binding.tVOverviewText.text = overview

        val facilityAdapter = AdapterHotelFacility(hotelFacilities!!)
        binding.rVFacilities.adapter = facilityAdapter

        binding.mCVtVMrp.text = numberFormat.format(mrp)
        binding.mCVtVDiscounted.text = numberFormat.format(discountedPrice)

        binding.iVLoading.visibility = View.GONE
        binding.pBLoading.visibility = View.GONE
    }

    private fun scrollToPoint(point: Int) {
        Handler().post {

            binding.sVFragmentPackageDetails.scrollTo(0, point)
        }
    }

    override fun onTabSelected(tab: TabLayout.Tab?) {

        when (tab?.text) {

            "Overview" -> {
                Handler().post {
                    binding.aBL.setExpanded(false)
                    binding.sVFragmentPackageDetails.scrollTo(0, binding.tVOverview.top)
                }
            }

            "Facilities" -> {
                Handler().post {
                    Log.d("Facilities", "Selected ")
                    binding.aBL.setExpanded(false)
                    binding.sVFragmentPackageDetails.scrollTo(0, binding.tVFacilities.top)
                }
            }

            "Sightseeing" -> {
                Handler().post {
                    binding.aBL.setExpanded(false)
                    binding.sVFragmentPackageDetails.scrollTo(0, binding.tVSightseeing.top)
                }
            }

            "Packages" -> {
                Handler().post {
                    binding.aBL.setExpanded(false)
                    binding.sVFragmentPackageDetails.scrollTo(0, binding.tVPackages.top)
                }
            }

            "Reviews" -> Handler().post {
                binding.aBL.setExpanded(false)

                binding.sVFragmentPackageDetails.scrollTo(0, binding.fLWriteReview.top)
            }

            "Hotel Enquiry" -> {
                val modalBottomSheet = HotelBottomSheet(hotelName)
                modalBottomSheet.show(supportFragmentManager, modalBottomSheet.tag)

            }
        }
    }

    override fun onTabUnselected(tab: TabLayout.Tab?) {

    }

    override fun onTabReselected(tab: TabLayout.Tab?) {
        when (tab?.text) {

            "Overview" -> {
                Handler().post {
                    binding.aBL.setExpanded(false)
                    binding.sVFragmentPackageDetails.scrollTo(0, binding.tVOverview.top)
                }
            }

            "Facilities" -> {
                Handler().post {
                    binding.aBL.setExpanded(false)
                    binding.sVFragmentPackageDetails.scrollTo(0, binding.tVFacilities.top)
                }
            }

            "Sightseeing" -> {
                Handler().post {
                    binding.aBL.setExpanded(false)
                    binding.sVFragmentPackageDetails.scrollTo(0, binding.tVSightseeing.top)
                }
            }

            "Packages" -> {
                Handler().post {
                    binding.aBL.setExpanded(false)
                    binding.sVFragmentPackageDetails.scrollTo(0, binding.tVPackages.top)
                }
            }

            "Reviews" -> Handler().post {
                binding.aBL.setExpanded(false)

                binding.sVFragmentPackageDetails.scrollTo(0, binding.fLWriteReview.top)
            }

            "Hotel Enquiry" -> {
                val modalBottomSheet = HotelBottomSheet(hotelName)
                modalBottomSheet.show(supportFragmentManager, modalBottomSheet.tag)

            }
        }
    }
}
