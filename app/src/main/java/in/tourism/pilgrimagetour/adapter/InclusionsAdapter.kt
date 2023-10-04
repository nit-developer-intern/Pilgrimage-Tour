package `in`.tourism.pilgrimagetour.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import `in`.tourism.pilgrimagetour.R

class InclusionsAdapter(private val limited: Boolean, private var inclusions: List<String>) :
    RecyclerView.Adapter<InclusionsAdapter.InclusionsViewHolder>() {

    private val limit = 3

    class InclusionsViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        val textView: TextView

        init {

            textView = view.findViewById(R.id.tVInc1)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): InclusionsViewHolder {
        return InclusionsViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.inclusions_item, parent, false)
        )
    }

    override fun onBindViewHolder(holder: InclusionsViewHolder, position: Int) {
        if (position< inclusions.size)
            holder.textView.text = inclusions[position]
    }

    override fun getItemCount(): Int {
        return if(limited) {
            if(inclusions.size < limit) return inclusions.size
            else limit
        }
        else inclusions.size
    }
}
