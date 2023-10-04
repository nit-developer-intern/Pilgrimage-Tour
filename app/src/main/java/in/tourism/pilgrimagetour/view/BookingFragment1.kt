package `in`.tourism.pilgrimagetour.view

import android.os.Bundle
import android.os.Handler
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.addCallback

import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.children
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.commit
import androidx.fragment.app.replace
import androidx.navigation.fragment.findNavController
import com.firebase.ui.auth.AuthUI
import com.firebase.ui.auth.FirebaseAuthUIActivityResultContract

import com.firebase.ui.auth.data.model.FirebaseAuthUIAuthenticationResult
import com.google.android.gms.common.api.ApiException
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.FirebaseFirestore

import com.google.firebase.ktx.Firebase
import com.kizitonwose.calendar.core.*
import com.kizitonwose.calendar.view.CalendarView
import com.kizitonwose.calendar.view.MonthDayBinder
import com.kizitonwose.calendar.view.MonthHeaderFooterBinder
import com.kizitonwose.calendar.view.ViewContainer
import `in`.tourism.pilgrimagetour.*
import `in`.tourism.pilgrimagetour.R
import `in`.tourism.pilgrimagetour.ResourceFactory.packageCollection
import `in`.tourism.pilgrimagetour.databinding.FragmentBooking1Binding

import `in`.tourism.pilgrimagetour.viewmodel.BookingViewModel
import java.sql.Timestamp
import java.text.NumberFormat
import java.time.DayOfWeek
import java.time.LocalDate
import java.time.Year
import java.time.YearMonth
import java.time.format.TextStyle
import java.util.*
import kotlin.math.roundToInt

class BookingFragment1 : Fragment() {

    private lateinit var binding: FragmentBooking1Binding
    private var currentUser: FirebaseUser? = null
    private var db = FirebaseFirestore.getInstance()
    private var detailsData: TravelPac? = null
    private var viewType: Int? = null
    private var numberFormat = NumberFormat.getCurrencyInstance(Locale("en", "in"))

    //calendar
    private var selectedDate: LocalDate? = null
    private var today = LocalDate.now()
    private lateinit var currentMonth: YearMonth
    private lateinit var startMonth: YearMonth
    private lateinit var endMonth: YearMonth
    private lateinit var september2023: YearMonth
    private lateinit var august2023: YearMonth
    private var selectedDateFormatted = ""
    private var packageName = ""

    private var packageDays = ""
    private var packageCost = 0
    private var numOfAdults = 1
    private var packageAdultsCost = 0
    private var totalNumberOfPersons = 1
    private var totalAdultsCost = 0
    private var totalPackageCost = 0
    private var gST = 0.0
    private var grandTotal = 0

    private lateinit var childPercentCollection: CollectionReference
    private lateinit var childPercentDocument: DocumentReference
    private var childCostPercent: ChildCost? = null
    private var childCostPercentHel: Double? = null
    private var childCostPercentNonHel: Double? = null
    private var totalChildrenCost = 0
    private var numOfChildren = 0
    private var packageChildrenCost = 0
    private var couponCode: String? = null

