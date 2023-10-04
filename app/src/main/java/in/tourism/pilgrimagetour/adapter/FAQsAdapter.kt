package `in`.tourism.pilgrimagetour.adapter

import `in`.tourism.pilgrimagetour.databinding.FaqItemBinding
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import `in`.tourism.pilgrimagetour.FAQ2
import `in`.tourism.pilgrimagetour.R

class FAQsAdapter(private val limited: Boolean,
    private var fAQsList: List<FAQ2>
) :
    RecyclerView.Adapter<FAQsAdapter.FAQ2sViewHolder>() {

    private val limit = 3

    var onItemClick: ((FAQ2) -> Unit)? = null

    inner class FAQ2sViewHolder(private val binding: FaqItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        var fAQ: FAQ2?= null

        init {
            itemView.setOnClickListener {
//                onItemClick?.invoke(fAQsList[adapterPosition])
                fAQ?.let {
                    it.isExpanded = it.isExpanded.not()

                    notifyItemChanged(fAQsList.indexOf(it))
                }
            }
        }

        fun bind(item: FAQ2) {

            fAQ= item

            binding.tVQ.text = item.textQ
            binding.tVQuestion.text = item.textQuestion
            binding.tVAnswer.text = item.textAnswer

            if (item.isExpanded){

                binding.iVDownUp.setImageResource(R.drawable.ic_arrow_up)
                binding.lLAnswer.visibility= View.VISIBLE
            } else{
                binding.iVDownUp.setImageResource(R.drawable.ic_down)
                binding.lLAnswer.visibility= View.GONE
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FAQ2sViewHolder =
        FAQ2sViewHolder(
            FaqItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )

    override fun onBindViewHolder(holder: FAQ2sViewHolder, position: Int) {

        if (position< fAQsList.size)
             holder.bind(fAQsList[position])
    }

    override fun getItemCount(): Int {

        return if (limited){
            if(fAQsList.size < limit)
                return fAQsList.size

            limit
        }
        else fAQsList.size
    }
}
