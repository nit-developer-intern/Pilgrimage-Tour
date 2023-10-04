package `in`.tourism.pilgrimagetour.adapter

import android.content.Context
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.recyclerview.widget.RecyclerView
import `in`.tourism.pilgrimagetour.CustomerDetails
import `in`.tourism.pilgrimagetour.databinding.CustomerDetailsItemBinding

class AdultCustomerAdapter(
     val viewType: Int ,
     private var check: Boolean ,
     private val context: Context,
     private val size: Int,
     private var adultsList : ArrayList<CustomerDetails>
)  : RecyclerView.Adapter<AdultCustomerAdapter.AdultsViewHolder>() {

    inner class AdultsViewHolder(private val binding: CustomerDetailsItemBinding) :
        RecyclerView.ViewHolder(binding.root), AdapterView.OnItemSelectedListener {


        fun bind(customer: CustomerDetails) {

            binding.tVCustomerHeading.text = customer.customerHeading
            binding.tIETCustomerNAme.setText(customer.customerNAme)
            binding.spinnerGender.onItemSelectedListener = this
            binding.iVGender.setOnClickListener { binding.spinnerGender.performClick() }
            binding.tIETCustomerAge.setText(customer.customerAge)
            binding.tIETCustomerNAme.addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(
                    charSequence: CharSequence?, i: Int, i1: Int, i2: Int
                ) {
                }

                override fun onTextChanged(
                    charSequence: CharSequence?, i: Int, i1: Int, i2: Int
                ) {
                    adultsList[absoluteAdapterPosition].customerNAme = charSequence.toString()
//                            .setEditTextValue(editText.getText().toString())
//                        Toast.makeText(context, customerList[0].customerNAme, Toast.LENGTH_LONG).show()
                }

                override fun afterTextChanged(editable: Editable?) {

                }
            })
            binding.tIETCustomerAge.addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(
                    charSequence: CharSequence?, i: Int, i1: Int, i2: Int
                ) {
                }

                override fun onTextChanged(
                    charSequence: CharSequence?, i: Int, i1: Int, i2: Int
                ) {
                    adultsList[absoluteAdapterPosition].customerAge = charSequence.toString()
                }

                override fun afterTextChanged(editable: Editable?) {

                }
            })

            val genders = arrayOf("Male", "Female", "Others")
            val genderAdapter =
                ArrayAdapter(context, android.R.layout.simple_spinner_dropdown_item, genders)
            genderAdapter.setDropDownViewResource((android.R.layout.simple_spinner_dropdown_item))
            binding.spinnerGender.adapter = genderAdapter

            when (viewType) {

                2 -> {

                    binding.tIETCustomerWeight.setText(customer.customerWeight)

                    if (check) {
                        if (binding.tIETCustomerWeight.text.trim()
                                .isEmpty() || binding.tIETCustomerWeight.text.toString()
                                .toInt() > 250
                        ) {
                            binding.tIETCustomerWeight.error = "Please enter valid weight in kg"
                            binding.tIETCustomerWeight.requestFocus()

                        } else binding.tIETCustomerWeight.error = null
                    }

                    binding.tIETCustomerWeight.addTextChangedListener(object : TextWatcher {
                        override fun beforeTextChanged(
                            p0: CharSequence?,
                            p1: Int,
                            p2: Int,
                            p3: Int
                        ) {

                        }

                        override fun onTextChanged(
                            charSequence: CharSequence?,
                            p1: Int,
                            p2: Int,
                            p3: Int
                        ) {
                            adultsList[absoluteAdapterPosition].customerWeight =
                                charSequence.toString()
                        }

                        override fun afterTextChanged(p0: Editable?) {

                        }
                    })
                }

                else -> {
                    binding.mCVCustomerWeight.visibility = View.GONE
                    customer.customerWeight = "NA"
                }
            }

            if (check) {
                if (binding.tIETCustomerNAme.text.isEmpty()) {
                    binding.tIETCustomerNAme.error = "Please Enter Your Name"
                    binding.tIETCustomerNAme.requestFocus()
                } else binding.tIETCustomerNAme.error = null


                if (binding.tIETCustomerAge.text.isEmpty() || binding.tIETCustomerAge.text.toString()
                        .toInt() > 110
                ) {
                    binding.tIETCustomerAge.error = "Please Enter Age(in Years) "
                    binding.tIETCustomerAge.requestFocus()
                } else binding.tIETCustomerAge.error = null

            }
        }

        override fun onItemSelected(parent: AdapterView<*>?, p1: View?, position: Int, id: Long) {
            adultsList[absoluteAdapterPosition].customerGender =
                parent?.getItemAtPosition(position).toString()
        }

        override fun onNothingSelected(parent: AdapterView<*>?) {
            adultsList[absoluteAdapterPosition].customerGender =
                parent?.getItemAtPosition(0).toString()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AdultsViewHolder {
        return AdultsViewHolder(
            CustomerDetailsItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent, false
            )
        )
    }

    override fun getItemCount(): Int {
        return size
    }

    override fun onBindViewHolder(holder: AdultsViewHolder, position: Int) {
        try {

            holder.bind(adultsList[position])

        } catch (e: java.lang.IndexOutOfBoundsException) {
            Log.d("AdultsList", "onBindViewHolder: ${e.message}")
        }
    }

    fun validateCustomer() {

        check = true
        notifyDataSetChanged()
    }
}
