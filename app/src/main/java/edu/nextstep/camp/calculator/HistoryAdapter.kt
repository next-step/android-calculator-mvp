package edu.nextstep.camp.calculator

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import edu.nextstep.camp.calculator.databinding.ItemResultBinding
import edu.nextstep.domain.CalculateHistoryItem

class HistoryAdapter : RecyclerView.Adapter<HistoryViewHolder>() {

    private val historyList = mutableListOf<CalculateHistoryItem>()

    fun setItem(item: CalculateHistoryItem) {
        historyList.add(item)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HistoryViewHolder {
        val view = ItemResultBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return HistoryViewHolder(view)
    }

    override fun onBindViewHolder(holder: HistoryViewHolder, position: Int) =
        holder.bind(historyList[position])

    override fun getItemCount(): Int = historyList.size

}