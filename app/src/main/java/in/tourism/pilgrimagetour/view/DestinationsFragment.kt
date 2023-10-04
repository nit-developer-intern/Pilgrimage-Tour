package `in`.tourism.pilgrimagetour.view

import android.content.Context
import `in`.tourism.pilgrimagetour.databinding.FragmentDestinationsBinding
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
import `in`.tourism.pilgrimagetour.*
import `in`.tourism.pilgrimagetour.adapter.FragmentPackagesAdapter
import `in`.tourism.pilgrimagetour.viewmodel.MainViewModel
import java.lang.Exception

class DestinationsFragment : Fragment() {

    private lateinit var binding: FragmentDestinationsBinding
    private lateinit var fragmentDestinationsAdapter: FragmentPackagesAdapter
    private var destination: String = ""
    private var ism: String = ""
    private var db : FirebaseFirestore?= null
    private var listener: FragmentInteractionListener?= null
    private var docId: String?= null
    private var travelPacsList: ArrayList<TravelPac?>? = null
    private val viewModel: MainViewModel by activityViewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        db = FirebaseFirestore.getInstance()

        val callback = requireActivity().onBackPressedDispatcher.addCallback(this) {

            (activity as MainActivity).selectTab(0)
        }
        callback.isEnabled
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentDestinationsBinding.inflate(layoutInflater)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        try {
            destination = arguments?.let { DestinationsFragmentArgs.fromBundle(it).name }.toString()
        }
        catch (_: Exception) {
        }

        try {
            ism = arguments?.let { DestinationsFragmentArgs.fromBundle(it).ism }.toString()
        } catch (_: Exception) {
        }

        val packagesData = PackagesList.addList()

        travelPacsList = ArrayList()
        travelPacsList = viewModel.packagesList

        Log.d("List" , travelPacsList.toString())
        fragmentDestinationsAdapter = FragmentPackagesAdapter(onCardClickListener,travelPacsList)
        binding.rVFragmentDestinations.adapter = fragmentDestinationsAdapter

        fragmentDestinationsAdapter?.addData(travelPacsList)
        fragmentDestinationsAdapter.notifyDataSetChanged()

            filter(destination, travelPacsList)
    }

    private fun filter(text: String, packagesData: ArrayList<TravelPac?>?) {

        val filteredList: ArrayList<TravelPac?> = ArrayList()
        Log.d("filterTravel" , text)
        Log.d("filterTravel" , "$packagesData")

        packagesData!!.filter {
            it!!.name!!.lowercase().trim().contains(text.lowercase().trim())
        }.forEach {
            filteredList.add(it)
        }
        Log.d("filter" , "${filteredList.size}")

        packagesData.filter {
            it?.daysList?.any { day ->
                day.placesList?.any { place ->
                    place.lowercase().contains(text.lowercase())
                }!!
            }!!
        }.forEach {
            if (!filteredList.contains(it)) filteredList.add(it)
        }

        if (filteredList.isEmpty()) {

            binding.rVFragmentDestinations.visibility = View.INVISIBLE
            binding.tV.visibility = View.VISIBLE
        } else {

            binding.tV.visibility = View.INVISIBLE
            fragmentDestinationsAdapter.addData(filteredList)
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

            Log.d("docId", "onClick: $docId")

            if (detailed == false) {
                listener?.sendData(
                    fragment = "destinationsFragment", cab = "${travelPac?.name!!}- ${travelPac.days!!}"
                )
            } else if (travelPac?.daysList?.isEmpty() == false) startActivity(detailsIntent)
        }
    }


}
