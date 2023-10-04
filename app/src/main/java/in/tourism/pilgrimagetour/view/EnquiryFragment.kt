package `in`.tourism.pilgrimagetour.view

import `in`.tourism.pilgrimagetour.databinding.FragmentEnquiryBinding
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.net.ConnectivityManager
import android.net.Uri
import android.os.Bundle
import android.os.Message
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.CookieManager
import android.webkit.WebChromeClient
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.Toast
import androidx.fragment.app.Fragment

class EnquiryFragment : Fragment() {

    private lateinit var binding: FragmentEnquiryBinding
    private var url: String = ""

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentEnquiryBinding.inflate(layoutInflater)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        try {

            url = arguments?.let { EnquiryFragmentArgs.fromBundle(it).name }.toString()
        } catch (_: Exception) {
        }
        val info =
            (requireContext().getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager).activeNetworkInfo

        if (info == null) {

            binding.lLLoading.visibility = View.GONE

            Toast.makeText(context, "No Internet Connection", Toast.LENGTH_LONG)
                .show()

            binding.iVNoInt.visibility = View.VISIBLE
        } else {

            binding.iVNoInt.visibility = View.GONE

            val cookieManager: CookieManager = CookieManager.getInstance()

            cookieManager.setAcceptCookie(true)

            binding.wV.clearCache(true)
            binding.wV.settings.javaScriptEnabled = true

            if (url == "") {
                url = arguments?.getString("url").toString()
            }

            if (url == "") {
                binding.wV.loadUrl("https://www.pilgrimagetour.in/")
//                binding.wV.loadUrl("https://www.chardhamtour.in/packages/mob/m-query.php?c=enquiry")
            } else {
                binding.wV.loadUrl(url)
            }

            binding.wV.settings.setSupportMultipleWindows(true)
            binding.wV.webChromeClient = object : WebChromeClient() {
                override fun onCreateWindow(
                    view: WebView, dialog: Boolean, userGesture: Boolean, resultMsg: Message
                ): Boolean {
                    val newWebView = WebView(requireContext())

                    newWebView.webViewClient = object : WebViewClient() {

                        override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {

//                            findNavController().navigate(EnquiryFragmentDirections.actionEnquiryFragmentToPopupFragment(url!!))
                        }
                    }
                    val transport: WebView.WebViewTransport =
                        resultMsg.obj as WebView.WebViewTransport

                    transport.webView = newWebView

                    resultMsg.sendToTarget()

                    return true
                }
            }
            binding.wV.webViewClient = MyWebViewClient()
        }
        binding.iVNoInt.setOnClickListener {

            MenuFragment.buttonCalls(this)
        }
    }

    inner class MyWebViewClient : WebViewClient() {

        override fun shouldOverrideUrlLoading(view: WebView?, url: String?): Boolean {

            if (Uri.parse(url).host == "www.pilgrimagetour.in") {

                return false
            }
            view?.context?.startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(url)))

            return true
        }

        override fun onPageFinished(view: WebView?, url: String?) {
            super.onPageFinished(view, url)

            binding.lLLoading.visibility = View.GONE
//            binding.sVFragmentEnquiry.visibility= View.VISIBLE
            binding.wV.visibility = View.VISIBLE
        }
    }
}
