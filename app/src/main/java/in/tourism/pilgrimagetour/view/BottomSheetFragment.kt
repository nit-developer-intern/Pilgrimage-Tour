package `in`.tourism.pilgrimagetour.view

import android.app.DatePickerDialog
import android.app.DatePickerDialog.OnDateSetListener
import android.app.Dialog
import android.os.Bundle
import android.text.SpannableString
import android.text.style.UnderlineSpan
import android.util.Log
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
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase
import `in`.tourism.pilgrimagetour.Contact
import `in`.tourism.pilgrimagetour.Enquiry
import `in`.tourism.pilgrimagetour.databinding.FragmentBottomSheetBinding
import java.sql.Timestamp
import java.text.SimpleDateFormat
import java.util.*

class BottomSheetFragment(private val packageNAme: String, val enquiryFragment: String) :
    BottomSheetDialogFragment(), AdapterView.OnItemSelectedListener {

    private lateinit var binding: FragmentBottomSheetBinding
    private lateinit var calendar: Calendar
    private var yatriName = ""
    private var destination = ""
    private var email = ""
    private var mobileNumber = ""
    private var dateofTravel = ""
    private var numberofPersons = ""
    private var additionalInfo = ""
    private var currentUser: FirebaseUser? = null
    private var db = FirebaseFirestore.getInstance()

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        var dialog = super.onCreateDialog(savedInstanceState) as BottomSheetDialog
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
                binding.bottomEnquiry as ScrollView //rootLayout is root of your fragment layout.
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

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentBottomSheetBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        currentUser = Firebase.auth.currentUser
        binding.tIETBottom1Destination.setText(packageNAme)
        binding.tIETBottom2Name.setText(currentUser?.displayName)
        binding.tIETBottom3email.setText(currentUser?.email)
        binding.tIETBottom4mobile.setText(currentUser?.phoneNumber)


        val closetext = SpannableString("Close")
        closetext.setSpan(UnderlineSpan(), 0, closetext.length, 0)

        binding.tVCall.text = Contact.phoneNumber
        binding.tVClose.text = closetext
        binding.tVClose.setOnClickListener {
            dialog?.dismiss()
        }

        val scrolltext = SpannableString("Scroll To Top")
        scrolltext.setSpan(UnderlineSpan(), 0, scrolltext.length, 0)

        binding.tVScroll.text = scrolltext
        binding.tVScroll.setOnClickListener {
            binding.bottomEnquiry.scrollTo(0, binding.tVClose.top)
        }

        calendar = Calendar.getInstance()

        val date = OnDateSetListener { _, year, monthOfYear, dayOfMonth ->

            calendar.set(Calendar.YEAR, year)
            calendar.set(Calendar.MONTH, monthOfYear)
            calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth)

            val dateFormat = SimpleDateFormat("dd/mm/yy", Locale.US)
            binding.tIETBottom5date.setText(dateFormat.format(calendar.time))
            binding.tIETBottom5date.error = null

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

        binding.iVArrow.setOnClickListener { binding.tIETBottom6people.performClick() }

        binding.tIETBottom6people.onItemSelectedListener = this
        val numberofperson = arrayOf("1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12")
        val adapter = ArrayAdapter(
            requireContext(),
            androidx.transition.R.layout.support_simple_spinner_dropdown_item,
            numberofperson
        )
        adapter.setDropDownViewResource(androidx.appcompat.R.layout.support_simple_spinner_dropdown_item)
        binding.tIETBottom6people.adapter = adapter

        binding.buttonBottomSubmitEnquiry.setOnClickListener {
            if (validateSheet() == 1) saveEnquiry()
        }

        binding.mCVContactPhone.setOnClickListener {
            MenuFragment.buttonVibrates(this)
            MenuFragment.buttonCalls(this)
        }

    }

    private fun saveEnquiry() {
        val enquiry = Enquiry(
            com.google.firebase.Timestamp(
                Date(
                    Timestamp(
                        System.currentTimeMillis()
                    ).time
                )
            ),
            destination,
            yatriName,
            email,
            mobileNumber,
            dateofTravel,
            numberofPersons,
            additionalInfo
        )

        db.collection("enquiry").add(enquiry).addOnSuccessListener {
            Log.d("Success", it.id.toString())

            Toast.makeText(
                requireContext(), "Enquiry submitted successfully", Toast.LENGTH_LONG
            ).show()

            dismiss()
        }.addOnFailureListener {
            Log.d("Failure", "Error in adding Document")
        }

    }

    private fun validateSheet(): Int {
        destination = binding.tIETBottom1Destination.text.toString().trim()

        yatriName = binding.tIETBottom2Name.text.toString().trim()

        email = binding.tIETBottom3email.text.toString().trim()

        mobileNumber = binding.tIETBottom4mobile.text.toString().trim()

        dateofTravel = binding.tIETBottom5date.text.toString().trim()

        additionalInfo = binding.tIETBottom7additional.text.toString().trim()

        if (destination == "") {

            binding.tIETBottom1Destination.error = "Please enter destination"
            binding.tIETBottom1Destination.requestFocus()
            binding.tVScroll.performClick()

        } else if (yatriName == "") {

            binding.tIETBottom2Name.error = "Please enter name"
            binding.tIETBottom2Name.requestFocus()

        } else if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {

            binding.tIETBottom3email.error = "Enter valid email"

        } else if (mobileNumber.length < 10) {

            binding.tIETBottom4mobile.error = "Enter valid mobile number"
            binding.tIETBottom4mobile.requestFocus()

        } else if (dateofTravel == "") {
            binding.tIETBottom5date.error = "Enter date of travel"
        } else {
            return 1
        }
        return 0
    }

    override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
        val item = p0?.getItemAtPosition(p2).toString()
        numberofPersons = item
    }

    override fun onNothingSelected(p0: AdapterView<*>?) {
        numberofPersons = p0?.getItemAtPosition(0).toString()
    }

}