    private lateinit var cV: CalendarView
    private var couponCostValue = 0
    private var totalAfterCoupon = 0
    private var advanceCost = 0
    private var userPayCost = 0.0
    private var isAdvance = true
    private var amountDueAfterAdvance = 0
    private val sharedViewModel: BookingViewModel by activityViewModels()
    private val signInLauncher =
        registerForActivityResult(FirebaseAuthUIActivityResultContract()) { res ->
            this.onSignInResult(res)
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        requireActivity().onBackPressedDispatcher.addCallback(this) {

            activity?.finish()
        }.isEnabled
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentBooking1Binding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val handler = Handler()

        numberFormat.maximumFractionDigits = 0
        numberFormat.currency = Currency.getInstance("INR")

        currentUser = Firebase.auth.currentUser

        val docId = (activity as BookingActivity).getDocID()

        Log.d("BookingFragment", "$docId")

        childPercentCollection = db.collection("childrenCostPercentage")
        childPercentDocument = childPercentCollection.document("childCostPercent")

        getFromVM()

        childCostPercentHel = 100.0
        childCostPercentNonHel = 40.0

        if (numOfChildren == 0) binding.grpChildren.visibility = View.GONE

        binding.iVBack.setOnClickListener {
            activity?.onBackPressed()
        }

        binding.iVPreviousMonth.setColorFilter(R.color.black)

        cV = binding.calendarView

        binding.iVPreviousMonth.setOnClickListener {
            val prevMon = cV.findFirstVisibleMonth()?.yearMonth!!.minusMonths(1)
            cV.smoothScrollToMonth(prevMon)
        }

        binding.iVNextMonth.setOnClickListener {
            val nextMonth = cV.findLastVisibleMonth()?.yearMonth!!.plusMonths(1)
            cV.smoothScrollToMonth(nextMonth)
        }

        if (packageAdultsCost == 0) {
            packageCollection(db).whereEqualTo("name", docId).get().addOnSuccessListener { docs ->

                for (document in docs) {
                    detailsData = document.toObject(TravelPac::class.java)
                    detailsData?.let { travelPackage ->

                        packageName = travelPackage.name.toString()
                        packageDays = travelPackage.days.toString()
                        packageCost = travelPackage.discountedPrice!!
                        packageAdultsCost = packageCost

                        viewType = travelPackage.viewType
                    }
                    calculateCost()

                    setInVM()

                    updateUI()

                    Timer().schedule(object : TimerTask() {
                        override fun run() {

                            handler.post {

                                makeVisible()
                            }
                        }
                    }, 1000)

                }
            }.addOnFailureListener { exception ->
                Log.w("BookingFragment", "Error getting documents: ", exception)
            }
        } else {
            calculateCost()
            setInVM()
            updateUI()

            Timer().schedule(object : TimerTask() {
                override fun run() {

                    handler.post {

                        makeVisible()
                    }
                }
            }, 1000)

        }

        //calendar setup
        currentMonth = YearMonth.now()
        startMonth = currentMonth
        endMonth = YearMonth.of(2024, 12)

        september2023 = YearMonth.of(2023, 9)
        august2023 = YearMonth.of(2023, 8)

        val daysOfWeek = daysOfWeek(firstDayOfWeek = DayOfWeek.SUNDAY)

        class DayViewContainer(view: View) : ViewContainer(view) {
            lateinit var day: CalendarDay

            val textView = view.findViewById<TextView>(R.id.calendarDayText)
            val imageViewBackground = view.findViewById<ImageView>(R.id.iVCalendarBg)

            init {
                view.setOnClickListener {
                    if (day.position == DayPosition.MonthDate) {

                        if (viewType == 2) {

                            if (day.date < today.plusDays(3)) {
                                cV.notifyDayChanged(day)
                            } else if (day.date > YearMonth.of(2023, 10).atDay(
                                    20
                                ) && day.date < YearMonth.of(2024, 4).atDay(10)
                            ) {
                                cV.notifyDayChanged(day)
                            } else if (day.date > YearMonth.of(2024, 6)
                                    .atDay(20) && day.date < YearMonth.of(2024, 9).atDay(
                                    10
                                )
                            ) {

                                cV.notifyDayChanged(day)
                            } else if (day.date > YearMonth.of(2024, 10).atDay(20)) {

                                cV.notifyDayChanged(day)
                            } else if (selectedDate == day.date) {
                                selectedDate = null

                                cV.notifyDayChanged(day)
                            } else {

                                val oldDate = selectedDate
                                selectedDate = day.date
                                oldDate?.let { cV.notifyDateChanged(oldDate) }
                                cV.notifyDateChanged(day.date)

                                setInVM()

                                binding.sVBooking.scrollTo(0, binding.mCVContinue.top)
                                updateUI()
                            }

                        } else {
                            if (day.date < startMonth.atDay(
                                    LocalDate.now().plusDays(3).dayOfMonth
                                )
                            ) {
                                cV.notifyDayChanged(day)
                            } else if (selectedDate == day.date) {
                                selectedDate = null

                                cV.notifyDayChanged(day)
                            } else {
                                val oldDate = selectedDate
                                selectedDate = day.date
                                oldDate?.let { cV.notifyDateChanged(oldDate) }
                                cV.notifyDateChanged(day.date)

                                setInVM()

                                binding.sVBooking.scrollTo(0, binding.mCVContinue.top)
                                updateUI()
                            }
                        }
                    }
                }
            }
        }

        class MonthViewContainer(view: View) : ViewContainer(view) {
            val titlesContainer = view as ViewGroup
        }

        cV.dayBinder = object : MonthDayBinder<DayViewContainer> {
            // Called only when a new container is needed.
            override fun create(view: View) = DayViewContainer(view)

            // Called every time we need to reuse a container.
            override fun bind(container: DayViewContainer, data: CalendarDay) {

                container.day = data

                bindDate(
                    data.date,
                    container.textView,
                    container.imageViewBackground,
                    data.position == DayPosition.MonthDate
                )
            }
        }

        cV.monthScrollListener = { updateMonth() }

        cV.monthHeaderBinder = object : MonthHeaderFooterBinder<MonthViewContainer> {
            override fun create(view: View) = MonthViewContainer(view)
            override fun bind(container: MonthViewContainer, data: CalendarMonth) {
                // Remember that the header is reused so this will be called for each month.
                // However, the first day of the week will not change so no need to bind
                // the same view every time it is reused.
                if (container.titlesContainer.tag == null) {
                    container.titlesContainer.tag = data.yearMonth
                    container.titlesContainer.children.map { it as TextView }
                        .forEachIndexed { index, textView ->
                            val dayOfWeek = daysOfWeek[index]
                            val title =
                                dayOfWeek.getDisplayName(TextStyle.SHORT, Locale.getDefault())
                            textView.text = title
                            // In the code above, we use the same `daysOfWeek` list
                            // that was created when we set up the calendar.
                            // However, we can also get the `daysOfWeek` list from the month data:
                            // val daysOfWeek = data.weekDays.first().map { it.date.dayOfWeek }
                            // Alternatively, you can get the value for this specific index:
                            // val dayOfWeek = data.weekDays.first()[index].date.dayOfWeek
                        }
                }
            }
        }

        cV.setup(startMonth, endMonth, daysOfWeek.first())


        if (sharedViewModel.advanceCost.value != 0) {
            selectedDate = sharedViewModel.date.value
            selectedDate?.let { cV.notifyDateChanged(it) }
        } else if (viewType == 2) {
            selectedDate = YearMonth.of(2023, 10).atDay(19)
            selectedDate?.let { cV.notifyDateChanged(it) }
        } else selectedDate = today.plusDays(3)

        selectedDate?.let { cV.scrollToMonth(it.yearMonth) }
        binding.tVMonth.text = "${selectedDate?.month} ${selectedDate?.year}"
        binding.cB1PayAdvance.isChecked = true
        updateUI()

        binding.tVPlusAdults.setOnClickListener {

            if (numOfAdults < 6) numOfAdults += 1

            increaseOrDecreaseAdults()
        }

        binding.tVMinusAdults.setOnClickListener {

            if (numOfAdults > 1) numOfAdults -= 1

            increaseOrDecreaseAdults()
        }

        binding.tVMinusChildren.setOnClickListener {

            if (numOfChildren > 0) numOfChildren -= 1

            increaseOrDecreaseChildren()
            if (numOfChildren == 0) binding.grpChildren.visibility = View.GONE
        }

        binding.tVPlusChildren.setOnClickListener {

            binding.grpChildren.visibility = View.VISIBLE

            if (numOfChildren < 6) numOfChildren += 1

            increaseOrDecreaseChildren()

        }

        binding.mCvApplyCoupon.setOnClickListener {
            setInVM()


            activity?.supportFragmentManager?.commit {
                replace<CouponsFragment>(R.id.navHostFragmentBooking)
                setReorderingAllowed(true)
                addToBackStack("name") // Name can be null
            }
        }

        binding.tVMoreCoupons.setOnClickListener {
            activity?.supportFragmentManager?.commit {
                replace<CouponsFragment>(R.id.navHostFragmentBooking)
                setReorderingAllowed(true)
                addToBackStack("name") // Name can be null
            }
        }

        binding.iVDeleteCoupon.setOnClickListener {
            couponCostValue = 0
            couponCode = ""

            calculateCost()
            setInVM()
            updateUI()
        }

        binding.tVPayAdvance.setOnClickListener {
            if (!binding.cB1PayAdvance.isChecked) {
                binding.cB1PayAdvance.performClick()
            }
        }

        binding.tVPayFull.setOnClickListener {
            if (!binding.cB2Payfull.isChecked) {
                isAdvance = false
                binding.cB2Payfull.performClick()
            }
        }

        if (!binding.cB1PayAdvance.isChecked) binding.cB2Payfull.isChecked = true
        else binding.cB1PayAdvance.isChecked = true

        binding.cB1PayAdvance.setOnCheckedChangeListener { button, isChecked ->

            if (isChecked) {
                binding.cB2Payfull.isChecked = false

                binding.tVAmtduelater.visibility = View.VISIBLE
                binding.tVAmtduelaterCost.visibility = View.VISIBLE
                button.isEnabled = false
                isAdvance = true
                calculateCost()
                setInVM()
                updateUI()
            } else button.isEnabled = true

        }


        binding.cB2Payfull.setOnCheckedChangeListener { button, isChecked ->

            if (isChecked) {
                binding.cB1PayAdvance.isChecked = false
                button.isEnabled = false
                binding.tVAmtduelater.visibility = View.GONE
                binding.tVAmtduelaterCost.visibility = View.GONE

                isAdvance = false
                calculateCost()
                setInVM()
                updateUI()
            } else button.isEnabled = true

        }

        binding.btContinueBooking.setOnClickListener {
            if (currentUser != null) {
                findNavController().navigate(
                    BookingFragment1Directions.actionBookingFragment1To()
                )
            } else createSignInIntent()
        }
    }

