package `in`.tourism.pilgrimagetour.view

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.activity.addCallback
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import `in`.tourism.pilgrimagetour.databinding.FragmentSearchBinding
import `in`.tourism.pilgrimagetour.databinding.SearchFragmentItemBinding
import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import androidx.fragment.app.activityViewModels
import `in`.tourism.pilgrimagetour.FragmentInteractionListener
import `in`.tourism.pilgrimagetour.OnCardClickListener2
import `in`.tourism.pilgrimagetour.R
import `in`.tourism.pilgrimagetour.TravelPac
import `in`.tourism.pilgrimagetour.viewmodel.MainViewModel

class SearchFragment : Fragment(), SearchView.OnQueryTextListener {

    private lateinit var binding: FragmentSearchBinding
    private var packagesList: List<TravelPac?>? = null
    private lateinit var filteredList: ArrayList<TravelPac?>
    private lateinit var searchAdapter: SearchAdapter
    private var docId: String? = null
    private var listener: FragmentInteractionListener? = null
    private val viewModel: MainViewModel by activityViewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val callback = requireActivity().onBackPressedDispatcher.addCallback(this) {

            (activity as MainActivity).selectTab(0)
        }
        callback.isEnabled
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentSearchBinding.inflate(layoutInflater)

        return binding.root
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.sV.requestFocus()
        binding.sV.setOnQueryTextListener(this)
        binding.rV.visibility = View.VISIBLE

        packagesList = ArrayList()
        packagesList = viewModel.packagesList

        val closeButtonImage =
            binding.sV.findViewById<ImageView>(androidx.appcompat.R.id.search_close_btn)

        closeButtonImage.setImageResource(R.drawable.ic_cancel4)

        val layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        binding.rV.layoutManager = layoutManager

        searchAdapter = SearchAdapter(onCardClickListener, packagesList)
        binding.rV.adapter = searchAdapter

        packagesList?.let { searchAdapter.addData(it) }
        searchAdapter.notifyDataSetChanged()
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
                    fragment = "searchFragment", cab = "${travelPac?.name}- ${travelPac?.days}"
                )

            } else if (travelPac?.daysList?.isEmpty() == false) startActivity(detailsIntent)

        }

    }

    override fun onQueryTextSubmit(query: String?): Boolean {

        binding.rV.visibility = View.VISIBLE

        if (query != null) {
            filter(query)
        }
        return false
    }

    override fun onQueryTextChange(newText: String?): Boolean {

        filteredList = ArrayList()

        packagesList = viewModel.packagesList

        binding.rV.visibility = View.VISIBLE

        if (newText != null) {
            filter(newText)
        }
        return false
    }

    private fun filter(text: String) {

        packagesList?.filter {
            (it!!.name!!.lowercase().trim().contains(text.lowercase().trim()))
        }?.forEach {
            if (!filteredList.contains(it)) filteredList.add(it)
        }

        if (filteredList.isEmpty()) {
            binding.rV.visibility = View.INVISIBLE
            binding.tV.visibility = View.VISIBLE
        } else {
            binding.tV.visibility = View.INVISIBLE

            searchAdapter.addData(filteredList)
            searchAdapter.notifyDataSetChanged()
        }
    }


    inner class SearchAdapter(
        onCardClickListener2: OnCardClickListener2?, private var packagesList: List<TravelPac?>?
    ) : RecyclerView.Adapter<SearchAdapter.SearchViewHolder>() {

        inner class SearchViewHolder(private val binding: SearchFragmentItemBinding) :
            RecyclerView.ViewHolder(binding.root) {
            init {
                itemView.setOnClickListener {

                    if (packagesList?.get(adapterPosition)?.daysList?.isEmpty() == false)

                        onCardClickListener.onClick(
                            true, packagesList!![adapterPosition]
                        )
                    else onCardClickListener.onClick(
                        false, packagesList!![adapterPosition]
                    )
                }
            }

            fun bind(result: TravelPac) {
                binding.tvName.text = "${result.name}- ${result.days}"
            }
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = SearchViewHolder(
            SearchFragmentItemBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )

        override fun onBindViewHolder(holder: SearchViewHolder, position: Int) {

            if (packagesList != null) packagesList!![position]?.let { holder.bind(it) }
        }

        override fun getItemCount(): Int = packagesList!!.size

        @SuppressLint("NotifyDataSetChanged")
        fun addData(list: List<TravelPac?>) {
            packagesList = list

            notifyDataSetChanged()
        }
    }
}
