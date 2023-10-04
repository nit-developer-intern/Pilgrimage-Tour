package `in`.tourism.pilgrimagetour.view

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
import android.widget.Toast
import androidx.core.view.isVisible
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import `in`.tourism.pilgrimagetour.OnHotelListener
import `in`.tourism.pilgrimagetour.databinding.GuestsRoomsSheetBinding


class GuestsRoomsSheet : BottomSheetDialogFragment() , AdapterView.OnItemSelectedListener{

    private lateinit var binding: GuestsRoomsSheetBinding

    private var bed11: Boolean? = null
    private var bed12: Boolean? = null
    private var bed21: Boolean? = null
    private var bed22: Boolean? = null
    private var bed31: Boolean? = null
    private var bed32: Boolean? = null
    private var bed41: Boolean? = null
    private var bed42: Boolean? = null

    private lateinit var childrenBeds : ArrayList<String>
    private var listener : OnHotelListener?= null

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog = super.onCreateDialog(savedInstanceState) as BottomSheetDialog

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
            bottomSheet?.layoutParams?.height = ViewGroup.LayoutParams.MATCH_PARENT
            val behavior = BottomSheetBehavior.from<View>(bottomSheet!!)
            val layout =
                binding.sVGuestsRooms //rootLayout is root of your fragment layout.
            layout.viewTreeObserver?.addOnGlobalLayoutListener(object :
                ViewTreeObserver.OnGlobalLayoutListener {
                override fun onGlobalLayout() {
                    try {
                        layout.viewTreeObserver.removeGlobalOnLayoutListener(this)
                        behavior.peekHeight = layout.height
                        view?.requestLayout()
                    } catch (e: Exception) {
                        Log.d("TAG", "onGlobalLayout: ${e.message}")
                    }
                }
            })
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = GuestsRoomsSheetBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var numberOfRooms = 1

        var adults1 = 2
        var adults2 = 0
        var adults3 = 0
        var adults4 = 0

        var children1 = 0
        var children2 = 0
        var children3 = 0
        var children4 = 0

        val visibility = View.VISIBLE

        val scrollText = SpannableString("Scroll to Top")
        scrollText.setSpan(UnderlineSpan() , 0, scrollText.length, 0)
        binding.tVScroll.text = scrollText
        binding.tVScroll.setOnClickListener {
            binding.sVGuestsRooms.scrollTo(0 , binding.sVGuestsRooms.top)
        }

        childrenBeds = arrayListOf("Child with Bed" , "Child without Bed")

        val dataAdapter = ArrayAdapter(requireContext() , com.firebase.ui.auth.R.layout.support_simple_spinner_dropdown_item , childrenBeds)
        dataAdapter.setDropDownViewResource(com.firebase.ui.auth.R.layout.support_simple_spinner_dropdown_item)

        binding.spin11.onItemSelectedListener = this
        binding.spin11.adapter = dataAdapter
        binding.spin12.onItemSelectedListener = this
        binding.spin12.adapter = dataAdapter
        binding.spin21.onItemSelectedListener = this
        binding.spin21.adapter = dataAdapter
        binding.spin22.onItemSelectedListener = this
        binding.spin22.adapter = dataAdapter
        binding.spin31.onItemSelectedListener = this
        binding.spin31.adapter = dataAdapter
        binding.spin32.onItemSelectedListener = this
        binding.spin32.adapter = dataAdapter
        binding.spin41.onItemSelectedListener = this
        binding.spin41.adapter = dataAdapter
        binding.spin42.onItemSelectedListener = this
        binding.spin42.adapter = dataAdapter

        binding.mCVAdd.setOnClickListener {
            if(numberOfRooms < 4) numberOfRooms += 1

            if(binding.mCVRoom1.isVisible && !binding.mCVRoom2.isVisible){
                binding.mCVRoom2.visibility = View.VISIBLE
                binding.mCVRemove.visibility = View.VISIBLE

                adults2 = 2

                binding.tVAdults2.text = adults2.toString()
            }
            else if(binding.mCVRoom2.isVisible && !binding.mCVRoom3.isVisible){
                binding.mCVRoom3.visibility = View.VISIBLE

                adults3 = 2

                binding.tVAdults3.text = adults3.toString()
            }
            else if(binding.mCVRoom3.isVisible && !binding.mCVRoom4.isVisible){
                binding.mCVRoom4.visibility = View.VISIBLE
                binding.mCVAdd.visibility = View.GONE

                adults4 = 2

                binding.tVAdults4.text = adults4.toString()
            }

        }