    private fun updateMonth() {
        val month = cV.findFirstVisibleMonth()?.yearMonth ?: return

        binding.tVMonth.text = "${month.month} - ${month.year}"

        if (month.year == Year.now().value) {

            when (month.month) {

                startMonth.month -> {

                    binding.iVPreviousMonth.setColorFilter(R.color.black)
                    binding.iVNextMonth.colorFilter = null
                }
                endMonth.month -> {

                    binding.iVNextMonth.setColorFilter(R.color.black)
                    binding.iVPreviousMonth.colorFilter = null
                }
                else -> {
                    binding.iVPreviousMonth.colorFilter = null
                    binding.iVNextMonth.colorFilter = null
                }
            }
        } else {
            when (month.month) {

                startMonth.month -> {

                    binding.iVPreviousMonth.colorFilter = null
                    binding.iVNextMonth.colorFilter = null
                }
                endMonth.month -> {

                    binding.iVNextMonth.setColorFilter(R.color.black)
                    binding.iVPreviousMonth.colorFilter = null
                }
                else -> {
                    binding.iVPreviousMonth.colorFilter = null
                    binding.iVNextMonth.colorFilter = null
                }
            }
        }
    }

    private fun bindDate(
        date: LocalDate, textView: TextView, imageView: ImageView, isSelectable: Boolean
    ) {
        textView.text = date.dayOfMonth.toString()

        if (isSelectable) {

            when (date) {

                selectedDate -> {

                    when (viewType) {

                        2 -> {
                            if (date >= currentMonth.atDay(
                                    LocalDate.now().plusDays(3).dayOfMonth
                                )
                            ) {
                                textView.setTextColor(resources.getColor(R.color.white))
                                textView.setBackgroundResource(R.color.transparent)
                                imageView.setBackgroundResource(R.color.blue_google)
                            }
                        }
                        else -> {
                            //other package type

                            if (date >= currentMonth.atDay(
                                    LocalDate.now().plusDays(3).dayOfMonth
                                )
                            ) {
                                textView.setTextColor(resources.getColor(R.color.white))
                                textView.setBackgroundResource(R.color.transparent)
                                imageView.setBackgroundResource(R.color.blue_google)
                            }
                        }
                    }
                }
                today -> {
                    textView.setTextColor(resources.getColor(R.color.gray))
                    textView.setBackgroundResource(R.color.white)
                    imageView.setBackgroundResource(R.color.white)
                }
                else -> {
                    when (viewType) {
                        2 -> {
                            if (date < today.plusDays(3)) {
                                textView.setTextColor(resources.getColor(R.color.gray))
                                textView.setBackgroundResource(R.color.white)
                            }
                        }
                        else -> {

                            if (date < currentMonth.atDay(
                                    LocalDate.now().plusDays(3).dayOfMonth
                                )
                            ) {
                                textView.setTextColor(resources.getColor(R.color.gray))
                                textView.setBackgroundResource(R.color.white)
                            } else if (date >= currentMonth.atDay(
                                    LocalDate.now().plusDays(3).dayOfMonth
                                )
                            ) {

                                textView.setTextColor(resources.getColor(R.color.black))
                                textView.setBackgroundResource(R.color.white)

                                imageView.setBackgroundResource(R.color.white)
                            }
                        }
                    }
                }
            }
        } else {
            textView.setTextColor(resources.getColor(R.color.gray))
            textView.setBackgroundResource(R.color.white)
            imageView.setBackgroundResource(R.color.white)
        }
    }

