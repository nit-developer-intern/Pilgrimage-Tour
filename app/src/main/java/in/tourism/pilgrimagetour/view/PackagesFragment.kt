package `in`.tourism.pilgrimagetour.view

import android.content.Context
import `in`.tourism.pilgrimagetour.databinding.FragmentPackagesBinding
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.addCallback
import androidx.fragment.app.activityViewModels
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FirebaseFirestore
import `in`.tourism.pilgrimagetour.FragmentInteractionListener
import `in`.tourism.pilgrimagetour.OnCardClickListener2
import `in`.tourism.pilgrimagetour.TravelPac
import `in`.tourism.pilgrimagetour.adapter.FragmentPackagesAdapter
import `in`.tourism.pilgrimagetour.viewmodel.MainViewModel

class PackagesFragment : Fragment() {

    private lateinit var fragmentPackagesAdapter: FragmentPackagesAdapter
    private lateinit var binding: FragmentPackagesBinding
    private var db: FirebaseFirestore ?= null
    private lateinit var travelPacsList : ArrayList<TravelPac?>
    private var listener: FragmentInteractionListener? = null
    private val viewModel: MainViewModel by activityViewModels()
    private var show: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val callback = requireActivity().onBackPressedDispatcher.addCallback(this) {

            (activity as MainActivity).selectTab(0)
        }
        callback.isEnabled

        db = FirebaseFirestore.getInstance()

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentPackagesBinding.inflate(layoutInflater)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        travelPacsList = ArrayList()
        travelPacsList = viewModel.packagesList

        fragmentPackagesAdapter = FragmentPackagesAdapter(onCardClickListener, travelPacsList)
        binding.rVFragmentPackages.adapter = fragmentPackagesAdapter

        binding.eFABPlanHoliday.setOnClickListener {

            listener?.sendData(fragment = "homeFragment", cab = "")
        }
        binding.fABWhatsapp.setOnClickListener {
            HomeFragment.buttonWhatsApp(this)
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)

        if (context is FragmentInteractionListener) listener = context
        else throw IllegalArgumentException("$context must implement OnFragmentInteractionListener")
    }

    private val onCardClickListener = object : OnCardClickListener2 {

        override fun onClick(detailed: Boolean?, travelPac: TravelPac?) {
            val detailsIntent = Intent(requireContext(), PackageDetailsActivity::class.java)
            detailsIntent.putExtra("travelPac", travelPac)

            Log.d("sheet", "onClick: ${detailed.toString()}")

            if (detailed == false) {
                Log.d("listener", "onClick: ${detailed.toString()}")
                listener?.sendData(
                    fragment = "packagesFragment", cab = "${travelPac?.name!!}- ${travelPac.days!!}"
                )
            } else if (travelPac?.daysList?.isEmpty() == false) startActivity(detailsIntent)
        }
    }
}