        binding.mCVRemove.setOnClickListener {
            if (numberOfRooms > 1) numberOfRooms -= 1

            if(binding.mCVRoom4.isVisible){
                binding.mCVRoom4.visibility = View.GONE
                binding.mCVAdd.visibility = View.VISIBLE

                adults4 = 0
                children4 = 0
            }
            else if(binding.mCVRoom3.isVisible && !binding.mCVRoom4.isVisible){
                binding.mCVRoom3.visibility = View.GONE

                adults3 = 0
                children3 = 0
            }
            else if(binding.mCVRoom2.isVisible && !binding.mCVRoom3.isVisible){
                binding.mCVRoom2.visibility = View.GONE

                adults2 = 0
                children2 = 0

                binding.mCVRemove.visibility = View.GONE
            }

        }

        binding.texPlus1.setOnClickListener {
            if(adults1 < 3) adults1 += 1
            binding.tVAdults1.text = adults1.toString()
        }
        binding.texPlus2.setOnClickListener {
            if(adults2 < 3) adults2 += 1
            binding.tVAdults2.text = adults2.toString()
        }
        binding.texPlus3.setOnClickListener{
            if(adults3 < 3) adults3 += 1
            binding.tVAdults3.text = adults3.toString()
        }
        binding.texPlus4.setOnClickListener {
            if(adults4 < 3) adults4 += 1
            binding.tVAdults4.text = adults4.toString()
        }

        binding.texMinus1.setOnClickListener {
            if(adults1 > 1) adults1 -= 1

            binding.tVAdults1.text = adults1.toString()
        }
        binding.texMinus2.setOnClickListener {
            if(adults2 > 1) adults2 -= 1

            binding.tVAdults2.text = adults2.toString()
        }
        binding.texMinus3.setOnClickListener {
            if(adults3 > 1) adults3 -= 1

            binding.tVAdults3.text = adults3.toString()
        }
        binding.texMinus4.setOnClickListener {
            if(adults4 > 1) adults4 -= 1

            binding.tVAdults4.text = adults4.toString()
        }

        binding.texPlus1children.setOnClickListener {

            if(children1 < 2) children1 += 1
            if(children1 == 1){

                binding.lLRoom1sel.visibility = View.VISIBLE

            } else {

                binding.mCVBed12.visibility = View.VISIBLE
            }
            binding.tVChildren1.text = children1.toString()
        }
        binding.texPlus2children.setOnClickListener {

            if(children2 < 2) children2 += 1
            if(children2 == 1){

                binding.lLRoom2sel.visibility = View.VISIBLE

            } else {

                binding.mCVBed22.visibility = View.VISIBLE
            }

            binding.tVChildren2.text = children2.toString()
        }
        binding.texPlus3children.setOnClickListener {

            if(children3 < 2) children3 += 1
            if(children3 == 1){

                binding.lLRoom3sel.visibility = View.VISIBLE

            } else {

                binding.mCVBed32.visibility = View.VISIBLE
            }
            binding.tVChildren3.text = children3.toString()
        }
        binding.texPlus4children.setOnClickListener {

            if(children4 < 2) children4 += 1
            if(children4 == 1){

                binding.lLRoom4sel.visibility = View.VISIBLE

            } else {

                binding.mCVBed42.visibility = View.VISIBLE
            }
            binding.tVChildren4.text = children4.toString()
        }