    private fun scrollDown() {
        binding.sVBooking.post {
            binding.sVBooking.fullScroll(View.FOCUS_DOWN)
        }
    }

    private fun getFromVM() {

        selectedDate = sharedViewModel.date.value

        viewType = sharedViewModel.viewType.value

        packageName = sharedViewModel.packageName.value!!
        packageDays = sharedViewModel.packageDays.value!!
        packageCost = sharedViewModel.packageAdultCost.value!!
        packageAdultsCost = sharedViewModel.packageAdultCost.value!!
        packageChildrenCost = sharedViewModel.packageChildrenCost.value!!

        numOfAdults = sharedViewModel.numberofadults.value!!
        numOfChildren = sharedViewModel.numberOfChildren.value!!

        gST = sharedViewModel.gSTCost.value!!

        grandTotal = sharedViewModel.grandTotal.value!!

        couponCode = sharedViewModel.couponCode.value!!

        couponCostValue = sharedViewModel.couponCost.value!!

        totalAfterCoupon = sharedViewModel.totalafterCoupon.value!!

        advanceCost = sharedViewModel.advanceCost.value!!

        isAdvance = sharedViewModel.isAdvance.value!!
    }

    private fun setInVM() {
        viewType?.let { sharedViewModel.setViewType(it) }

        sharedViewModel.setDate(selectedDate!!)

        sharedViewModel.setNumberofAdults(numOfAdults)

        sharedViewModel.setNumberOfChildren(numOfChildren)

        sharedViewModel.setPackageName(packageName)

        sharedViewModel.setPackageDays(packageDays)

        sharedViewModel.setPackageAdultCost(packageAdultsCost)

        sharedViewModel.setPackageChildrenCost(packageChildrenCost)

        sharedViewModel.setGSTCost(gST)

        sharedViewModel.setGrandTotal(grandTotal)

        couponCode?.let { sharedViewModel.setCouponCode(it) }

        sharedViewModel.setCouponCost(couponCostValue)

        sharedViewModel.setTotalaftercoupon(totalAfterCoupon)

        sharedViewModel.setAdvanceCost(advanceCost)

        sharedViewModel.setAmountDueCost(amountDueAfterAdvance)

        sharedViewModel.setUserPayCost(userPayCost)

        sharedViewModel.setAdvance(isAdvance)

    }

