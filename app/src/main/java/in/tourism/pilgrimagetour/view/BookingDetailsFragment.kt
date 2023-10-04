package `in`.tourism.pilgrimagetour.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import `in`.tourism.pilgrimagetour.databinding.FragmentBookingDetailsBinding
import `in`.tourism.pilgrimagetour.viewmodel.BookingViewModel
import java.text.NumberFormat
import java.util.Currency
import java.util.Locale

class BookingDetailsFragment : Fragment() {


    private lateinit var binding: FragmentBookingDetailsBinding
    private val sharedViewModel: BookingViewModel by activityViewModels()
    private var numberFormat = NumberFormat.getCurrencyInstance(Locale("en","in"))

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentBookingDetailsBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        numberFormat.maximumFractionDigits = 0
        numberFormat.currency = Currency.getInstance("INR")

        val date = sharedViewModel.date.value!!
        val travelDate = "${date.dayOfMonth} ${date.month} , ${date.year}"

        val packageName = sharedViewModel.packageName.value
        val duration = sharedViewModel.packageDays.value
        val orderID = sharedViewModel.orderID.value
        val numberOfPerson = (sharedViewModel.numberofadults.value)?.plus((sharedViewModel.numberOfChildren.value)!!)

        val LeadTraveller = sharedViewModel.leadTraveller.value
        val IDType = sharedViewModel.iDType.value
        val leadEmail = sharedViewModel.orderID.value
        val mobileNumber = sharedViewModel.leadContact.value
        val fullAddress = sharedViewModel.fullAddress.value
        val addRequirement = sharedViewModel.additionalRequirement.value
        val paymentType = sharedViewModel.paymentType.value
        val userpayCost = sharedViewModel.userPayCost.value
        val amountDue = sharedViewModel.amountDueCost.value

        binding.tVOrderId.text = orderID
        binding.tVPackageName.text = packageName
        binding.tVDuration.text = duration
        binding.tVTravelDate.text = travelDate
        binding.tVnumberofPersons.text = numberOfPerson.toString()

        binding.tVLeadTraveller.text = LeadTraveller
        binding.tVLeadIdtype.text = IDType
        binding.tVLeadEmail.text = leadEmail
        binding.tVLeadMobile.text = mobileNumber

        if(fullAddress.isNullOrEmpty()){
            binding.tRAddress.visibility = View.GONE
        }

        if(addRequirement.isNullOrEmpty()){
            binding.tRRequiremnt.visibility = View.GONE
        }

        binding.tVLeadAddress.text = fullAddress
        binding.tVLeadAddReq.text = addRequirement

        binding.tVPaymentType.text = paymentType
        binding.tVLeadPayDone.text = numberFormat.format(userpayCost)
        binding.tVLeadPayDue.text = numberFormat.format(amountDue)

    }

}