package `in`.tourism.pilgrimagetour.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import `in`.tourism.pilgrimagetour.R

class ExclusionsAdapter(private val limited: Boolean, private var exclusions: List<String>) :
    RecyclerView.Adapter<ExclusionsAdapter.ExclusionsViewHolder>() {

    private val limit = 3
    private var showReadMoreButton = false

    class ExclusionsViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        val textView: TextView
//        val readButton: Button


        init {
            textView = view.findViewById(R.id.tVExc1)
//            readButton = view.findViewById(R.id.tVReadMore2)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExclusionsViewHolder {

        return ExclusionsViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.exclusions_item, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ExclusionsViewHolder, position: Int) {

        holder.textView.text = exclusions[position]
//        if(exclusions.size < limit) holder.readButton.visibility = View.GONE
    }

    override fun getItemCount(): Int {

//        return if (limited) limit
        return if(limited) {
            if(exclusions.size < limit) {
                showReadMoreButton = false
                return exclusions.size}
            else {
                showReadMoreButton = true
                limit
            }
        }
        else {
            showReadMoreButton = false
            exclusions.size
        }
    }

    fun showReadMoreButton(): Boolean {
        return showReadMoreButton
    }
}