    private fun calculateCost() {

        totalNumberOfPersons = numOfChildren + numOfAdults
        totalAdultsCost = packageAdultsCost.times(numOfAdults)
        totalChildrenCost = packageChildrenCost.times(numOfChildren)
        totalPackageCost = totalAdultsCost.plus(totalChildrenCost)

        gST = (7.5 * totalPackageCost) / 100

        grandTotal = totalPackageCost.plus(gST).roundToInt()

        totalAfterCoupon = grandTotal.minus(couponCostValue)

        advanceCost = (25 * totalAfterCoupon) / 100

        if (binding.cB1PayAdvance.isChecked) {
            userPayCost = advanceCost.toDouble()

            amountDueAfterAdvance = totalAfterCoupon.minus(advanceCost)
        } else if (binding.cB2Payfull.isChecked) {

            userPayCost = totalAfterCoupon.toDouble()

            amountDueAfterAdvance = 0

        }

    }

    private fun updateUI() {

        selectedDateFormatted =
            "${selectedDate?.dayOfMonth} ${selectedDate?.month}, ${selectedDate?.year} "

        binding.chosenDate.text = selectedDateFormatted

        binding.tVAdultsNumber.text = numOfAdults.toString()
        binding.tVChildrenNumber.text = numOfChildren.toString()

        if (viewType == 2) binding.tVChildrenAgeRange.text = "2-12 Years"
        else binding.tVChildrenAgeRange.text = "5-12 Years"


        binding.tVBookingPackageName.text = "$packageName - $packageDays"

        binding.chosenNumOfPerson.text = totalNumberOfPersons.toString()
        binding.tVPackageCost.text = numberFormat.format(packageCost)

        binding.tVPackageAdultsCost.text = numberFormat.format(packageAdultsCost)
        binding.tVNumOfAdults.text = "X  $numOfAdults"
        binding.tVPackageAdultsTotalCost.text = numberFormat.format(totalAdultsCost)

        binding.tVPackageChildrenCost.text = numberFormat.format(packageChildrenCost)
        binding.tVNumOfChildren.text = "X  $numOfChildren"
        binding.tVPackageChildrenTotalCost.text = numberFormat.format(totalChildrenCost)

        binding.tVGSTCost.text = numberFormat.format(gST)
        binding.tVGrandTotalCost.text = numberFormat.format(totalPackageCost.plus(gST))

        if (couponCode != null && couponCostValue > 0) {
            binding.mCvApplyCoupon.visibility = View.GONE
            binding.mCVCouponApplied.visibility = View.VISIBLE
            binding.tVYouSaved.text = "You saved ₹$couponCostValue"
            binding.tVCouponApplied.text = "$couponCode Applied"
            scrollDown()

        } else {
            binding.mCvApplyCoupon.visibility = View.VISIBLE
            binding.mCVCouponApplied.visibility = View.GONE
        }

        binding.tVPayAdvance.text = numberFormat.format(advanceCost)
        binding.tVPayFull.text = numberFormat.format(totalAfterCoupon)
        binding.tVAmtduelaterCost.text = numberFormat.format(amountDueAfterAdvance)

        if (binding.cB1PayAdvance.isChecked) {
            binding.tVShowPaymentType.text = "Partial Payment"
            binding.tVAmtduelater.visibility = View.VISIBLE
            binding.tVAmtduelaterCost.visibility = View.VISIBLE

        } else if (binding.cB2Payfull.isChecked) {
            binding.tVShowPaymentType.text = "Full Payment"
        }
        sharedViewModel.setPaymentType(binding.tVShowPaymentType.text.toString())
        binding.tVPayCost.text = numberFormat.format(userPayCost)

    }

