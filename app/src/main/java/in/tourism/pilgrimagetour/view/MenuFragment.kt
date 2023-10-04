package `in`.tourism.pilgrimagetour.view

import android.app.Activity.RESULT_OK
import `in`.tourism.pilgrimagetour.databinding.FragmentMenuBinding
import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.os.VibrationEffect
import android.os.VibratorManager
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.addCallback
import androidx.appcompat.app.AlertDialog
import androidx.core.content.ContextCompat
import com.firebase.ui.auth.AuthUI
import com.firebase.ui.auth.FirebaseAuthUIActivityResultContract
import com.firebase.ui.auth.data.model.FirebaseAuthUIAuthenticationResult
import com.google.android.gms.common.api.ApiException
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase
import com.squareup.picasso.Picasso
import `in`.tourism.pilgrimagetour.Contact
import `in`.tourism.pilgrimagetour.FragmentInteractionListener
import `in`.tourism.pilgrimagetour.R
import `in`.tourism.pilgrimagetour.User
import java.lang.Exception
import java.sql.Timestamp
import java.util.*

class MenuFragment : Fragment() {

    private lateinit var binding: FragmentMenuBinding
    private var db = FirebaseFirestore.getInstance()
    private var bottomSheetEnquiry: BottomSheetFragment? = null
    private val signInLauncher =
        registerForActivityResult(FirebaseAuthUIActivityResultContract()) { res ->
            this.onSignInResult(res)
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        requireActivity().onBackPressedDispatcher.addCallback(this) {

            (activity as MainActivity).selectTab(0)
        }.isEnabled
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentMenuBinding.inflate(layoutInflater)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.tVTFNumber.text = Contact.phoneNumber
        binding.tVWANumber.text = Contact.phoneNumber
        binding.tVEmailID.text = Contact.email
        binding.tVOfficeAddress.text = Contact.address

        val currentUser = Firebase.auth.currentUser
        val playStore = "https://play.google.com/store/apps/details?id=in.tourism.pilgrimagetour"

        if (currentUser != null) {

            binding.lLLogIn.visibility = View.GONE
            binding.lLUser.visibility = View.VISIBLE
            Picasso.get().load(currentUser.photoUrl).placeholder(R.drawable.avatar)
                .into(binding.sIVUser)

            binding.tVUserName.text = currentUser.displayName
            binding.tVUserEmail.text = currentUser.email
        }

        binding.rLCall.setOnClickListener {

            buttonVibrates(this)
            buttonCalls(this)
        }
        binding.lLEmail.setOnClickListener {

            buttonVibrates(this)

            /* val intent = Intent(Intent.ACTION_SEND)

             intent.putExtra(Intent.EXTRA_EMAIL, Contact.email)
             intent.putExtra(Intent.EXTRA_SUBJECT, "From PilgrimageTour App:")
             intent.putExtra(Intent.EXTRA_TEXT, "Hi! I am planning a Pilgrimage. Can you help me?")*/
//            intent.type = "*/*"

//            startActivity(Intent.createChooser(intent, "Choose:"))

            val intent = Intent(Intent.ACTION_VIEW)
            val data = Uri.parse(
                "mailto:${Contact.email}?subject=" + Uri.encode("From PilgrimageTour App:") + "&body=" + Uri.encode(
                    "Hi! I am planning a Pilgrimage tour. Can you help me?"
                )
            )

            intent.data = data

            startActivity(intent)
        }
        binding.lLRate.setOnClickListener {

            buttonVibrates(this)

            try {
                startActivity(
                    Intent(
                        Intent.ACTION_VIEW,
                        Uri.parse("market://details?id=in.tourism.pilgrimagetour")
                    )
                )
            } catch (e: ActivityNotFoundException) {
                startActivity(
                    Intent(
                        Intent.ACTION_VIEW, Uri.parse(playStore)
                    )
                )
            }
        }
        binding.lLShare.setOnClickListener {

            buttonVibrates(this)

            try {

                val intent = Intent(Intent.ACTION_SEND)

                intent.type = "text/plain"
                intent.putExtra(Intent.EXTRA_SUBJECT, "Sharing PilgrimageTour App:")
                intent.putExtra(
                    Intent.EXTRA_TEXT, "Check out PilgrimageTour app:- $playStore"
                )

                startActivity(Intent.createChooser(intent, "Choose"))
            } catch (e: Exception) {
                Log.d("TAG", "onViewCreated: ${e.message}")
            }
        }
        binding.iVFacebook.setOnClickListener {

            buttonVibrates(this)

            val intent = Intent(
                Intent.ACTION_VIEW, Uri.parse("https://www.facebook.com/PilgrimageTourism/")
            )

            intent.setPackage("com.facebook.katana")

            try {

                startActivity(intent)

            } catch (e: Exception) {

                startActivity(
                    Intent(
                        Intent.ACTION_VIEW, Uri.parse("https://www.facebook.com/PilgrimageTourism/")
                    )
                )
            }
        }
        binding.iVTwitter.setOnClickListener {

            buttonVibrates(this)

            val intent =
                Intent(Intent.ACTION_VIEW, Uri.parse("https://twitter.com/PilgrimageTrips/"))

            intent.setPackage("com.twitter.android")

            try {

                startActivity(intent)

            } catch (e: Exception) {
                startActivity(
                    Intent(
                        Intent.ACTION_VIEW, Uri.parse("https://twitter.com/PilgrimageTrips/")
                    )
                )
            }
        }
        binding.iVInstagram.setOnClickListener {

            buttonVibrates(this)

            val intent = Intent(
                Intent.ACTION_VIEW, Uri.parse("https://www.instagram.com/pilgrimagetourism/")
            )

            intent.setPackage("com.instagram.android")

            try {

                startActivity(intent)

            } catch (e: Exception) {
                startActivity(
                    Intent(
                        Intent.ACTION_VIEW,
                        Uri.parse("https://www.instagram.com/pilgrimagetourism/")
                    )
                )
            }
        }
        binding.iVYoutube.setOnClickListener {

            buttonVibrates(this)

            startActivity(
                Intent(
                    Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/@pilgrimagetour5136")
                )
            )
        }
        binding.rLWhatsapp.setOnClickListener {

            HomeFragment.buttonWhatsApp(this)
        }
        binding.tVPrivacy.setOnClickListener {

            opensUrl(this, "https://www.pilgrimagetour.in/privacy-policy/")
        }
        binding.tVTerms.setOnClickListener {

            opensUrl(this, "https://www.pilgrimagetour.in/terms-and-conditions/")
        }
        binding.tVWebsite.setOnClickListener {

            opensUrl(this, "https://www.pilgrimagetour.in/")
        }
        binding.rLEnquire.setOnClickListener {
            Log.d(
                "MenuFragment", "Enquiry Click"
            )
            bottomSheetEnquiry =
                BottomSheetFragment(packageNAme = "", enquiryFragment = "menuFragment")
            bottomSheetEnquiry?.show(requireFragmentManager(), bottomSheetEnquiry?.tag)

        }
        binding.lLLogIn.setOnClickListener {
            // Choose authentication providers
            val providers = arrayListOf(
                AuthUI.IdpConfig.EmailBuilder().build(), AuthUI.IdpConfig.GoogleBuilder().build()
            )

            // Create and launch sign-in intent
            val signInIntent =
                AuthUI.getInstance().createSignInIntentBuilder().setAvailableProviders(providers)
                    .build()
            signInLauncher.launch(signInIntent)
        }
        binding.tVLogOut.setOnClickListener {
            showLogoutDialog(requireContext())
        }
    }

