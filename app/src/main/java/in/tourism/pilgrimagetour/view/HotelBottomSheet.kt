package `in`.tourism.pilgrimagetour.view

import android.R
import android.app.DatePickerDialog
import android.app.Dialog
import android.os.Bundle
import android.text.SpannableString
import android.text.style.UnderlineSpan
import android.util.Log
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewTreeObserver
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ScrollView
import android.widget.Toast
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase
import `in`.tourism.pilgrimagetour.HotelEnquiry
import `in`.tourism.pilgrimagetour.databinding.HotelBottomSheetBinding
import java.sql.Timestamp
import java.text.SimpleDateFormat
import java.util.*


class HotelBottomSheet(
    private var hotelName: String
) : BottomSheetDialogFragment(), AdapterView.OnItemSelectedListener {

    private lateinit var binding: HotelBottomSheetBinding
    private lateinit var calendar: Calendar
    private var hotel = ""
    private var checkIn = ""
    private var checkOut = ""
    private var yatriName = ""
    private var email = ""
    private var mobileNumber = ""
    private var numberOfAdults = ""
    private var numberOfChildren = ""
    private var additionalInformation = ""

    private var db = FirebaseFirestore.getInstance()
    private var hotelEnquiryCollection: CollectionReference? = null
    private var currentUser: FirebaseUser? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        binding = HotelBottomSheetBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog = super.onCreateDialog(savedInstanceState) as BottomSheetDialog
        dialog.setOnShowListener {

            val bottomSheet =
                dialog.findViewById<View>(com.google.android.material.R.id.design_bottom_sheet)

            BottomSheetBehavior.from(bottomSheet!!).state = BottomSheetBehavior.STATE_EXPANDED
        }
        return dialog
    }

    override fun onStart() {
        super.onStart()
        dialog?.also {
            val bottomSheet =
                dialog?.findViewById<View>(com.google.android.material.R.id.design_bottom_sheet)
            bottomSheet?.layoutParams?.height = ViewGroup.LayoutParams.WRAP_CONTENT
            val behavior = BottomSheetBehavior.from<View>(bottomSheet!!)
            val layout =
                binding.sVBottomCab as ScrollView //rootLayout is root of your fragment layout.
            layout.viewTreeObserver?.addOnGlobalLayoutListener(object :
                ViewTreeObserver.OnGlobalLayoutListener {
                override fun onGlobalLayout() {
                    try {
                        layout.viewTreeObserver.removeGlobalOnLayoutListener(this)
                        behavior.peekHeight = layout.height
                        view?.requestLayout()
                    } catch (e: Exception) {
                    }
                }
            })
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        currentUser = Firebase.auth.currentUser
        hotelEnquiryCollection = db.collection("hotelEnquiry")

        binding.tIETBottom1HotelName.setText(hotelName)
        binding.tIETBottom2Name.setText(currentUser?.displayName)
        binding.tIETBottom3email.setText(currentUser?.email)
        binding.tIETBottom4mobile.setText(currentUser?.phoneNumber)

        calendar = Calendar.getInstance()

        val closeText = SpannableString("Close")

        closeText.setSpan(UnderlineSpan(), 0, closeText.length, 0)

        binding.tVClose.text = closeText
        binding.tVClose.setOnClickListener {
            dialog?.dismiss()
        }

        val scrollText = SpannableString("Scroll To Top")

        scrollText.setSpan(UnderlineSpan(), 0, scrollText.length, 0)

        binding.tVScroll.text = scrollText
        binding.tVScroll.setOnClickListener {
            binding.sVBottomCab.scrollTo(0, binding.tVClose.top)
        }


        binding.tIETCheckOut.setOnClickListener {
            showDatePicker(binding.tIETCheckOut)
        }
        binding.tIETCheckIn.setOnClickListener {
            showDatePicker(binding.tIETCheckIn)
        }

        binding.iVArrow1.setOnClickListener { binding.tIETBottom6Adults.performClick() }
        binding.iVArrow2.setOnClickListener { binding.tIETBottom6Child.performClick() }

        binding.tIETBottom6Adults.onItemSelectedListener = this
        binding.tIETBottom6Child.onItemSelectedListener =
            object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                    numberOfChildren = p0?.getItemAtPosition(p2).toString()
                }

                override fun onNothingSelected(p0: AdapterView<*>?) {
                    numberOfAdults = p0?.getItemAtPosition(0).toString()
                }

            }

        val adultArray = arrayOf("1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12")
        val childrenArray =
            arrayOf("0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12")
        val adultsAdapter = ArrayAdapter(requireContext(), R.layout.simple_spinner_item, adultArray)

        adultsAdapter.setDropDownViewResource(R.layout.simple_spinner_dropdown_item)

        val childrenAdapter =
            ArrayAdapter(requireContext(), R.layout.simple_spinner_item, childrenArray)

        childrenAdapter.setDropDownViewResource(R.layout.simple_spinner_dropdown_item)

        binding.tIETBottom6Adults.adapter = adultsAdapter
        binding.tIETBottom6Child.adapter = childrenAdapter


        binding.buttonBottomSubmitEnquiry.setOnClickListener {


            hotel = binding.tIETBottom1HotelName.text.toString().trim()

            checkIn = binding.tIETCheckIn.text.toString().trim()

            checkOut = binding.tIETCheckOut.text.toString().trim()

            yatriName = binding.tIETBottom2Name.text.toString().trim()

            email = binding.tIETBottom3email.text.toString().trim()

            mobileNumber = binding.tIETBottom4mobile.text.toString().trim()

            additionalInformation = binding.tIETBottom7additional.text.toString().trim()

            val error = "Please enter Valid Text"

            if (hotel == "") {
                binding.tIETBottom1HotelName.error = error
                binding.tIETBottom1HotelName.requestFocus()
            } else if (checkIn == "") {
                binding.tIETCheckIn.error = error
                binding.tIETCheckIn.requestFocus()
            } else if (checkOut == "") {
                binding.tIETCheckOut.error = error
                binding.tIETCheckOut.requestFocus()
            } else if (yatriName == "") {
                binding.tIETBottom2Name.error = error
                binding.tIETBottom2Name.requestFocus()
            } else if (email == "") {
                binding.tIETBottom3email.error = error
                binding.tIETBottom3email.requestFocus()
            } else if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                binding.tIETBottom3email.error = "Please enter Valid Email"
                binding.tIETBottom3email.requestFocus()
            } else if (mobileNumber.length != 10) {
                binding.tIETBottom4mobile.error = "Enter valid mobile number"
                binding.tIETBottom4mobile.requestFocus()
            } else if (checkIn == "") {
                binding.tIETCheckIn.error = "Enter Check In Date"
                binding.tIETCheckIn.requestFocus()
            } else if (checkOut == "") {
                binding.tIETCheckOut.error = "Enter Check Out Date"
                binding.tIETCheckOut.requestFocus()
            } else {

                val hotelEnquiry = HotelEnquiry(
                    com.google.firebase.Timestamp(
                        Date(
                            Timestamp(
                                System.currentTimeMillis()
                            ).time
                        )
                    ),
                    hotel,
                    checkIn,
                    checkOut,
                    yatriName,
                    email,
                    mobileNumber,
                    numberOfAdults,
                    numberOfChildren,
                    additionalInformation
                )

                hotelEnquiryCollection?.add(hotelEnquiry)?.addOnSuccessListener {

                        Log.d(
                            "onSuccess", "DocumentSnapshot written with ID: " + it.id
                        )
                        Toast.makeText(
                            requireContext(), "Enquiry submitted successfully", Toast.LENGTH_LONG
                        ).show()
                        dismiss()
                    }?.addOnFailureListener { e ->
                        Log.d(
                            "onFailure", "Error in adding Document $e"
                        )
                    }
            }

        }

    }


    override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
        val item = p0?.getItemAtPosition(p2).toString()

        numberOfAdults = item


    }

    override fun onNothingSelected(p0: AdapterView<*>?) {
        numberOfAdults = p0?.getItemAtPosition(0).toString()
    }


    private fun showDatePicker(editText: TextInputEditText) {

        val dateFormat = SimpleDateFormat("MM/dd/yyyy", Locale.US)

        val datePicker = DatePickerDialog(
            requireContext(),
            DatePickerDialog.OnDateSetListener { view, year, month, dayOfMonth ->
                calendar.set(year, month, dayOfMonth)
                editText.setText(dateFormat.format(calendar.time))
            },
            calendar.get(Calendar.YEAR),
            calendar.get(Calendar.MONTH),
            calendar.get(Calendar.DAY_OF_MONTH)
        )
        datePicker.datePicker.minDate = System.currentTimeMillis() - 1000
        datePicker.show()
    }


}