package `in`.tourism.pilgrimagetour.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import `in`.tourism.pilgrimagetour.R

class GuidelinesAdapter(private val limited: Boolean, private var guidelinesList: List<String>) :
    RecyclerView.Adapter<GuidelinesAdapter.GuidelinesViewHolder>() {

    private val limit = 3

    class GuidelinesViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        val textView: TextView
        init {
            textView = view.findViewById(R.id.tVGuid)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GuidelinesViewHolder {

        return GuidelinesViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.guidelines_item, parent, false)
        )
    }
    override fun onBindViewHolder(holder: GuidelinesViewHolder, position: Int) {

        if (position< guidelinesList.size)
            holder.textView.text = guidelinesList[position]
    }
    override fun getItemCount(): Int {

        return if (limited) {
            if(guidelinesList.size < limit)
                return guidelinesList.size

            return limit
        }
        else guidelinesList.size
    }
}
