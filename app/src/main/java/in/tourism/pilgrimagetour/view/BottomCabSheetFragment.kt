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
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase
import `in`.tourism.pilgrimagetour.EnquiryCab
import `in`.tourism.pilgrimagetour.databinding.BottomCabSheetBinding
import java.sql.Timestamp
import java.text.SimpleDateFormat
import java.util.*

class BottomCabSheetFragment(
    private val modelName: String
) : BottomSheetDialogFragment(), AdapterView.OnItemSelectedListener {

    private lateinit var binding: BottomCabSheetBinding
    private lateinit var calendar: Calendar
    private var cabModel = ""
    private var yatriName = ""
    private var email = ""
    private var mobileNumber = ""
    private var dateOfTravel = ""
    private var numberOfPersons = ""
    private var additionalInformation = ""
    private var destinationFrom = ""
    private var destinationTo = ""

    private var db = FirebaseFirestore.getInstance()
    private var cabEnquiryCollection: CollectionReference? = null
    private var currentUser: FirebaseUser? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {

        binding = BottomCabSheetBinding.inflate(layoutInflater)
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
        cabEnquiryCollection = db.collection("cabEnquiry")

        binding.tIETBottom1ModelName.setText(modelName)
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

        val date = DatePickerDialog.OnDateSetListener { _, year, month, day ->
            calendar.set(Calendar.YEAR, year)
            calendar.set(Calendar.MONTH, month)
            calendar.set(Calendar.DAY_OF_MONTH, day)

            val dateFormat = SimpleDateFormat("MM/dd/yyyy", Locale.US)
            binding.tIETBottom5date.setText(dateFormat.format(calendar.time))
        }

        binding.tIETBottom5date.setOnClickListener {
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

        binding.tIETBottom6people.onItemSelectedListener = this
        val numberOfPeople = arrayOf("1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12")
        val dataAdapter =
            ArrayAdapter(requireContext(), R.layout.simple_spinner_item, numberOfPeople)

        dataAdapter.setDropDownViewResource(R.layout.simple_spinner_dropdown_item)

        binding.tIETBottom6people.adapter = dataAdapter

        binding.iVArrow.setOnClickListener { binding.tIETBottom6people.performClick() }

        binding.buttonBottomSubmitEnquiry.setOnClickListener {

            cabModel = binding.tIETBottom1ModelName.text.toString().trim()

            yatriName = binding.tIETBottom2Name.text.toString().trim()

            email = binding.tIETBottom3email.text.toString().trim()

            mobileNumber = binding.tIETBottom4mobile.text.toString().trim()

            dateOfTravel = binding.tIETBottom5date.text.toString().trim()

            additionalInformation = binding.tIETBottom7additional.text.toString().trim()

            destinationFrom = binding.tIETDestinationFrom.text.toString().trim()
            destinationTo = binding.tIETDestinationTo.text.toString().trim()

            val error = "Please enter Valid Text"

            if (cabModel == "") {
                binding.tIETBottom1ModelName.error = error
                binding.tIETBottom1ModelName.requestFocus()
            } else if (destinationFrom == "") {
                binding.tIETDestinationFrom.error = error
                binding.tIETDestinationFrom.requestFocus()
            }
            else if (destinationTo == "") {
                binding.tIETDestinationTo.error = error
                binding.tIETDestinationTo.requestFocus()
            } else if (yatriName == "") {
                binding.tIETBottom2Name.error = error
                binding.tIETBottom2Name.requestFocus()
            }
            else if (email == "") {
                binding.tIETBottom3email.error = error
                binding.tIETBottom3email.requestFocus()
            }
            else if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                binding.tIETBottom3email.error = "Please enter Valid Email"
                binding.tIETBottom3email.requestFocus()
            }
            else if (mobileNumber.length < 10) {
                binding.tIETBottom4mobile.error = "Enter valid mobile number"
                binding.tIETBottom4mobile.requestFocus()
            } else if (dateOfTravel == "") {
                binding.tIETBottom5date.error = "Enter date of travel"
                binding.tIETBottom5date.requestFocus()
            } else {

                val cabEnquiry = EnquiryCab(
                    com.google.firebase.Timestamp(
                        Date(
                            Timestamp(
                                System.currentTimeMillis()
                            ).time
                        )
                    ),
                    cabModel,
                    destinationFrom,
                    destinationTo,
                    yatriName,
                    email,
                    mobileNumber,
                    dateOfTravel,
                    numberOfPersons,
                    additionalInformation
                )

                cabEnquiryCollection?.add(cabEnquiry)?.addOnSuccessListener {

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
        numberOfPersons = item
    }

    override fun onNothingSelected(p0: AdapterView<*>?) {
        numberOfPersons = p0?.getItemAtPosition(0).toString()
    }

}