        binding.texMinus1children.setOnClickListener {

            if(children1 > 0) children1 -= 1
            binding.tVChildren1.text = children1.toString()

            if(children1 == 1){
                binding.mCVBed12.visibility = View.GONE
            } else {
                binding.lLRoom1sel.visibility = View.GONE
            }
        }
        binding.texMinus2children.setOnClickListener {

            if(children2 > 0) children2 -= 1
            binding.tVChildren2.text = children2.toString()

            if(children2 == 1){
                binding.mCVBed22.visibility = View.GONE
            } else {
                binding.lLRoom2sel.visibility = View.GONE
            }
        }
        binding.texMinus3children.setOnClickListener {

            if(children3 > 0) children3 -= 1
            binding.tVChildren3.text = children3.toString()

            if(children3 == 1){
                binding.mCVBed32.visibility = View.GONE
            } else {
                binding.lLRoom3sel.visibility = View.GONE
            }
        }
        binding.texMinus4children.setOnClickListener {

            if(children4 > 0) children4 -= 1
            binding.tVChildren4.text = children4.toString()

            if(children4 == 1){
                binding.mCVBed42.visibility = View.GONE
            } else {
                binding.lLRoom4sel.visibility = View.GONE
            }
        }

        binding.mCVDone.setOnClickListener {

            val totalChildren = children1.plus(children2).plus(children3).plus(children4)
            val totalAdults = adults1.plus(adults2).plus(adults3).plus(adults4)

            listener?.sendData(
                adults1,
                adults2,
                adults3,
                adults4,
                numberOfRooms,
                bed11,
                bed12,
                bed21,
                bed22,
                bed31,
                bed32,
                bed41,
                bed42,
                totalChildren,
                totalAdults
            )
            dialog?.dismiss()
            Toast.makeText(requireContext(), "Done", Toast.LENGTH_SHORT).show()
        }

        binding.iVClose.setOnClickListener {
            binding.mCVDone.performClick()
        }

    }

    override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
        var item = p0?.getItemAtPosition(p2).toString()

        if (binding.spin11.selectedItem == childrenBeds[0]) bed11 =
            true else if (binding.spin11.selectedItem == childrenBeds[1]) bed11 = false

        if (binding.mCVBed12.visibility == View.VISIBLE) {
            if (binding.spin12.selectedItem == childrenBeds[0]) bed12 =
                true else if (binding.spin12.selectedItem == childrenBeds[1]) bed12 = false
        }
        if (binding.lLRoom2sel.visibility == View.VISIBLE) {
            if (binding.spin21.selectedItem == childrenBeds[0]) bed21 =
                true else if (binding.spin21.selectedItem == childrenBeds[1]) bed21 = false
        }
        if (binding.mCVBed22.visibility == View.VISIBLE) {
            if (binding.spin22.selectedItem == childrenBeds[0]) bed22 =
                true else if (binding.spin22.selectedItem == childrenBeds[1]) bed22 = false
        }
        if (binding.lLRoom3sel.visibility == View.VISIBLE) {
            if (binding.spin31.selectedItem == childrenBeds[0]) bed31 =
                true else if (binding.spin31.selectedItem == childrenBeds[1]) bed31 = false
        }
        if (binding.mCVBed32.visibility == View.VISIBLE) {
            if (binding.spin32.selectedItem == childrenBeds[0]) bed32 =
                true else if (binding.spin32.selectedItem == childrenBeds[1]) bed32 = false
        }
        if (binding.lLRoom4sel.visibility == View.VISIBLE) {
            if (binding.spin41.selectedItem == childrenBeds[0]) bed41 =
                true else if (binding.spin41.selectedItem == childrenBeds[1]) bed41 = false
        }
        if (binding.mCVBed42.visibility == View.VISIBLE) {
            if (binding.spin42.selectedItem == childrenBeds[0]) bed42 =
                true else if (binding.spin42.selectedItem == childrenBeds[1]) bed42 = false
        }
        Log.d("rAdultsC bed11", "onViewCreated: $bed11")
        Log.d("rAdultsC bed12", "onViewCreated: $bed12")
        Log.d("rAdultsC bed21", "onViewCreated: $bed21")
        Log.d("rAdultsC bed22", "onViewCreated: $bed22")
        Log.d("rAdultsC bed31", "onViewCreated: $bed31")
        Log.d("rAdultsC bed32", "onViewCreated: $bed32")
        Log.d("rAdultsC bed41", "onViewCreated: $bed41")
        Log.d("rAdultsC bed42", "onViewCreated: $bed42")

    }

    override fun onNothingSelected(p0: AdapterView<*>?) {
        var item = p0?.getItemAtPosition(0) as String
    }

    fun setListener(listener: OnHotelListener) {
        this.listener = listener
    }


}