    private fun showLogoutDialog(context: Context) {
        val builder = AlertDialog.Builder(context)
        builder.setMessage("Are you sure you want to Log Out?").setPositiveButton("Yes") { _, _ ->
            AuthUI.getInstance().signOut(requireContext()).addOnCompleteListener {
                val snackbar = Snackbar.make(
                    binding.root, "Logged Out Successfully", Snackbar.LENGTH_SHORT
                ).show()

                binding.lLUser.visibility = View.GONE
                binding.lLLogIn.visibility = View.VISIBLE
            }
        }.setNegativeButton("Cancel") { dialog, _ ->
            // User cancelled the Log Out action, dismiss the dialog
            dialog.cancel()
        }
        builder.create().show()
    }

    companion object {

        fun buttonVibrates(fragment: Fragment) {

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {

                val vibratorManager =
                    fragment.context?.getSystemService(Context.VIBRATOR_MANAGER_SERVICE) as VibratorManager
                val vibrator = vibratorManager.defaultVibrator

                vibrator.vibrate(
                    VibrationEffect.createOneShot(
                        100, VibrationEffect.DEFAULT_AMPLITUDE
                    )
                )
            }

        }

        fun buttonCalls(fragment: Fragment) {

            val intent = Intent(Intent.ACTION_DIAL)

            intent.data = Uri.parse("tel:${Contact.phoneNumber}")

            fragment.startActivity(intent)
        }

        fun opensUrl(fragment: Fragment, uriString: String) {

            buttonVibrates(fragment)

            val uri = Uri.parse(uriString)
            val intent = Intent(Intent.ACTION_VIEW, uri)

            fragment.startActivity(intent)
        }
    }