    private fun increaseOrDecreaseAdults() {
        calculateCost()

        sharedViewModel.setCustomerList(arrayListOf())

        setInVM()

        updateUI()
    }

    private fun increaseOrDecreaseChildren() {

        childPercentDocument.get().addOnCompleteListener { task ->

            if (task.isSuccessful) {
                val document = task.result
                Log.d("BookingFragmentDocument", "${document.data}")

                if (document.exists()) {
                    childCostPercent = document.toObject(ChildCost::class.java)
                    childCostPercent.let {

                        childCostPercentHel = it?.childCostPercentHel
                        childCostPercentNonHel = it?.childCostPercentNonHel
                    }
                } else {
                    Log.d("BookingFragment", "No such Document")
                }
            } else {
                childCostPercentHel = 100.0
                childCostPercentNonHel = 100.0
            }
        }

        packageChildrenCost = if (viewType == 2) {
            childCostPercentHel!!.times(packageAdultsCost).div(100).roundToInt()
        } else {
            childCostPercentNonHel!!.times(packageAdultsCost).div(100).roundToInt()
        }


        calculateCost()

        setInVM()

        updateUI()
    }

    private fun createSignInIntent() {
        val providers = arrayListOf(
            AuthUI.IdpConfig.EmailBuilder().build(), AuthUI.IdpConfig.GoogleBuilder().build()
        )

// Create and launch sign-in intent
        val signInIntent =
            AuthUI.getInstance().createSignInIntentBuilder().setAvailableProviders(providers)
                .build()
        signInLauncher.launch(signInIntent)
    }

