package `in`.tourism.pilgrimagetour.view

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.addCallback
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import `in`.tourism.pilgrimagetour.FragmentInteractionListener
import `in`.tourism.pilgrimagetour.HotelDetails
import `in`.tourism.pilgrimagetour.adapter.HotelsListAdapter
import `in`.tourism.pilgrimagetour.databinding.FragmentHotelsListBinding
import `in`.tourism.pilgrimagetour.viewmodel.MainViewModel
import java.lang.Exception

class HotelsListFragment : Fragment() {

    private lateinit var binding: FragmentHotelsListBinding
    private var destination: String = ""
    private var hotelsListAdapter: HotelsListAdapter? = null
    private var hotelsList: ArrayList<HotelDetails?>? = null
    private val viewModel: MainViewModel by activityViewModels()
    private var listener: FragmentInteractionListener? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val callback = requireActivity().onBackPressedDispatcher.addCallback(this) {

            (activity as MainActivity).selectTab(0)
        }
        callback.isEnabled
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)

        if (context is FragmentInteractionListener) listener = context
        else throw IllegalArgumentException("$context must implement OnFragmentInteractionListener")

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentHotelsListBinding.inflate(layoutInflater)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        destination = viewModel.hotelDestination.value.toString()

        val db = FirebaseFirestore.getInstance()
        db.collection("hotelDetails").orderBy("discounted", Query.Direction.DESCENDING)
            .addSnapshotListener { value, error ->

                if (value != null) {

                    hotelsList = ArrayList()

                    val documentList = value.documents

                    if (documentList.isNotEmpty()) {
                        for (document in documentList) {
                            try {
                                (hotelsList as java.util.ArrayList<HotelDetails?>).add(
                                    document.toObject(
                                        HotelDetails::class.java
                                    )
                                )
                            } catch (e: Exception) {
                                Log.d("sliderBannersList", "onViewCreated: ${e.message}")
                            }
                        }
                        hotelsListAdapter = HotelsListAdapter(requireContext(), hotelsList)
                        binding.rVHotelsFragment.layoutManager =
                            LinearLayoutManager(requireContext())
                        binding.rVHotelsFragment.adapter = hotelsListAdapter

                        filter(destination, hotelsList)

                        hotelsListAdapter?.onItemClick = {


                        }
                    }

                }
            }
    }

    private fun filter(destination: String, allhotels: ArrayList<HotelDetails?>?) {

        val filteredList = ArrayList<HotelDetails?>()

        allhotels!!.filter {

            it!!.textDestination.lowercase().trim().contains(
                destination.lowercase().trim()
            )
        }.forEach {
            filteredList.add(it)
        }

        if (filteredList.isEmpty()) {

            binding.rVHotelsFragment.visibility = View.GONE
            binding.tVNoHotels.visibility = View.VISIBLE

        } else {
            binding.tVNoHotels.visibility = View.GONE
            hotelsListAdapter?.addData(filteredList)
        }

    }

}