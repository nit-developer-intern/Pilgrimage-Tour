package `in`.tourism.pilgrimagetour.view

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.viewpager.widget.PagerAdapter
import com.google.android.material.imageview.ShapeableImageView
import `in`.tourism.pilgrimagetour.Cabs
import `in`.tourism.pilgrimagetour.FragmentInteractionListener
import `in`.tourism.pilgrimagetour.R
import `in`.tourism.pilgrimagetour.adapter.CabsAdapter
import `in`.tourism.pilgrimagetour.databinding.FragmentCabsBinding

class CabsFragment : Fragment() {

    private lateinit var binding: FragmentCabsBinding
    private lateinit var cabsAdapter: CabsAdapter
    private var listener: FragmentInteractionListener? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = FragmentCabsBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val cabsList = arrayListOf<Cabs>().apply {

            add(
                Cabs(
                    "Swift Dzire", "5 Seats", "5 Members", "Manual", listOf(
                        R.drawable.sedan4seats, R.drawable.sedan4seats2, R.drawable.sedan4seats
                    )
                )
            )
            add(
                Cabs(
                    "Innova", "6 Seats", "6 Members", "Manual", listOf(
                        R.drawable.innova6seats, R.drawable.innova6seats2, R.drawable.innova6seats3
                    )
                )
            )
            add(
                Cabs(
                    "Ertiga", "6 Seats", "6 Members", "Manual", listOf(
                        R.drawable.ertiga6seats, R.drawable.ertiga6seats2, R.drawable.ertiga6seats3
                    )
                )
            )
            add(
                Cabs(
                    "Innova Crysta", "6 Seats", "6 Members", "Manual", listOf(
                        R.drawable.innova_crysta6seats,
                        R.drawable.innova_crysta6seats2,
                        R.drawable.innova_crysta6seats3
                    )
                )
            )
            add(
                Cabs(
                    "Innova", "7 Seats", "7 Members", "Manual", listOf(
                        R.drawable.innova7seats, R.drawable.innova7seats2, R.drawable.innova7seats3
                    )
                )
            )
            add(
                Cabs(
                    "Innova Crysta", "7 Seats", "7 Members", "Manual", listOf(
                        R.drawable.innova_crysta7seats,
                        R.drawable.innova_crysta7seats2,
                        R.drawable.innova_crysta7seats3
                    )
                )
            )
            add(
                Cabs(
                    "Bus", "18 Seats", "18 Members", "Manual", listOf(
                        R.drawable.bus18seats1, R.drawable.bus18seats2, R.drawable.bus18seats3
                    )
                )
            )


        }

        cabsAdapter = CabsAdapter(requireContext(), cabsList)

        binding.rVCabs.adapter = cabsAdapter

        cabsAdapter.onItemClick = { cab ->

            listener?.sendData("cabsFragment", "${cab.carModel} - ${cab.totalSeats}")
        }

    }

    override fun onAttach(context: Context) {
        super.onAttach(context)

        if (context is FragmentInteractionListener) listener = context
        else throw IllegalArgumentException("$context must implement OnFragmentInteractionListener")

    }
}

class CabSliderAdapter(
    private val context: Context, private var imageList: List<Int>
) : PagerAdapter() {
    override fun getCount(): Int {
        return imageList.size
    }

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view === `object` as ConstraintLayout
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {

        val layoutInflater =
            context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

        val itemView = layoutInflater.inflate(R.layout.slider_item, container, false)

        val imageView = itemView.findViewById<View>(R.id.sIVBanner) as ShapeableImageView

        imageView.setImageResource(imageList[position])

        container.addView(itemView, 0)

        return itemView

    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as ConstraintLayout)
    }


}
