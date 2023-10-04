package `in`.tourism.pilgrimagetour.view

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.os.Handler
import android.text.SpannableString
import android.text.style.UnderlineSpan
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.LinearInterpolator
import android.view.animation.TranslateAnimation
import android.widget.Toast
import androidx.activity.addCallback
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import com.squareup.picasso.Picasso
import `in`.tourism.pilgrimagetour.*
import `in`.tourism.pilgrimagetour.ResourceFactory.contactCollection
import `in`.tourism.pilgrimagetour.ResourceFactory.destCollection
import `in`.tourism.pilgrimagetour.ResourceFactory.packageCollection
import `in`.tourism.pilgrimagetour.adapter.*
import `in`.tourism.pilgrimagetour.databinding.FragmentHomeBinding
import `in`.tourism.pilgrimagetour.viewmodel.MainViewModel
import java.util.*
import kotlin.collections.ArrayList

class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding

    private var bestsellingPackagesAdapter: FBPackageAdapter? = null
    private var religiousPackagesAdapter: FBPackageAdapter? = null
    private var chardhamPackagesAdapter: FBPackageAdapter? = null
    private var contactInfo: ContactInfo? = null
    private val viewModel: MainViewModel by activityViewModels()
    private var listener: FragmentInteractionListener? = null
    private val db = FirebaseFirestore.getInstance()
    private lateinit var translateAnim: TranslateAnimation
    private var bannerSliderAdapter: BannerSliderAdapter? = null
    private lateinit var bannersList: ArrayList<SliderBanner?>
    private lateinit var handler: Handler
    private lateinit var handleR: Handler
    private var destinationAdapter: AdapterDes? = null
    private var carRentalPoster: String? = null
    private var reviewsSliderAdapter: ReviewsSliderAdapter? = null
    private lateinit var reviewList: ArrayList<ReviewsSliderVM>
    private lateinit var showAll: SpannableString
    private var hotelDestinationAdapter: HotelDestinationAdapter? = null
    private lateinit var hotelsDestinationsList: ArrayList<HotelsDest>
    private lateinit var hideText: SpannableString
    private lateinit var travelPacList: ArrayList<TravelPac?>
    private lateinit var destinationList: ArrayList<Destination2>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)



        requireActivity().onBackPressedDispatcher.addCallback(this) {

            (activity as MainActivity).finish()
        }.isEnabled
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(layoutInflater)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        translateAnim = TranslateAnimation(-50f, 25f, 0f, 0f)

        translateAnim.duration = 1000
        translateAnim.interpolator = LinearInterpolator()
        translateAnim.repeatCount = Animation.INFINITE
        translateAnim.repeatMode = Animation.REVERSE

        getFromVM()

        if (bannersList.size == 0) {

            db.collection("homeTopSlider").addSnapshotListener { value, error ->

                if (value != null) {

                    val documentList = value.documents
                    Log.d(TAG, "BannerList: ${documentList.size}")

                    if (documentList.isNotEmpty()) {
                        for (document in documentList) {

                            try {
                                bannersList.add(
                                    document.toObject(
                                        SliderBanner::class.java
                                    )
                                )

                            } catch (e: Exception) {
                                Log.d(TAG, "Banner: ${e.printStackTrace()}")
                            }
                        }

                        try {

                            bannerSliderAdapter = BannerSliderAdapter(requireContext(), bannersList)

                            binding.vPTop.adapter = bannerSliderAdapter
                        } catch (e: java.lang.Exception) {
                            Log.d(TAG, "bindViews: ${e.message}")
                        }

                        Timer().schedule(object : TimerTask() {

                            override fun run() {
                                handler.post {

                                    if (binding.vPTop.currentItem < bannersList.size - 1) {
                                        binding.vPTop.currentItem = binding.vPTop.currentItem + 1

                                    } else {
                                        binding.vPTop.currentItem = 0
                                    }

                                }
                            }
                        }, SliderBannerDuration, SliderBannerDuration)

                        binding.tlsliderDots.setupWithViewPager(binding.vPTop, true)

                        viewModel.setSliderBanners(bannersList)


                    }
                }
            }

        } else {
            try {

                bannerSliderAdapter = BannerSliderAdapter(requireContext(), bannersList)

                binding.vPTop.adapter = bannerSliderAdapter
            } catch (e: java.lang.Exception) {
                Log.d(TAG, "bindViews: ${e.message}")
            }

            Timer().schedule(object : TimerTask() {

                override fun run() {
                    handler.post {

                        if (binding.vPTop.currentItem < bannersList.size - 1) {
                            binding.vPTop.currentItem = binding.vPTop.currentItem + 1

                        } else {
                            binding.vPTop.currentItem = 0
                        }

                    }
                }
            }, SliderBannerDuration, SliderBannerDuration)

            binding.tlsliderDots.setupWithViewPager(binding.vPTop, true)
        }


        fetchContact()



        handler = Handler()
        handleR = Handler()

        var topSellingPackagesList: List<TravelPac?>?
        var religiousPackagesList: List<TravelPac?>?
        var chardhamPackagesList: List<TravelPac?>?

        if (travelPacList.size == 0) {

            packageCollection(FirebaseFirestore.getInstance()).orderBy(
                "discountedPrice", Query.Direction.DESCENDING
            ).addSnapshotListener { value, e ->

                if (value != null) {

                    val documentList = value.documents

                    Log.d(TAG, "DocumentList: ${documentList.size}")

                    travelPacList = ArrayList()

                    if (documentList.isNotEmpty()) {
                        for (document in documentList) {

                            try {
                                travelPacList.add(
                                    document.toObject(
                                        TravelPac::class.java
                                    )
                                )
                            } catch (e: Exception) {
                                Log.d(TAG, "TravelPac: ${e.message}")
                            }
                        }
                        bestsellingPackagesAdapter =
                            FBPackageAdapter(travelPacList, onCardClickListener)

                        religiousPackagesAdapter =
                            FBPackageAdapter(travelPacList, onCardClickListener)

                        chardhamPackagesAdapter =
                            FBPackageAdapter(travelPacList, onCardClickListener)

                        bindViews()

                        topSellingPackagesList = travelPacList
                        religiousPackagesList = travelPacList
                        chardhamPackagesList = travelPacList

                        bestsellingPackagesAdapter!!.addData(topSellingPackagesList)
                        bestsellingPackagesAdapter!!.notifyDataSetChanged()
                        filter("t", topSellingPackagesList)


                        religiousPackagesAdapter!!.addData(religiousPackagesList)
                        religiousPackagesAdapter!!.notifyDataSetChanged()
                        filter("r", religiousPackagesList)

                        chardhamPackagesAdapter!!.addData(chardhamPackagesList)
                        chardhamPackagesAdapter!!.notifyDataSetChanged()
                        filter("c", chardhamPackagesList)
                    }
                    viewModel.setPackageList(travelPacList)
                    Contact.setPackageList(travelPacList)

                    Timer().schedule(object : TimerTask() {
                        override fun run() {

                            handler.post {
                                makeVisible()
                            }

                        }

                    }, 1000)
                }
            }
        } else makeVisible()

        bestsellingPackagesAdapter = FBPackageAdapter(travelPacList, onCardClickListener)

        religiousPackagesAdapter = FBPackageAdapter(travelPacList, onCardClickListener)

        chardhamPackagesAdapter = FBPackageAdapter(travelPacList, onCardClickListener)

        topSellingPackagesList = travelPacList
        religiousPackagesList = travelPacList
        chardhamPackagesList = travelPacList
        bestsellingPackagesAdapter!!.addData(topSellingPackagesList)
        bestsellingPackagesAdapter!!.notifyDataSetChanged()
        filter("t", topSellingPackagesList)


        religiousPackagesAdapter!!.addData(religiousPackagesList)
        religiousPackagesAdapter!!.notifyDataSetChanged()
        filter("r", religiousPackagesList)

        chardhamPackagesAdapter!!.addData(chardhamPackagesList)
        chardhamPackagesAdapter!!.notifyDataSetChanged()
        filter("c", chardhamPackagesList)

        if (destinationList.size == 0) {
            destCollection(db).orderBy("position", Query.Direction.ASCENDING)
                .addSnapshotListener { value, _ ->

                    if (value != null) {
                        val documentList = value.documents
                        Log.d(TAG, "DocumentListDestination: ${documentList.size}")

                        if (documentList.isNotEmpty()) {
                            for (document in documentList) {

                                try {
                                    (destinationList as ArrayList<Destination2?>).add(
                                        document.toObject(
                                            Destination2::class.java
                                        )
                                    )
                                } catch (e: java.lang.Exception) {
                                    Log.d(TAG, "Destination: ${e.message}")
                                }
                            }

                            viewModel.setPackageDestinationsList(destinationList)

                            destinationAdapter = AdapterDes(destinationList)

                            binding.rVDestinations.adapter = destinationAdapter

                            destinationAdapter!!.onItemClick = { trending ->
                                navigatesFragment(this, "d", trending.textName)
                            }

                        }
                    }
                }
        } else {
            destinationAdapter = AdapterDes(destinationList)

            binding.rVDestinations.adapter = destinationAdapter
        }

        //car rental
        contactCollection(db).document("carRental").get().addOnCompleteListener { task ->

            if (task.isSuccessful) {

                val document = task.result
                Log.d(TAG, "CarRentalsDocument: ${document.data}")

                if (document.exists()) {

                    carRentalPoster = document["poster"].toString()

                    viewModel.setCarRental(carRentalPoster!!)

                    Picasso.get().load(carRentalPoster).into(binding.iVOutstationCabs)

                } else {
                    Log.d(TAG, "CarRental: No such Document")
                }
            } else {
                Log.d(TAG, "CarRental: " + task.exception.toString())
            }
        }

        reviewList = ArrayList()

        if (reviewList.size == 0) {

            db.collection("homeReviews").addSnapshotListener { value, error ->

                if (value != null) {
                    val documentList = value.documents
                    Log.d(TAG, "homeReviews: ${documentList.size}")

                    if (documentList.isNotEmpty()) {
                        for (document in documentList) {

                            try {
                                (reviewList as ArrayList<ReviewsSliderVM?>).add(
                                    document.toObject(ReviewsSliderVM::class.java)!!
                                )
                            } catch (e: java.lang.Exception) {
                                Log.d(TAG, "homeReviews: ${e.message}")
                            }
                            Log.d(TAG, "homeReviews: $reviewList")
                        }

                        reviewsSliderAdapter = ReviewsSliderAdapter(requireContext(), reviewList)

                        bindViews()
                    }

                }
            }

        }


        //hotels

        showAll = SpannableString("Show All")
        showAll.setSpan(UnderlineSpan(), 0, showAll.length, 0)

        hideText = SpannableString("Hide")
        hideText.setSpan(UnderlineSpan(), 0, hideText.length, 0)


        hotelsDestinationsList = ArrayList()

        if (hotelsDestinationsList.size == 0) {
            db.collection("hotelDestination").addSnapshotListener { value, _ ->

                if (value != null) {
                    val documentList = value.documents
                    Log.d(TAG, "HotelsDestination: ${documentList.size}")

                    if (documentList.isNotEmpty()) {
                        for (document in documentList) {

                            try {
                                (hotelsDestinationsList as ArrayList<HotelsDest?>).add(
                                    document.toObject(
                                        HotelsDest::class.java
                                    )
                                )

                            } catch (e: java.lang.Exception) {
                                Log.d(TAG, "HotelDestination: ${e.message}")
                            }
                            Log.d(TAG, "HotelDestination: for $hotelsDestinationsList")
                        }

                        hotelDestinationAdapter =
                            HotelDestinationAdapter(hotelsDestinationsList, limited = true)

                        bindViews()

                        hotelDestinationAdapter!!.onItemClick = {
                            viewModel.setHotelDestination(it.name)
                            findNavController().navigate(
                                HomeFragmentDirections.actionHomeFragmentToHotelsListFragment()
                            )
                        }
                    }
                }
            }
        }

        bindViews()

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

            if (detailed == false || travelPac?.daysList == null) {

                listener?.sendData(
                    fragment = "homeFragment", cab = "${travelPac?.name!!}- ${travelPac.days!!}"
                )

            } else startActivity(detailsIntent)
        }

    }

    companion object {

        const val reviewsSliderDuration: Long = 5 * 1000
        const val SliderBannerDuration: Long = 3 * 1000
        const val TAG = "HomeFragTAG"

        fun navigatesFragment(fragment: Fragment, fragmentString: String, string: String) {

            (fragment.activity as MainActivity).selectTab(1)

            when (fragmentString) {
                "d" -> {
                    fragment.findNavController().navigate(
                        PackagesFragmentDirections.actionPackagesFragmentToDestinationsFragment(
                            string
                        )
                    )
                }
                "i" -> {
                    fragment.findNavController().navigate(
                        PackagesFragmentDirections.actionPackagesFragmentToDestinationsFragment(
                            ism = string, name = ""
                        )
                    )
                }
                else -> {
                    fragment.findNavController().navigate(
                        PackagesFragmentDirections.actionPackagesFragmentToEnquiryFragment(
                            string
                        )
                    )
                }
            }
        }

        fun buttonWhatsApp(fragment: Fragment) {

            MenuFragment.buttonVibrates(fragment)

//           val url = "https://api.whatsapp.com/send?phone=+917838908606"

            val url = Contact.contactUrl
            val intent = Intent(Intent.ACTION_VIEW)

            intent.type = "text/plain"
            intent.putExtra(Intent.EXTRA_TEXT, "Hi, please tell me more about...")
            intent.data = Uri.parse(url)

            fragment.startActivity(intent)
        }
    }

    private fun fetchContact() {

        contactCollection(db).document("Contact1.0").get().addOnCompleteListener { task ->

            if (task.isSuccessful) {

                val document = task.result
                Log.d(TAG, "MenuFragmentDocument: ${document.data}")

                if (document.exists()) {
                    contactInfo = document.toObject(ContactInfo::class.java)
                    contactInfo.let {
                        Contact.setNumber(it?.phoneNumber)
                        Contact.setUrl(it?.whatsappLink)
                        Contact.setMail(it?.email)
                        Contact.setOfficeAddress(it?.address)

                        binding.tVPhoneNumber.text = Contact.phoneNumber

                    }
                } else {
                    Log.d(TAG, "No such Document")
                }
            } else {
                Log.d(TAG, task.exception.toString())
            }
        }
    }

    private fun filter(text: String, packagesList: List<TravelPac?>?) {
        val filteredList: ArrayList<TravelPac?> = ArrayList()

        when (text) {

            "t" -> {
                packagesList?.filter {
                    Log.d(TAG, "Filter: " + it?.topSelling.toString())
                    it?.topSelling?.trim().equals("t")
                }?.forEach { filteredList.add(it) }

                bestsellingPackagesAdapter?.addData(filteredList)

            }

            "r" -> {
                packagesList?.filter {
                    it?.religious?.equals("r") == true
                }?.forEach { filteredList.add(it) }

                religiousPackagesAdapter?.addData(filteredList)
            }

            "c" -> {
                packagesList?.filter {
                    it?.chardham?.equals("c") == true
                }?.forEach { filteredList.add(it) }

                chardhamPackagesAdapter?.addData(filteredList)
            }

        }

    }

    private fun makeVisible() {

        binding.iVLoading.visibility = View.GONE
        binding.pBLoading.visibility = View.GONE
        binding.sVFragmentHome.visibility = View.VISIBLE
        binding.eFABPlanHoliday.visibility = View.VISIBLE
        binding.fABWhatsapp.visibility = View.VISIBLE

    }

    private fun bindViews() {

        binding.iVRight1.startAnimation(translateAnim)
        binding.iVRight2.startAnimation(translateAnim)
        binding.iVRight3.startAnimation(translateAnim)
        binding.iVRight4.startAnimation(translateAnim)

        binding.rVBestsellingPackages.adapter = bestsellingPackagesAdapter
        binding.rVReligiouspackages.adapter = religiousPackagesAdapter
        binding.rVChardhamPackages.adapter = chardhamPackagesAdapter

        binding.rVDestinations.adapter = destinationAdapter

        Picasso.get().load(carRentalPoster).into(binding.iVOutstationCabs)

        binding.mCVOutstationCabs.setOnClickListener {
            findNavController().navigate(
                HomeFragmentDirections.actionHomeFragmentToCabsFragment()
            )
        }

        binding.mCVCallHelp.setOnClickListener {

            MenuFragment.buttonVibrates(this)
            MenuFragment.buttonCalls(this)
        }
        binding.mCVChatWhatsapp.setOnClickListener {

            buttonWhatsApp(this)

        }

        binding.vPReviews.adapter = reviewsSliderAdapter

        Timer().schedule(object : TimerTask() {
            override fun run() {

                handleR.post {

                    if (binding.vPReviews.currentItem < reviewList.size - 1) {

                        binding.vPReviews.currentItem = binding.vPReviews.currentItem + 1
                    } else {
                        binding.vPReviews.currentItem = 0
                    }
                }
            }
        }, reviewsSliderDuration, reviewsSliderDuration)

        binding.tVShowAllHotels.text = showAll

        binding.rVHotels.layoutManager = GridLayoutManager(requireContext(), 4)
        binding.rVHotels.adapter = hotelDestinationAdapter

        binding.tVShowAllHotels.setOnClickListener {

            if (binding.tVShowAllHotels.text == showAll) {
                val hotelDestinationAdapter =
                    HotelDestinationAdapter(hotelsDestinationsList, limited = false)
                binding.rVHotels.adapter = hotelDestinationAdapter
                binding.tVShowAllHotels.text = hideText

                hotelDestinationAdapter.onItemClick = {
                    viewModel.setHotelDestination(it.name)

                    findNavController().navigate(
                        HomeFragmentDirections.actionHomeFragmentToHotelsListFragment()
                    )
                }
            } else {
                val hotelDestinationAdapter =
                    HotelDestinationAdapter(hotelsDestinationsList, limited = true)
                binding.rVHotels.adapter = hotelDestinationAdapter
                binding.tVShowAllHotels.text = showAll

                hotelDestinationAdapter.onItemClick = {
                    viewModel.setHotelDestination(it.name)

                    findNavController().navigate(
                        HomeFragmentDirections.actionHomeFragmentToHotelsListFragment()
                    )
                    Toast.makeText(requireContext(), it.name, Toast.LENGTH_SHORT).show()
                }
            }
        }
        binding.eFABPlanHoliday.setOnClickListener {

            listener?.sendData(fragment = "homeFragment", cab = "")
        }
        binding.fABWhatsapp.setOnClickListener {

            buttonWhatsApp(this)
        }

        binding.tVPhoneNumber.text = Contact.phoneNumber

    }

    private fun getFromVM() {

        bannersList = viewModel.homeTopSliderBannersList

        destinationList = viewModel.packagesDestinationsList

        travelPacList = viewModel.packagesList

    }

}
