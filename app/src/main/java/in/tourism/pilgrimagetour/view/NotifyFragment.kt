package `in`.tourism.pilgrimagetour.view

import `in`.tourism.pilgrimagetour.databinding.FragmentNotifyBinding
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FirebaseFirestore
import `in`.tourism.pilgrimagetour.adapter.NotifyFragmentAdapter
import `in`.tourism.pilgrimagetour.SliderBanner

class NotifyFragment : Fragment() {

    private lateinit var binding: FragmentNotifyBinding
    private lateinit var offersList : ArrayList<SliderBanner?>
    private lateinit var offersbannerCollection: CollectionReference

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentNotifyBinding.inflate(layoutInflater)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

//        val info =
//            (requireContext().getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager).activeNetworkInfo
//
//        if (info == null) {
//
//            binding.lLLoading.visibility = View.GONE
//
//            Toast.makeText(context, "No Internet Connection", Toast.LENGTH_LONG)
//                .show()
//
//            binding.iVNoInt.visibility = View.VISIBLE
//        } else {
//
//            binding.iVNoInt.visibility = View.GONE
//
//            val cookieManager: CookieManager = CookieManager.getInstance()
//
//            cookieManager.setAcceptCookie(true)
////            cookieManager.setAcceptThirdPartyCookies(binding.wV, true)
//            binding.wV.clearCache(true)
//            binding.wV.settings.javaScriptEnabled = true
//            binding.wV.loadUrl("https://www.pilgrimagetour.in/packages/mob/offer/c1.php")
//            binding.wV.settings.setSupportMultipleWindows(true)
//            binding.wV.webChromeClient = object : WebChromeClient() {
//                override fun onCreateWindow(
//                    view: WebView, dialog: Boolean, userGesture: Boolean, resultMsg: Message
//                ): Boolean {
//                    val newWebView = WebView(requireContext())
//
//                    newWebView.webViewClient = object : WebViewClient() {
//
//                        override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
//
////                            findNavController().navigate(EnquiryFragmentDirections.actionEnquiryFragmentToPopupFragment(url!!))
//                        }
//                    }
//                    val transport: WebView.WebViewTransport =
//                        resultMsg.obj as WebView.WebViewTransport
//
//                    transport.webView = newWebView
//
//                    resultMsg.sendToTarget()
//
//                    return true
//                }
//            }
//            binding.wV.webViewClient = MyWebViewClient()
//        }
//        binding.iVNoInt.setOnClickListener {
//
//            MenuFragment.buttonCalls(this)
//        }
//        binding.eFABPlanHoliday.setOnClickListener {
//
//            HomeFragment.navigatesFragment(
//                this,
//                "e",
//                "https://www.pilgrimagetour.in/packages/mob/m-query.php?c=offers"
//            )
//        }
//        binding.fABWhatsapp.setOnClickListener {
//
//            HomeFragment.buttonWhatsApp(this)
//        }
//    }
//
//    inner class MyWebViewClient : WebViewClient() {
//
//        override fun shouldOverrideUrlLoading(view: WebView?, url: String?): Boolean {
//
//            if (Uri.parse(url).host == "www.pilgrimagetour.in") {
//
//                return false
//            }
//            view?.context?.startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(url)))
//
//            return true
//        }
//
//        override fun onPageFinished(view: WebView?, url: String?) {
//            super.onPageFinished(view, url)
//
//            binding.lLLoading.visibility = View.GONE
//            binding.wV.visibility = View.VISIBLE
//        }
//    }

        offersList = ArrayList()
        offersbannerCollection = FirebaseFirestore.getInstance().collection("offersBanner")
        if (offersList.size == 0){
            offersbannerCollection.addSnapshotListener{ value , _->
                if (value != null) {

                    val documentList = value.documents

                    Log.d("documentList", "onViewCreated: ${documentList.size}")

                    offersList = ArrayList()
                    if (documentList.isNotEmpty()) {
                        for (document in documentList) {
                            (offersList as java.util.ArrayList<SliderBanner?>).add(
                                document.toObject(
                                    SliderBanner::class.java
                                )
                            )
                        }
                    }

                    try {
                        Log.d("offersList", "onViewCreated: ${offersList.size}")
                        binding.rVbanner.layoutManager = LinearLayoutManager(requireContext() )
                        binding.rVbanner.adapter = NotifyFragmentAdapter(activity = (activity as MainActivity), offersList)
                    } catch (e: java.lang.Exception){
                        Log.d("MainActivity", "onViewCreated: ${e.message}")
                    }
//                binding.cL.setOnClickListener { (activity as MainActivity).selectTab(1) }
                }

            }
        }
    }
}
