package `in`.tourism.pilgrimagetour.view

import android.R
import android.app.DatePickerDialog
import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.core.content.ContextCompat
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import `in`.tourism.pilgrimagetour.adapter.AdultCustomerAdapter
import `in`.tourism.pilgrimagetour.CustomerDetails
import `in`.tourism.pilgrimagetour.databinding.FragmentBooking2Binding
import `in`.tourism.pilgrimagetour.viewmodel.BookingViewModel
import `in`.tourism.pilgrimagetour.OnBookingFragmentInteractionListener
import java.text.NumberFormat
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.util.Calendar
import java.util.Currency
import java.util.Locale
import kotlin.math.roundToInt


class BookingFragment2 : Fragment(), AdapterView.OnItemSelectedListener {

    private lateinit var binding: FragmentBooking2Binding
    private var currentUser: FirebaseUser? = null
    private var totalNumberOfPersons = 1
    private var numberOfAdults = 1
    private var numberOfChildren = 0
    private var numberFormat = NumberFormat.getCurrencyInstance(Locale("en", "in"))
    private var userpayCost = 0.0
    private var packageName: String? = null
    private var selectedDate = LocalDate.now()
    private var amountDueCost = 0

    var adultsList = ArrayList<CustomerDetails>()
    var childrenList = ArrayList<CustomerDetails>()
    private lateinit var adultCustomerAdapter: AdultCustomerAdapter
    private lateinit var childCustomerAdapter: AdultCustomerAdapter
    private var iDType = ""
    private var viewType = 1
    private var razorpayAmount = 0
    private var leadTraveller: String = ""
    private var leadEmail: String = ""
    private var iDNumber: String = ""
    private var mobileNumber: String = ""
    private var fullAddress: String = ""
    private var addReq: String = ""
    private var arrivalDate: String = ""
    private var arrivalCity: String = ""
    private var packageDays: String? = null
    private var paymentType = ""
    private lateinit var calendar: Calendar
    private lateinit var dateFormat: SimpleDateFormat
    private var selectedDateFormatted = ""
    var isLeadValid = 0
    var isCustomerListValid = 0

    private var listener: OnBookingFragmentInteractionListener? = null
    private val sharedViewModel: BookingViewModel by activityViewModels()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentBooking2Binding.inflate(layoutInflater)
        return binding.root
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)

        if (context is OnBookingFragmentInteractionListener) listener = context
        else throw IllegalArgumentException("$context must implement OnBookingFragmentInteractionListener")
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        numberFormat.maximumFractionDigits = 0
        numberFormat.currency = Currency.getInstance("INR")

        currentUser = Firebase.auth.currentUser

        calendar = Calendar.getInstance()

        dateFormat = SimpleDateFormat("MM/dd/yyyy")

        getFromVM()

        totalNumberOfPersons = numberOfAdults + numberOfChildren

        updateViews()

        leadTraveller = binding.tIETLeadTravellerName.text.toString()
        leadEmail = binding.tIETLeadTravellerEmail.text.toString()
        iDNumber = binding.tIETLeadIDNumber.text.toString()
        mobileNumber = binding.tIETLeadTravellerPhone.text.toString()
        fullAddress = binding.tIETLeadTravellerAddress.text.toString()
        addReq = binding.tIETAdditionalInfo.text.toString()

        if (leadEmail.isNotBlank()) {
            binding.tIETLeadTravellerName.setText(leadTraveller)
            binding.tIETLeadTravellerEmail.setText(leadEmail)
            binding.tIETLeadTravellerPhone.setText(mobileNumber)
        }

        binding.iVBackArrow.setOnClickListener {
            findNavController().navigate(
                BookingFragment2Directions.actionBookingFragment2ToBookingFragment1()
            )
        }

        adultsList = ArrayList()
        childrenList = ArrayList<CustomerDetails>()

        repeat(numberOfAdults) { index ->

            adultsList.add(
                CustomerDetails("Adult ${index + 1}", "", "", "", "")
            )
        }

