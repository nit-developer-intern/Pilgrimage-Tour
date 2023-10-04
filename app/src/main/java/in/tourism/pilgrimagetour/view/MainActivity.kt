package `in`.tourism.pilgrimagetour.view

import `in`.tourism.pilgrimagetour.databinding.ActivityMainBinding
import android.content.Context
import android.content.pm.PackageManager
import android.content.res.Configuration
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.VibrationEffect
import android.os.VibratorManager
import android.util.Log
import android.widget.LinearLayout
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AlertDialog
import androidx.core.content.ContextCompat
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.tabs.TabLayout
import com.google.firebase.messaging.FirebaseMessaging
import `in`.tourism.pilgrimagetour.FragmentInteractionListener
import `in`.tourism.pilgrimagetour.R

class MainActivity : AppCompatActivity(), TabLayout.OnTabSelectedListener ,
    FragmentInteractionListener {

    private lateinit var binding: ActivityMainBinding
    private var PERMISSION_NOTIFICATION = true
    private var paramsLinear: LinearLayout.LayoutParams? = null
    private lateinit var navController: NavController
    private var detailsData: String? = null
    private var bottomSheetEnquiry: BottomSheetFragment? = null
    private var bottomCabSheetEnquiry: BottomCabSheetFragment? = null
    private var bottomHotelSheetEnquiry: HotelBottomSheet? = null

    // Declare the launcher at the top of your Activity/Fragment:
    private val requestPermissionLauncher = registerForActivityResult(
        ActivityResultContracts.RequestPermission(),
    ) { isGranted: Boolean ->
        if (isGranted) {
            // FCM SDK (and your app) can post notifications.
            PERMISSION_NOTIFICATION = true
            Log.d("Notification" , PERMISSION_NOTIFICATION.toString())
        } else {
            val snackBar= Snackbar.make(
                binding.root, "Notifications Denied", Snackbar.LENGTH_LONG
            )
            val snackbarView= snackBar.view
            snackbarView.setBackgroundColor(ContextCompat.getColor(this, R.color.red_theme))
            snackBar.show()
            PERMISSION_NOTIFICATION = false
            Log.d("Notification" , PERMISSION_NOTIFICATION.toString())
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding.root)

        FirebaseMessaging.getInstance().token.addOnCompleteListener { task ->
            if(task.isSuccessful){
                var token = task.result
                Log.d("Token" , token)
            }
        }

        askNotificationPermission()

        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.navHostFragment) as NavHostFragment

        navController = navHostFragment.navController

        binding.tabLayout.tabGravity = TabLayout.GRAVITY_CENTER
        binding.tabLayout.setOnTabSelectedListener(this)
        binding.tabLayout.getTabAt(0)?.setIcon(R.drawable.ic_home)
        binding.tabLayout.getTabAt(1)?.setIcon(R.drawable.ic_briefcase_packages)
        binding.tabLayout.getTabAt(2)?.setIcon(R.drawable.ic_search_glass)
        binding.tabLayout.getTabAt(3)?.setIcon(R.drawable.ic_offers)
        binding.tabLayout.getTabAt(4)?.setIcon(R.drawable.ic_more)

        for (i in 0..binding.tabLayout.tabCount) {

            paramsLinear =
                binding.tabLayout.getTabAt(i)?.view?.getChildAt(0)?.layoutParams as LinearLayout.LayoutParams?

            paramsLinear?.bottomMargin = 0
            paramsLinear?.width = 56
            paramsLinear?.height = 56

            binding.tabLayout.getTabAt(i)?.view?.getChildAt(0)?.layoutParams = paramsLinear
        }

        detailsData = intent?.getStringExtra("enquiryUrl")

        if (!detailsData.isNullOrBlank()) {
            navController.navigate(
                HomeFragmentDirections.actionHomeFragmentToEnquiryFragment(
                    detailsData!!
                )
            )
        }
    }

    override fun onTabSelected(tab: TabLayout.Tab?) {

        when (tab?.position) {

            0 -> {
                tab.setIcon(R.drawable.ic_home)

                adjustTabIcon(tab)

                navController.popBackStack()
                navController.navigate(R.id.homeFragment)
            }
            1 -> {
                tab.setIcon(R.drawable.ic_briefcase_packages)

                adjustTabIcon(tab)

                navController.popBackStack()
                navController.navigate(R.id.packagesFragment)
            }
            2 -> {
                tab.setIcon(R.drawable.ic_search_glass)

                adjustTabIcon(tab)

                navController.popBackStack()
                navController.navigate(R.id.searchFragment)
            }
            3 -> {
                tab.setIcon(R.drawable.ic_offers)

                adjustTabIcon(tab)

                navController.popBackStack()
                navController.navigate(R.id.notifyFragment)
            }
            4 -> {
                tab.setIcon(R.drawable.ic_more)

                adjustTabIcon(tab)

                navController.popBackStack()
                navController.navigate(R.id.menuFragment)
            }
        }
    }

    override fun onTabUnselected(tab: TabLayout.Tab?) {

        when (tab?.position) {

            0 -> {
                tab.setIcon(R.drawable.ic_home)

                adjustTabIcon(tab)
            }
            1 -> {
                tab.setIcon(R.drawable.ic_briefcase_packages)

                adjustTabIcon(tab)
            }
            2 -> {
                tab.setIcon(R.drawable.ic_search_glass)

                adjustTabIcon(tab)
            }
            3 -> {
                tab.setIcon(R.drawable.ic_offers)

                adjustTabIcon(tab)
            }
            4 -> {
                tab.setIcon(R.drawable.ic_more)

                adjustTabIcon(tab)
            }
        }
    }

    override fun onTabReselected(tab: TabLayout.Tab?) {

        when (tab?.position) {

            0 -> {
                navController.popBackStack()
                navController.navigate(R.id.homeFragment)
            }
            1 -> {
                navController.popBackStack()
                navController.navigate(R.id.packagesFragment)
            }
            2 -> {
                navController.popBackStack()
                navController.navigate(R.id.searchFragment)
            }
            3 -> {
                navController.popBackStack()
                navController.navigate(R.id.notifyFragment)
            }
            4 -> {
                navController.popBackStack()
                navController.navigate(R.id.menuFragment)
            }
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp() || super.onSupportNavigateUp()
    }

    override fun attachBaseContext(newBase: Context?) {

        val newOverride = Configuration(newBase?.resources?.configuration)
        newOverride.fontScale = 1.0f
        applyOverrideConfiguration(newOverride)

        super.attachBaseContext(newBase)
    }

    override fun onBackPressed() {
        super.onBackPressed()

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            val vibratorManager =
                getSystemService(Context.VIBRATOR_MANAGER_SERVICE) as VibratorManager
            val vibrator = vibratorManager.defaultVibrator

            vibrator.vibrate(VibrationEffect.createOneShot(100, VibrationEffect.DEFAULT_AMPLITUDE))
        }
    }

    private fun adjustTabIcon(tab: TabLayout.Tab) {

        paramsLinear = tab.view.getChildAt(0)?.layoutParams as LinearLayout.LayoutParams?
        paramsLinear?.bottomMargin = 0
        paramsLinear?.width = 56
        paramsLinear?.height = 56

        tab.view.getChildAt(0)?.layoutParams = paramsLinear
    }

    fun selectTab(id: Int) {

        when (id) {

            0 -> binding.tabLayout.getTabAt(0)?.select()
            1 -> {
                binding.tabLayout.getTabAt(1)?.select()
            }
            2 -> binding.tabLayout.getTabAt(2)?.select()
            3 -> binding.tabLayout.getTabAt(3)?.select()
            4 -> binding.tabLayout.getTabAt(4)?.select()
/*
            5 -> {
                binding.tabLayout.getTabAt(1)?.select()

                navController.navigate(
                    PackagesFragmentDirections.actionPackagesFragmentToEnquiryFragment(
                        "https://www.chardhamtour.in/packages/mob/m-query.php?n=Chardham%20Yatra%20by%20Helicopter-%202%20Days&c=m1"
                    )
                )
            }
*/
/*
            6 -> {
                binding.tabLayout.getTabAt(1)?.select()

                navController.navigate(
                    PackagesFragmentDirections.actionPackagesFragmentToEnquiryFragment(
                        "https://www.chardhamtour.in/packages/mob/m-query.php?n=Gangotri%20Yatra%20by%20Helicopter&c=m32"
                    )
                )
            }
*/
        }
    }

    private fun askNotificationPermission() {
        // This is only necessary for API level >= 33 (TIRAMISU)
        Log.d("Notification" , "askNotificationPermission() Entered")
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            if (ContextCompat.checkSelfPermission(
                    this,
                    android.Manifest.permission.POST_NOTIFICATIONS
                ) ==
                PackageManager.PERMISSION_GRANTED
            ) {
                // FCM SDK (and your app) can post notifications.
                Log.d("Notification" , "Permission Granted")
            } else if (shouldShowRequestPermissionRationale(android.Manifest.permission.POST_NOTIFICATIONS)) {
                // Create an AlertDialog.Builder
                val alertDialogBuilder = AlertDialog.Builder(this)
                alertDialogBuilder.setTitle("Alert Dialog")
                alertDialogBuilder.setMessage("This is an example alert dialog.")

                // Set the positive button (OK button)
                alertDialogBuilder.setPositiveButton("OK") { dialog, which ->
                    // Do something when OK button is clicked
                    requestPermissionLauncher.launch(android.Manifest.permission.POST_NOTIFICATIONS)
                }

                // Set the negative button (Cancel button)
                alertDialogBuilder.setNegativeButton("Cancel") { dialog, which ->
                    // Do something when Cancel button is clicked
                    dialog.dismiss()
                }

                // Create and show the AlertDialog
                val alertDialog = alertDialogBuilder.create()
                alertDialog.show()

            } else {
                // Directly ask for the permission
                requestPermissionLauncher.launch(android.Manifest.permission.POST_NOTIFICATIONS)
            }
        }
    }

    override fun sendData(fragment: String, cab: String) {
        when(fragment) {

            "cabsFragment" -> {
                bottomCabSheetEnquiry = BottomCabSheetFragment(modelName = cab)
                bottomCabSheetEnquiry?.show(supportFragmentManager, bottomCabSheetEnquiry?.tag)
            }

            "homeFragment" -> {
                bottomSheetEnquiry = BottomSheetFragment(packageNAme = cab , enquiryFragment = fragment)
                bottomSheetEnquiry?.show(supportFragmentManager , bottomSheetEnquiry?.tag)
            }

            "packagesFragment" -> {
                Log.d("bottomSheet" , fragment)
                bottomSheetEnquiry = BottomSheetFragment(packageNAme = cab , enquiryFragment = fragment)
                bottomSheetEnquiry?.show(supportFragmentManager , bottomSheetEnquiry?.tag)
            }
            "destinationsFragment" -> {
                bottomSheetEnquiry = BottomSheetFragment(packageNAme = cab , enquiryFragment = fragment)
                bottomSheetEnquiry?.show(supportFragmentManager , bottomSheetEnquiry?.tag)
            }
            "searchFragment" -> {
                bottomSheetEnquiry = BottomSheetFragment(packageNAme = cab , enquiryFragment = fragment)
                bottomSheetEnquiry?.show(supportFragmentManager , bottomSheetEnquiry?.tag)
            }
            "enquiryFragment" -> {
                Log.d("EnquiryFrag","Boottom Sheet")
                bottomSheetEnquiry = BottomSheetFragment(packageNAme = cab , enquiryFragment = fragment)
                bottomSheetEnquiry?.show(supportFragmentManager , bottomSheetEnquiry?.tag)
            }

            "hotelsListFragment" -> {
                bottomHotelSheetEnquiry = HotelBottomSheet(hotelName = cab)
                bottomHotelSheetEnquiry?.show(supportFragmentManager , bottomHotelSheetEnquiry?.tag)
            }
        }
    }

}