    private fun onSignInResult(result: FirebaseAuthUIAuthenticationResult?) {
        val response = result?.idpResponse

        if (result?.resultCode == AppCompatActivity.RESULT_OK) {
            val currentUser = FirebaseAuth.getInstance().currentUser
            try {
                /* val user = User(
                     currentUser?.email,
                     currentUser?.uid,
                     currentUser?.displayName,
                     currentUser?.photoUrl.toString()
                 )
                 db?.collection("user")?.add(user)?.addOnSuccessListener {
                     Log.d(
                         "Booking", "DocumentSnapshot written with ID: " + it?.id
                     )
                     val snackBar = Snackbar.make(
                         binding.root, "Signed in successfully", Snackbar.LENGTH_LONG
                     )
                     val snackbarView = snackBar.view

                     snackbarView.setBackgroundColor(
                         ContextCompat.getColor(
                             requireContext(), R.color.green_whatsapp
                         )
                     )
                     snackBar.show()
                     findNavController().navigate(
                         BookingFragment1Directions.actionBookingFragment1ToBookingFragment2(
                             numberofAdults, numberofChildren
                         )
                     )

                 }?.addOnFailureListener {
                     Log.d(
                         "Booking", "Error in adding user"
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
                                        TAG, "onSignInResult: Updated"
                                    )
                                    val snackBar = Snackbar.make(
                                        binding.root, sig, Snackbar.LENGTH_LONG
                                    )
                                    val snackbarView = snackBar.view

                                    snackbarView.setBackgroundColor(
                                        ContextCompat.getColor(
                                            requireContext(), R.color.green_whatsapp
                                        )
                                    )
                                    snackBar.show()

                                    findNavController().navigate(
                                        BookingFragment1Directions.actionBookingFragment1To()
                                    )
                                }.addOnFailureListener {
                                    Log.d(TAG, "onSignInResult: Failed")
                                }
                            Log.d(TAG, "user: Existing")
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
                                        requireContext(), R.color.green_whatsapp
                                    )
                                )
                                snackBar.show()

                                findNavController().navigate(
                                    BookingFragment1Directions.actionBookingFragment1To()
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

    private fun makeVisible() {
        binding.iVLoading.visibility = View.GONE
        binding.pBLoading.visibility = View.INVISIBLE
        binding.sVBooking.visibility = View.VISIBLE
        binding.mCVContinue.visibility = View.VISIBLE
    }

    companion object {

        const val TAG = "BookingFrag1TAG"
    }
}