    private fun onSignInResult(result: FirebaseAuthUIAuthenticationResult) {
        val response = result.idpResponse

        if (result.resultCode == RESULT_OK) {
            val currentUser = FirebaseAuth.getInstance().currentUser

            try {
               /* val user = User(
                    currentUser?.email,
                    currentUser?.uid,
                    currentUser?.displayName,
                    currentUser?.photoUrl.toString()
                )
                db.collection("user").add(user).addOnSuccessListener { documentRefrence ->
                    Log.d(
                        "onSuccess", "DocumentSnapshot written with ID: " + documentRefrence.id
                    )

                    val snackbar =
                        Snackbar.make(binding.root, "Logged In Successfully", Snackbar.LENGTH_SHORT)
                            .show()

                    binding.tVUserName.text = currentUser!!.displayName
                    binding.tVUserEmail.text = currentUser.email
                    Picasso.get().load(currentUser!!.photoUrl).placeholder(R.drawable.avatar)
                        .into(binding.sIVUser)


                }.addOnFailureListener { e ->
                    Log.w(
                        "onFailure", "Error adding document", e
                    )
                }

                binding.llLogin.visibility = View.GONE
                binding.lLUser.visibility = View.VISIBLE*/
                val userCollection = db.collection("user")
                val queryUser = userCollection.whereEqualTo("id", currentUser?.uid)
                val sig = "signed in successfully"

                queryUser.get().addOnCompleteListener {
                    if (it.isSuccessful) {
                        if (!it.result.isEmpty) {

                            val user = User(
                                currentUser?.email,
                                currentUser?.uid,
                                currentUser?.displayName,
                                currentUser?.photoUrl.toString(),
                                timestamp = com.google.firebase.Timestamp(
                                    Date(
                                        Timestamp(
                                            System.currentTimeMillis()
                                        ).time
                                    )
                                )
                            )

                            userCollection.document(it.result.documents[0].id).set(user)
                                .addOnSuccessListener {
                                    Log.d(
                                        "user", "onSignInResult: Updated"
                                    )
                                    val snackBar = Snackbar.make(
                                        binding.root, sig, Snackbar.LENGTH_LONG
                                    )
                                    val snackbarView = snackBar.view

                                    snackbarView.setBackgroundColor(
                                        ContextCompat.getColor(
                                            requireContext(), R.color.green_whatsapp
                                        )
                                    )
                                    snackBar.show()

                                    Picasso.get().load(currentUser!!.photoUrl)
                                        .placeholder(R.drawable.avatar).into(binding.sIVUser)
                                    binding.tVUserName.text = currentUser.displayName
                                    binding.tVUserEmail.text = currentUser.email
                                    binding.lLLogIn.visibility = View.GONE
                                    binding.lLUser.visibility = View.VISIBLE
                                }.addOnFailureListener {
                                    Log.d("user", "onSignInResult: Failed")
                                }
                            Log.d("user", "user: Existing")
                        } else {
                            val user = User(
                                currentUser?.email,
                                currentUser?.uid,
                                currentUser?.displayName,
                                currentUser?.photoUrl.toString(),
                                timestamp = com.google.firebase.Timestamp(
                                    Date(
                                        Timestamp(
                                            System.currentTimeMillis()
                                        ).time
                                    )
                                )
                            )
                            userCollection.add(user).addOnSuccessListener {

                                val snackBar = Snackbar.make(
                                    binding.root, sig, Snackbar.LENGTH_LONG
                                )
                                val snackbarView = snackBar.view

                                snackbarView.setBackgroundColor(
                                    ContextCompat.getColor(
                                        requireContext(), R.color.green_whatsapp
                                    )
                                )
                                snackBar.show()

                                Picasso.get().load(currentUser!!.photoUrl)
                                    .placeholder(R.drawable.avatar).into(binding.sIVUser)
                                binding.tVUserName.text = currentUser.displayName
                                binding.tVUserEmail.text = currentUser.email
                            }.addOnFailureListener { e ->
                                Log.w(
                                    "onFailure", "Error adding document", e
                                )
                            }

                            binding.lLLogIn.visibility = View.GONE
                            binding.lLUser.visibility = View.VISIBLE
                        }
                    }
                }

            } catch (e: ApiException) {
                Log.w("GoogleSignIn", "signInResult:failed code=" + e.statusCode)
            }
        } else {
            binding.lLLogIn.visibility = View.VISIBLE
            binding.lLUser.visibility = View.GONE
        }
    }
}