//        adultsList[0] = CustomerDetails(
//            customerHeading = "Adult 1",
//            customerNAme = currentUser?.displayName!!,
//            customerGender = "",
//            customerAge = "",
//            customerWeight = ""
//        )

        if (numberOfChildren > 0) {
            repeat(numberOfChildren) { index ->
                childrenList.add(
                    CustomerDetails(
                        "Child ${index + 1}", "", "", "", ""
                    )
                )
            }
        }

        val customersList = sharedViewModel.customersList.ifEmpty { adultsList + childrenList }

        adultCustomerAdapter = AdultCustomerAdapter(
            viewType, false, requireContext(), numberOfAdults, adultsList
        )

        childCustomerAdapter = AdultCustomerAdapter(
            viewType, false, requireContext(), numberOfChildren, childrenList
        )

        razorpayAmount = userpayCost.times(100).roundToInt()

        binding.rVAdults.adapter = adultCustomerAdapter
        binding.rVChildren.adapter = childCustomerAdapter

        binding.spinnerIDType.onItemSelectedListener = this

        val iDItems = arrayOf("Aadhaar Card", "Voter Card", "PAN Card", "Driver's License")
        val dataAdapter = ArrayAdapter(requireContext(), R.layout.simple_spinner_item, iDItems)

        dataAdapter.setDropDownViewResource(R.layout.simple_spinner_dropdown_item)

        binding.spinnerIDType.adapter = dataAdapter

        val date = DatePickerDialog.OnDateSetListener { datePicker, year, month, day ->
            calendar.set(Calendar.YEAR, year)
            calendar.set(Calendar.MONTH, month)
            calendar.set(Calendar.DAY_OF_MONTH, day)
            binding.tIETLeadDate.setText(dateFormat.format(calendar.time))
        }

        binding.tIETLeadDate.setOnClickListener {
            val datePickerDialog = DatePickerDialog(
                requireContext(),
                date,
                calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH)
            )
            datePickerDialog.datePicker.minDate = calendar.timeInMillis
            datePickerDialog.show()
        }

        binding.buttonContinuePayment.setOnClickListener {
//

            if (ValidateLeadTraveller()) {

                if (viewType == 2) {

                    if (adultsList.any { customer ->
                            customer.customerWeight == "" ||
                                    customer.customerWeight.toInt() >= 250
                        }) {
                        adultCustomerAdapter.validateCustomer()
                    }

                    if (childrenList.any { customer ->
                            customer.customerWeight == "" || customer.customerWeight.toInt() > 250
                        }) {
                        childCustomerAdapter.validateCustomer()

//                    scroll()
                    }
                }

                if (adultsList.any { customer -> customer.customerNAme.trim() == ""}) {
                    adultCustomerAdapter.validateCustomer()
                } else if (adultsList.any { customer -> customer.customerAge == "" }) {
                    adultCustomerAdapter.validateCustomer()

                } else if (adultsList.any { customer -> customer.customerAge.toInt() > 110 }) {
                    adultCustomerAdapter.validateCustomer()
                } else if (childrenList.any { customer -> customer.customerNAme == "" }) {
                    childCustomerAdapter.validateCustomer()
                } else if (childrenList.any { customer -> customer.customerAge == "" }) {
                    childCustomerAdapter.validateCustomer()
                } else if (childrenList.any { customer -> customer.customerAge.toInt() > 110 }) {
                    childCustomerAdapter.validateCustomer()
                } else {

                    Log.d("BookingFragment2", adultsList.toList().toString())
                    Log.d("BookingFragment2", childrenList.toList().toString())

                    setinVM(adultsList, childrenList)


                    listener?.sendData(
                        "razorpay",
                        razorpayAmount,
                        yatraPackage = "$packageName - $packageDays",
                        yatraDate = selectedDate,
                        totalYatri = totalNumberOfPersons
                    )

                    val snackBar =
                        Snackbar.make(
                            binding.root,
                            "Please Wait...",
                            Snackbar.LENGTH_LONG
                        )
                    val snackbarView = snackBar.view

                    snackbarView.setBackgroundColor(
                        ContextCompat.getColor(
                            requireContext(),
                            R.color.holo_green_dark
                        )
                    )
                    snackBar.show()
                }
            }

        }

    }

    private fun getFromVM() {

        numberOfAdults = sharedViewModel.numberofadults.value!!
        numberOfChildren = sharedViewModel.numberOfChildren.value!!

        viewType = sharedViewModel.viewType.value!!
        selectedDate = sharedViewModel.date.value

        leadTraveller = sharedViewModel.leadTraveller.value!!
        leadEmail = sharedViewModel.leadEmail.value!!
        mobileNumber = sharedViewModel.leadContact.value!!

        packageName = sharedViewModel.packageName.value
        packageDays = sharedViewModel.packageDays.value

        userpayCost = sharedViewModel.userPayCost.value!!
        amountDueCost = sharedViewModel.amountDueCost.value!!
        paymentType = sharedViewModel.paymentType.value!!

    }

    private fun setinVM(
        adultsList: ArrayList<CustomerDetails>,
        childrenList: ArrayList<CustomerDetails>
    ) {

        sharedViewModel.setadultsList(adultsList as ArrayList<CustomerDetails>)
        sharedViewModel.setChildList(childrenList as ArrayList<CustomerDetails>)

        sharedViewModel.setViewType(viewType)

        sharedViewModel.setLeadTraveller(leadTraveller)
        sharedViewModel.setIDType(iDType)
        sharedViewModel.setIDNumber(iDNumber)
        sharedViewModel.setLeadEmail(leadEmail)
        sharedViewModel.setLeadContact(mobileNumber)
        sharedViewModel.setFullAddress(fullAddress)
        sharedViewModel.setArrivalDate(arrivalDate)
        sharedViewModel.setArrivalCity(arrivalCity)
        sharedViewModel.setAdditionalRequirement(addReq)

        sharedViewModel.setPaymentType(paymentType)
        sharedViewModel.setUserPayCost(userpayCost)

    }

    private fun updateViews() {

        selectedDateFormatted =
            "${selectedDate.dayOfMonth} ${selectedDate.month} , ${selectedDate.year}"

        binding.tIETLeadTravellerName.setText(currentUser?.displayName)
        binding.tIETLeadTravellerEmail.setText(currentUser?.email)
        binding.tIETLeadTravellerPhone.setText(currentUser?.phoneNumber)
        binding.tIETLeadIDNumber.setText(iDNumber)
        binding.tIETLeadTravellerAddress.setText(fullAddress)
        binding.tIETLeadDate.setText("$selectedDate")
        binding.tIETLeadCity.setText(sharedViewModel.arrivalCity.value)
        binding.tIETAdditionalInfo.setText(sharedViewModel.additionalRequirement.value)

        binding.chosenDate.text = selectedDateFormatted
        binding.chosenNumofPerson.text = totalNumberOfPersons.toString()
        binding.tVYouPay.text = numberFormat.format(userpayCost)
        binding.tVYouPayCostBottom.text = numberFormat.format(userpayCost)
        binding.tVAmtduelaterCost.text = numberFormat.format(amountDueCost)
        binding.tVShowBreakup.text = paymentType

        binding.iVID.setOnClickListener { binding.spinnerIDType.performClick() }

    }

    private fun scroll() {

        binding.sVBooking2.post {
            binding.sVBooking2.fullScroll(View.FOCUS_UP)
        }
    }

    private fun validateCustomerList() {

        if (adultsList.any { customer -> customer.customerNAme == "" }) {
            adultCustomerAdapter.validateCustomer()
        } else if (adultsList.any { customer -> customer.customerAge == "" }) {
            adultCustomerAdapter.validateCustomer()

        } else if (adultsList.any { customer -> customer.customerAge.toInt() > 110 }) {
            adultCustomerAdapter.validateCustomer()
        } else if (childrenList.any { customer -> customer.customerNAme == "" }) {
            childCustomerAdapter.validateCustomer()
        } else if (childrenList.any { customer -> customer.customerAge == "" }) {
            childCustomerAdapter.validateCustomer()
        } else if (childrenList.any { customer -> customer.customerAge.toInt() > 110 }) {
            childCustomerAdapter.validateCustomer()
        }
        isCustomerListValid = 1
        Log.d("BookingFragment2", adultsList.toList().toString())
        Log.d("BookingFragment2", childrenList.toList().toString())

    }


    private fun ValidateLeadTraveller(): Boolean {

        if (binding.tIETLeadTravellerName.text.toString().trim().isEmpty()) {
            binding.tIETLeadTravellerName.error = "Please enter valid name"
            binding.tIETLeadTravellerName.requestFocus()
            return false
        } else if (binding.tIETLeadIDNumber.text.toString().trim().isEmpty()) {
            binding.tIETLeadIDNumber.error = "Please enter valid ID number"
            binding.tIETLeadIDNumber.requestFocus()
            return false
        } else if (binding.tIETLeadTravellerEmail.text.toString().trim().isEmpty() ||
            !android.util.Patterns.EMAIL_ADDRESS.matcher(
                binding.tIETLeadTravellerEmail.text.toString()
            ).matches()
        ) {
            binding.tIETLeadTravellerEmail.error = "Please enter valid Email"
            binding.tIETLeadTravellerEmail.requestFocus()
            return false
        } else if (binding.tIETLeadTravellerPhone.text.toString().trim().isEmpty()
            || (binding.tIETLeadTravellerPhone.text.toString().length != 10))
        {
            binding.tIETLeadTravellerPhone.error = "Please enter valid Number"
            binding.tIETLeadTravellerPhone.requestFocus()
            return false
        } else {
            isLeadValid = 1
            leadTraveller = binding.tIETLeadTravellerName.text.toString()
            leadEmail = binding.tIETLeadTravellerEmail.text.toString()
            iDNumber = binding.tIETLeadIDNumber.text.toString()
            mobileNumber = binding.tIETLeadTravellerPhone.text.toString()
            fullAddress = binding.tIETLeadTravellerAddress.text.toString()
            arrivalDate = binding.tIETLeadDate.text.toString()
            arrivalCity = binding.tIETLeadCity.text.toString()
            addReq = binding.tIETAdditionalInfo.text.toString()

            setinVM(adultsList, childrenList)
            Log.d("BookingFragment2", "$leadTraveller")
            Log.d("BookingFragment2", "$leadEmail")
            Log.d("BookingFragment2", "$iDNumber")
            Log.d("BookingFragment2", "$mobileNumber")
            Log.d("BookingFragment2", "$fullAddress")
            Log.d("BookingFragment2", "$arrivalCity")
            Log.d("BookingFragment2", "$addReq")

//            listener?.sendData(
//                "razorpay",
//                razorpayAmount,
//                yatraPackage = "$packageName - $packageDays",
//                yatraDate = selectedDate,
//                totalYatri = totalNumberOfPersons
//            )
        }
        return true
    }

    override fun onItemSelected(parent: AdapterView<*>?, p1: View?, position: Int, id: Long) {
        iDType = parent?.getItemAtPosition(position).toString()
    }

    override fun onNothingSelected(p0: AdapterView<*>?) {
        iDType = p0?.getItemAtPosition(0).toString()
    }

}