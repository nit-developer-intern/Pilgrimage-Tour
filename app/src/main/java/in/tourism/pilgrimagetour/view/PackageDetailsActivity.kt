package `in`.tourism.pilgrimagetour.view

import android.app.Dialog
import `in`.tourism.pilgrimagetour.databinding.ActivityPackageDetailsBinding
import android.content.Context
import android.content.Intent
import android.graphics.Paint
import android.net.Uri
import android.os.*
import android.text.TextUtils
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
import com.squareup.picasso.Picasso
import `in`.tourism.pilgrimagetour.*
import `in`.tourism.pilgrimagetour.ResourceFactory.packageCollection
import `in`.tourism.pilgrimagetour.adapter.*
import java.sql.Timestamp
import java.text.NumberFormat
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class PackageDetailsActivity : AppCompatActivity(), TabLayout.OnTabSelectedListener {

    private lateinit var binding: ActivityPackageDetailsBinding
    private var db = FirebaseFirestore.getInstance()
    private var currentUser: FirebaseUser? = null
    private lateinit var reviewCollection: CollectionReference
    private val signInLauncher =
        registerForActivityResult(FirebaseAuthUIActivityResultContract()) { res ->
            this.onSignInResult(res)
        }

    private var numberFormat = NumberFormat.getCurrencyInstance(Locale("en", "in"))
    private lateinit var daysList: ArrayList<Day>
    private var tourPackageName: String? = null
    private var packagedetails: TravelPac? = null
    private var docId: String? = null

    //adapters
    private lateinit var daysAdapter: DaysAdapter
    private lateinit var reviewAdapter: ReviewAdapter

    //package
    private var travelPac: TravelPac? = null
    private var viewType: Int? = null

    //reviewa
    private lateinit var dialog: Dialog
    private lateinit var ratingBar: RatingBar
    private lateinit var eTCity: EditText
    private lateinit var eTReview: EditText
    private lateinit var eTName: EditText
    private val sdf = SimpleDateFormat("dd/M/yyyy      hh:mm")
    private lateinit var excList: List<String>
    private lateinit var inclList: List<String>

    val currentDate: String = sdf.format(Date())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityPackageDetailsBinding.inflate(layoutInflater)

        setContentView(binding.root)

        numberFormat.maximumFractionDigits = 0

        currentUser = Firebase.auth.currentUser

        reviewCollection = db.collection("review")

        travelPac = intent.getParcelableExtra("travelPac")

        docId = intent.getStringExtra("detailsKey").toString()

        if (docId != null) {
            Log.d("docId", "onCreate: $docId")

            val query = packageCollection(db).whereEqualTo("name", docId)

            query.get().addOnSuccessListener { documents ->

                for (document in documents) {

                    packagedetails = document.toObject(TravelPac::class.java)

                    updateViews(packagedetails)

                    binding.buttonContinueBooking.setOnClickListener {

                        val bookingIntent = Intent(this, BookingActivity::class.java)

                        bookingIntent.putExtra("detailsKey", tourPackageName)

                        startActivity(bookingIntent)
                    }
                }

            }.addOnFailureListener {
                Log.w("TAG", "Error getting documents: " + it.message)
            }

            reviewCollection.whereEqualTo("textPackageName", binding.tVName.text.toString()).get()
                .addOnCompleteListener { task ->

                    val result = binding.tVName.text.toString()
                    Log.d("reviewBanner", result)
                    if (task.isSuccessful) {
                        if (!task.result.isEmpty) {
                            for (doc in task.result.documents) {
                                if (doc["textPackageName"].toString() == binding.tVName.text.toString()) {
                                    reviewAdapter =
                                        ReviewAdapter(task.result.documents, onCardClickListener)
                                    binding.rVReviews.visibility = View.VISIBLE
                                    binding.rVReviews.adapter = reviewAdapter
                                } else {
                                    binding.rVReviews.visibility = View.GONE
                                }
                            }
                        } else {
                            Log.d("review", "onCreate: isEmpty")
                        }
                    } else {
                        Log.d("error", "Reviews")
                    }
                }

        }

        updateViews(travelPac)

        binding.buttonContinueBooking.setOnClickListener {

            val bookingIntent = Intent(this, BookingActivity::class.java)

            bookingIntent.putExtra("detailsKey", tourPackageName)

            startActivity(bookingIntent)
        }
        //Enquiry
        binding.buttonEnquiry.setOnClickListener {
            val bottomSheetFragment = BottomSheetFragment(
                packageNAme = "${binding.tVName.text} - ${binding.tVDays.text}",
                enquiryFragment = "detailsActivity"
            )
            bottomSheetFragment.show(supportFragmentManager, bottomSheetFragment.tag)
        }

        //CallEnquiry
        binding.tVPhoneNumber.text = Contact.phoneNumber
        binding.mCVCallHelp.setOnClickListener {
            val intent = Intent(Intent.ACTION_DIAL)
            val uri = Uri.parse("tel:${Contact.phoneNumber}")
            intent.data = uri
            startActivity(intent)
        }

        if (currentUser == null) {
            binding.eFABWriteReview.visibility = View.VISIBLE
        } else {
            reviewCollection.whereEqualTo("id", currentUser!!.uid)
                .whereEqualTo("textPackageName", binding.tVName.text.toString()).get()
                .addOnCompleteListener { task ->

                    Log.d("review", "Not showing")
                    if (task.isSuccessful) {
                        if (!task.result.isEmpty) {
                            for (doc in task.result.documents) {
                                if (doc["textPackageName"].toString() == binding.tVName.text.toString()) {

                                    binding.eFABWriteReview.visibility = View.GONE

                                } else {
                                    binding.rVReviews.visibility = View.GONE
                                }
                            }
                        } else {
                            Log.d("review", "onCreate: isEmpty")
                        }
                    } else {
                        Log.d("error", "Reviews")
                    }
                }
        }

        dialog = Dialog(this@PackageDetailsActivity)
        dialog.setContentView(R.layout.dialog_review)
        dialog.setCancelable(false)
        dialog.findViewById<TextView>(R.id.tVPackageDialog).text = binding.tVName.toString()
        eTName = dialog.findViewById(R.id.eTName)
        eTCity = dialog.findViewById(R.id.eTCity)
        eTReview = dialog.findViewById(R.id.eTReview)
        ratingBar = dialog.findViewById(R.id.rB)

        dialog.findViewById<Button>(R.id.bTCancel).setOnClickListener {
            dialog.dismiss()
            val snackBar = Snackbar.make(binding.root, "Review cancelled", Snackbar.LENGTH_LONG)
            val snackbarView = snackBar.view

            snackbarView.setBackgroundColor(ContextCompat.getColor(this, R.color.red_theme))
            snackBar.show()

        }
        dialog.findViewById<Button>(R.id.bTSubmit).setOnClickListener {


            if (eTName.text.toString().trim() == "") eTName.error = "Please Enter Your name"
            else if (eTCity.text.toString().trim() == "") eTCity.error = "Please Enter Your City"
            else if (eTReview.text.toString().trim() == "") eTReview.error =
                "Please Enter Your Review"
            else {
                val review = Review(
                    currentUser?.email,
                    com.google.firebase.Timestamp(
                        Date(
                            Timestamp(
                                System.currentTimeMillis()
                            ).time
                        )
                    ),
                    currentUser?.uid,
                    eTReview.text.toString().trim(),
                    ratingBar.rating,
                    eTName.text.toString().trim(),
                    eTCity.text.toString().trim(),
                    binding.tVName.text.toString(),
                    currentDate
                )
                addReview(review)
                dialog.dismiss()
            }

        }

        reviewCollection.whereEqualTo("textPackageName", binding.tVName.text.toString())
            .orderBy("timestamp", Query.Direction.DESCENDING).get().addOnCompleteListener { task ->

                Log.d("review", "Not showing")
                if (task.isSuccessful) {
                    if (!task.result.isEmpty) {
                        for (doc in task.result.documents) {
                            if (doc["textPackageName"].toString() == binding.tVName.text.toString()) {

                                reviewAdapter =
                                    ReviewAdapter(task.result.documents, onCardClickListener)
                                binding.rVReviews.visibility = View.VISIBLE
                                binding.rVReviews.adapter = reviewAdapter

                            } else {
                                binding.rVReviews.visibility = View.GONE
                            }
                        }
                    } else {
                        Log.d("review", "onCreate: isEmpty")
                    }
                } else {
                    Log.d("error", "Reviews")
                }
            }

        binding.eFABWriteReview.setOnClickListener {
            currentUser = Firebase.auth.currentUser

            if (currentUser != null) {

                dialog.findViewById<TextView>(R.id.tVPackageDialog).text =
                    binding.tVName.text.toString()
                eTName.setText(currentUser?.displayName)

                reviewCollection.whereEqualTo("id", currentUser?.uid)
                    .whereEqualTo("textPackageName", binding.tVName.text.toString().trim()).get()
                    .addOnCompleteListener { task ->

                        if (task.isSuccessful) {
                            if (!task.result.isEmpty) {
                                for (doc in task.result.documents) {
                                    if (doc["textPackageName"].toString()
                                            .trim() == binding.tVName.text.toString().trim()
                                    ) {
                                        Snackbar.make(
                                            binding.root,
                                            "You've already reviewed.",
                                            Snackbar.LENGTH_LONG
                                        ).show()

                                        val alreadyReviewAdapter = ReviewAdapter(
                                            task.result.documents, onCardClickListener
                                        )

                                        binding.rVReviews.visibility = View.VISIBLE
                                        binding.rVReviews.adapter = alreadyReviewAdapter
                                    } else {
                                        dialog.show()
                                        binding.rVReviews.visibility = View.GONE
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
                showLogInDialog(this@PackageDetailsActivity)
            }
        }
        binding.rVReviews.visibility = View.VISIBLE

        val guidelines = travelPac?.guidelinesList?.let { GuidelinesAdapter(false, it) }
        val faq = travelPac?.fAQsList?.let { FAQsAdapter(false, it) }

    }

    private fun addReview(review: Review) {

        reviewCollection.add(review).addOnSuccessListener {
            Log.d("review", "Review added: ${it.id}")
            val snackBar = Snackbar.make(binding.root, "Review Submitted", Snackbar.LENGTH_LONG)
            val snackbarView = snackBar.view

            snackbarView.setBackgroundColor(
                ContextCompat.getColor(
                    this, R.color.green_whatsapp
                )
            )
            snackBar.show()
            binding.rVReviews.visibility = View.VISIBLE
            reviewCollection.whereEqualTo("textPackageName", binding.tVName.text.toString())
                .orderBy("timestamp", Query.Direction.DESCENDING)

                .addSnapshotListener { value, _ ->
                    if (value != null) {
                        val documentList = value.documents
                        reviewAdapter = ReviewAdapter(
                            documentList, onCardClickListener
                        )
                        binding.rVReviews.adapter = reviewAdapter
                    }
                }
        }.addOnFailureListener {
            Log.d("review", "Error adding Document: ${it.message}")
        }

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

    private fun updateViews(pac: TravelPac?) {

        pac?.let { travelPackage ->

            viewType = travelPackage.viewType

            try {

                Picasso.get().load(travelPackage.banner).placeholder(R.drawable.placeholder)
                    .into(binding.iVTop)
            } catch (e: java.lang.Exception) {
                Log.d("banner", "onCreate: ${e.message}")
            }

            excList = travelPackage.exclusionsList!!

            inclList = travelPackage.inclusionsList!!

            binding.tVDays.text = travelPackage.days
            binding.tVNights.text = travelPackage.nights

            tourPackageName = travelPackage.name

            binding.tVName.text = tourPackageName
            binding.mCVtVDiscounted.text = numberFormat.format(travelPackage.discountedPrice)
            binding.mCVtVMrp.text = numberFormat.format(travelPackage.mrp!!)
            binding.mCVtVMrp.paintFlags = Paint.STRIKE_THRU_TEXT_FLAG
            binding.tVOverviewText.text = travelPackage.textOverview!!.replace("\\n", "\n")

            daysList = ArrayList()

            try {

                for (day in travelPackage.daysList!!) {

                    daysList.add(day)
                }
            } catch (e: java.lang.Exception) {
                Log.d("daysList", "onCreate: ${e.message}")
            }

            daysList.firstOrNull()?.isExpanded = true
            daysAdapter = DaysAdapter(daysList)
            binding.rVDays.adapter = daysAdapter

            binding.rVInclusions.adapter =
                travelPackage.inclusionsList?.let { InclusionsAdapter(true, it) }

            binding.rVGuidelines.adapter =
                travelPackage.guidelinesList?.let { GuidelinesAdapter(true, it) }

            if (travelPackage.fAQsList?.isEmpty() == true) {

                binding.tL.getTabAt(4)?.let { binding.tL.removeTab(it) }
                binding.tVFAQs.visibility = View.GONE
                binding.rVFAQs.visibility = View.GONE
                binding.tVReadMoreFAQ.visibility = View.GONE
            }

            var faQsAdapter = travelPackage.fAQsList?.let { FAQsAdapter(limited = true, it) }

            binding.rVFAQs.adapter = faQsAdapter

            if (faQsAdapter != null) {
                faQsAdapter!!.onItemClick = {
                    faQsAdapter =
                        if (binding.tVReadMoreFAQ.visibility == View.VISIBLE) travelPackage!!.fAQsList?.let { it1 ->
                            FAQsAdapter(
                                limited = true, fAQsList = it1
                            )
                        }
                        else travelPackage!!.fAQsList?.let { it1 ->
                            FAQsAdapter(false, it1)
                        }
                }
            }

            binding.mT.setNavigationOnClickListener { onBackPressed() }
            binding.tVReadMore.setOnClickListener {
                binding.tVOverviewText.ellipsize = null
                binding.tVOverviewText.maxLines = Int.MAX_VALUE
                binding.tVReadMore.visibility = View.INVISIBLE
                binding.tVReadLess.visibility = View.VISIBLE
            }
            binding.tVReadLess.setOnClickListener {
                binding.tVReadLess.visibility = View.INVISIBLE
                binding.tVReadMore.visibility = View.VISIBLE
                binding.tVOverviewText.ellipsize = TextUtils.TruncateAt.END
                binding.tVOverviewText.maxLines = 3
            }
            binding.tVReadMore2.setOnClickListener {
                binding.tVReadMore2.visibility = View.INVISIBLE

                if (binding.tLInclusionsEx.selectedTabPosition == 0) {
                    binding.rVInclusions.adapter = travelPackage!!.inclusionsList?.let { it1 ->
                        InclusionsAdapter(
                            false, it1
                        )
                    }
                } else binding.rVInclusions.adapter = travelPackage!!.exclusionsList?.let { it1 ->
                    ExclusionsAdapter(
                        false, it1
                    )
                }
                binding.tVReadLess2.visibility = View.VISIBLE
            }
            binding.tVReadLess2.setOnClickListener {
                binding.tVReadLess2.visibility = View.INVISIBLE
                if (binding.tLInclusionsEx.selectedTabPosition == 0)

                    binding.rVInclusions.adapter = travelPackage.inclusionsList?.let { it1 ->
                        InclusionsAdapter(
                            true, it1
                        )
                    }
                else binding.rVInclusions.adapter = travelPackage.exclusionsList?.let { it1 ->
                    ExclusionsAdapter(
                        true, it1
                    )
                }

                binding.tVReadMore2.visibility = View.VISIBLE
            }
            binding.rVInclusions.isNestedScrollingEnabled = false
            binding.rVDays.isNestedScrollingEnabled = false

            val guidelines = travelPackage.guidelinesList?.let { GuidelinesAdapter(false, it) }

            binding.tVReadMoreGuid.setOnClickListener {
                binding.tVReadMoreGuid.visibility = View.INVISIBLE
                binding.rVGuidelines.adapter = travelPackage.guidelinesList?.let { it1 ->
                    GuidelinesAdapter(false, it1)
                }
                binding.tVReadLessGuid.visibility = View.VISIBLE
            }
            binding.tVReadLessGuid.setOnClickListener {
                binding.tVReadLessGuid.visibility = View.INVISIBLE
                binding.rVGuidelines.adapter = travelPackage.guidelinesList?.let { it1 ->
                    GuidelinesAdapter(true, it1)
                }
                binding.tVReadMoreGuid.visibility = View.VISIBLE
            }

            val faq = travelPackage.fAQsList?.let { FAQsAdapter(false, it) }
            if (faq?.itemCount!! <= 3) {
                binding.tVReadMoreFAQ.visibility = View.GONE
                binding.tVReadLessFAQ.visibility = View.GONE
            }

            binding.tVReadMoreFAQ.setOnClickListener {
                binding.tVReadMoreFAQ.visibility = View.INVISIBLE
                binding.rVFAQs.adapter = travelPackage.fAQsList?.let { it1 ->
                    FAQsAdapter(
                        limited = false, fAQsList = it1
                    )
                }
                binding.tVReadLessFAQ.visibility = View.VISIBLE
            }
            binding.tVReadLessFAQ.setOnClickListener {
                binding.tVReadLessFAQ.visibility = View.INVISIBLE
                binding.rVFAQs.adapter = travelPackage.fAQsList.let { it1 ->
                    FAQsAdapter(
                        limited = true, fAQsList = it1
                    )
                }
                binding.tVReadMoreFAQ.visibility = View.VISIBLE
            }

            binding.tL.addOnTabSelectedListener(this)
            binding.tLInclusionsEx.addOnTabSelectedListener(this)
            binding.buttonContinueBooking.setOnClickListener {

                val bookingIntent = Intent(this@PackageDetailsActivity, BookingActivity::class.java)
                bookingIntent.putExtra("viewType", viewType)
                bookingIntent.putExtra("detailsKey", tourPackageName)

                startActivity(bookingIntent)
            }

            makeVisible()
        }
    }

    private val onCardClickListener = object : OnCardClickListener {

        override fun onClick(detailed: Boolean, id: String) {

            docId = id

            Log.d("docId", "onClick: $docId")

            Log.d("update", "onClick: $currentUser?.uid")
            Log.d("update", "onClick: $binding.tVName.text.toString()")

            reviewCollection.whereEqualTo("id", currentUser?.uid)
                .whereEqualTo("textPackageName", binding.tVName.text.toString()).get()
                .addOnCompleteListener { task ->

                    if (task.isSuccessful) {
                        if (!task.result.isEmpty) {

                            for (doc in task.result.documents) {

                                if (doc["textPackageName"].toString() == binding.tVName.text.toString()) {

                                    dialog.findViewById<TextView>(R.id.tVPackageDialog).text =
                                        binding.tVName.text.toString().trim()
                                    eTName.setText(doc.get("textName").toString())
                                    eTCity.setText(doc.get("textCity").toString())
                                    eTReview.setText(doc.get("textReview").toString())

                                    val alreadyRated =
                                        (doc.get("rating").toString() + "f").toFloat()
                                    ratingBar.rating = alreadyRated

                                    dialog.findViewById<Button>(R.id.bTSubmit).setOnClickListener {

                                        val newReview = Review()
                                        newReview.timestamp =
                                            com.google.firebase.Timestamp(Date(Timestamp(System.currentTimeMillis()).time))
                                        newReview.textReview = eTReview.text.toString().trim()

                                        newReview.textCity = eTCity.text.toString().trim()
                                        newReview.textTime = currentDate
                                        newReview.textPackageName = binding.tVName.text.toString()

                                        newReview.id = currentUser?.uid
                                        newReview.rating = ratingBar.rating
                                        newReview.textName =
                                            dialog.findViewById<EditText>(R.id.eTName).text.toString()
                                        dialog.show()

                                        reviewCollection.document(id).set(newReview)
                                            .addOnSuccessListener {
                                                val snackBar = Snackbar.make(
                                                    binding.root,
                                                    "Review Updated Successfully",
                                                    Snackbar.LENGTH_LONG
                                                )
                                                val snackbarView = snackBar.view

                                                snackbarView.setBackgroundColor(
                                                    ContextCompat.getColor(
                                                        this@PackageDetailsActivity,
                                                        R.color.green_whatsapp
                                                    )
                                                )
                                                snackBar.show()

                                                dialog.dismiss()

                                                binding.rVReviews.visibility = View.VISIBLE
                                                binding.eFABWriteReview.visibility = View.GONE

                                                reviewCollection.whereEqualTo(
                                                    "textPackageName",
                                                    binding.tVName.text.toString()
                                                ).orderBy(
                                                    "timestamp", Query.Direction.DESCENDING
                                                ).addSnapshotListener { value, _ ->
                                                    if (value != null) {
                                                        val documentList = value.documents

                                                        reviewAdapter = ReviewAdapter(
                                                            documentList, null
                                                        )
                                                        binding.rVReviews.adapter = reviewAdapter
                                                    }
                                                }
                                            }.addOnFailureListener {
                                                Log.w(
                                                    "review update",
                                                    "Error updating document",
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

            "Itinerary" -> {
                Handler().post {
                    binding.aBL.setExpanded(false)

                    binding.sVFragmentPackageDetails.scrollTo(0, binding.tVItinerary.top)
                }
            }

            "Inclusion/Exclusion" -> {
                Handler().post {
                    binding.aBL.setExpanded(false)

                    binding.sVFragmentPackageDetails.scrollTo(0, binding.tLInclusionsEx.top)
                }
            }

            "Guidelines" -> Handler().post {
                binding.aBL.setExpanded(false)

                binding.sVFragmentPackageDetails.scrollTo(0, binding.tVGuidelines.top)
            }

            "FAQs" -> Handler().post {
                binding.aBL.setExpanded(false)

                binding.sVFragmentPackageDetails.scrollTo(0, binding.tVFAQs.top)
            }

            "Enquiry" -> Handler().post {
                binding.aBL.setExpanded(false)

                binding.sVFragmentPackageDetails.scrollTo(0, binding.mCVNeedHelp.top)
            }

            "Reviews" -> Handler().post {
                binding.aBL.setExpanded(false)

                binding.sVFragmentPackageDetails.scrollTo(0, binding.rVReviews.top)
            }

            "Inclusions" -> {

                if (binding.tVReadMore2.visibility == View.VISIBLE)

                    binding.rVInclusions.adapter = InclusionsAdapter(true, inclList)
                else binding.rVInclusions.adapter = InclusionsAdapter(false, inclList)

            }

            "Exclusions" -> {
                if (binding.tVReadMore2.visibility == View.VISIBLE)

                    binding.rVInclusions.adapter = ExclusionsAdapter(true, excList)
                else binding.rVInclusions.adapter = ExclusionsAdapter(false, excList)
            }
        }
    }

    override fun onTabUnselected(tab: TabLayout.Tab?) {
    }

    override fun onTabReselected(tab: TabLayout.Tab?) {
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

    private fun onSignInResult(result: FirebaseAuthUIAuthenticationResult?) {
        val response = result?.idpResponse

        if (result?.resultCode == RESULT_OK) {
            val currentUser = FirebaseAuth.getInstance().currentUser
            try {
                /* val user = User(
                     currentUser?.email,
                     currentUser?.uid,
                     currentUser?.displayName,
                     currentUser?.photoUrl.toString()
                 )
                 db.collection("user").add(user).addOnSuccessListener {
                         Log.d(
                             "User", "DocumentSnapshot written with ID: " + it?.id
                         )
                         val snackBar = Snackbar.make(
                             binding.root, "Signed in successfully", Snackbar.LENGTH_LONG
                         )
                         val snackbarView = snackBar.view

                         snackbarView.setBackgroundColor(
                             ContextCompat.getColor(
                                 this@PackageDetailsActivity, R.color.green_whatsapp
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

    private fun showLogInDialog(context: Context) {

        val builder = AlertDialog.Builder(context)
        builder.setTitle("Log In")
        builder.setMessage("You must be Logged in to Write a Review")
            .setPositiveButton("Proceed") { _, _ ->
                createSignInIntent()
            }.setNegativeButton("Cancel") { dialog, _ ->
                // User cancelled the Log Out action, dismiss the dialog
                dialog.cancel()
            }
        builder.create().show()
    }

    private fun makeVisible() {

        binding.cLActivity.visibility = View.VISIBLE
        binding.mCVBottom.visibility = View.VISIBLE
        binding.progressBar.visibility = View.INVISIBLE
    }
}
